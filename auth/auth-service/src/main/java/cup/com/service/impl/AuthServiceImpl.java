package cup.com.service.impl;

import cup.com.client.UserClient;
import cup.com.config.JwtProperties;
import cup.com.pojo.User;
import cup.com.pojo.UserInfo;
import cup.com.service.AuthService;
import cup.com.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/2
 * Time:12:45
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserClient userClient;
    @Autowired
    private JwtProperties jwtProperties;

    public String accredit(String username, String password) {
        // 1.根据用户名和密码查询
        User user = userClient.queryUser(username, password);
        // 2.判断user
        if (user == null) {
            return null;
        }
        try {
            // 3.jwtUtils生成jwt类型的token
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUsername());
            return JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
