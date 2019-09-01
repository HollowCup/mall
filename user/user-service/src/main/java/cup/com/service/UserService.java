package cup.com.service;

import cup.com.pojo.User;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/28
 * Time:17:27
 */
public interface UserService {
    Boolean checkData(String data, Integer type);

    Boolean sendVerifyCode(String phone);

    Boolean register(User user, String code);

    User queryUser(String username, String password);
}
