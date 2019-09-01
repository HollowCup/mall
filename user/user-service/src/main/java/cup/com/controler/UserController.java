package cup.com.controler;

import cup.com.pojo.User;
import cup.com.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/28
 * Time:17:28
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("check/{data}/{type}")
    @ApiOperation(value = "校验数据是否可用", notes = "校验数据是否可用")
    public ResponseEntity<Boolean> checkUserData(@PathVariable("data") String data, @PathVariable(value = "type") Integer type){
        Boolean bool = userService.checkData(data,type);
        if (bool == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    @PostMapping("code")
    @ApiOperation(value = "发送手机验证码", notes = "发送手机验证码")
    public ResponseEntity senVerifyCode(@RequestParam("phone") String phone){
        Boolean result = userService.sendVerifyCode(phone);
        if (result == null || !result){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public ResponseEntity<Void> register(@Valid User user, @RequestParam("code") String code) {
        userService.register(user, code);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("query")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public ResponseEntity<User> queryUser(@RequestParam("username")String username,@RequestParam("password")String password){
        User user = userService.queryUser(username,password);
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }
}
