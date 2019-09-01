package cup.com.service.impl;

import cup.com.mapper.UserMapper;
import cup.com.pojo.User;
import cup.com.service.UserService;
import cup.com.utils.CodecUtils;
import cup.com.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/28
 * Time:17:35
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:verify";


    public Boolean checkData(String data, Integer type) {
        User record = new User();
        switch (type) {
            case 1:
                record.setUsername(data);
                break;
            case 2:
                record.setPhone(data);
                break;
            default:
                return null;
        }
        return userMapper.selectCount(record) == 0;
    }

    @Override
    public Boolean sendVerifyCode(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }
        // 生成验证码
        String code = NumberUtils.generateCode(6);

        // 发送消息到rabbitMQ
        Map<String, String> msg = new HashMap<>();
        msg.put("phone", phone);
        msg.put("code", code);
        amqpTemplate.convertAndSend("sms.exchanger", "verify_code_sms", msg);

        // 把验证码保存到redis中
        redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);

        return true;
    }

    @Override
    public Boolean register(User user, String code) {
        // 查询redis中验证码
        String redisCode = redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());
        // 1.校验验证码
        if (!StringUtils.equals(code, redisCode)) {
            return false;
        }
        // 2.生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        // 3.加盐加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));
        // 4.新增用户
        user.setId(null);
        user.setCreated(new Date());
        userMapper.insertSelective(user);
        return true;
    }

    @Override
    public User queryUser(String username, String password) {
        User record = new User();
        record.setUsername(username);
        User user = userMapper.selectOne(record);

        // 判断user是否为空
        if (user == null) {
            return null;
        }
        // 获取盐，对用户输入的密码加盐加密
        password = CodecUtils.md5Hex(password, user.getSalt());
        // 和数据库中的密码比较
        if (StringUtils.equals(password, user.getPassword())) {
            return user;
        }
        return null;
    }

}
