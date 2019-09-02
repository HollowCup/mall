package cup.com.controller;

import cup.com.config.JwtProperties;
import cup.com.pojo.UserInfo;
import cup.com.service.AuthService;
import cup.com.utils.CookieUtils;
import cup.com.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/2
 * Time:12:21
 */
@Controller
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("accredit")
    @ApiOperation(value = "登录授权生成JWT", notes = "登录授权生成JWT")
    public ResponseEntity<Void> accredit(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response) {
        String token = authService.accredit(username, password);
        if (StringUtils.isBlank(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        CookieUtils.setCookie(request, response, jwtProperties.getCookieName(), token, jwtProperties.getExpire() * 60);
        return ResponseEntity.ok().build();
    }

    @GetMapping("verify")
    @ApiOperation(value = "根据JWT获取用户信息", notes = "根据JWT获取用户信息")
    public ResponseEntity<UserInfo> verify(@CookieValue("LY_TOKEN") String token, HttpServletRequest request,
                                               HttpServletResponse response){
        try {
            // 通过jwt工具类使用公钥解析jwt
            UserInfo user = JwtUtils.getInfoFromToken(token,jwtProperties.getPublicKey());
            if(user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // 刷新jwt中有效时间
            token = JwtUtils.generateToken(user,jwtProperties.getPrivateKey(),jwtProperties.getExpire());
            // 刷新cookie中的有效时间
            CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getExpire()*60);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
