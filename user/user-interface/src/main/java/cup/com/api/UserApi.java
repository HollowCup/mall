package cup.com.api;

import cup.com.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/2
 * Time:12:49
 */
@RequestMapping
public interface UserApi {
    @GetMapping("query")
    User queryUser(@RequestParam("username") String username, @RequestParam("password") String password);
}
