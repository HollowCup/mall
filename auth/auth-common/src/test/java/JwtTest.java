import cup.com.pojo.UserInfo;
import cup.com.utils.JwtUtils;
import cup.com.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/2
 * Time:12:02
 */
public class JwtTest {
    private static final String pubKeyPath = "C:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "C:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "mall@Login(Auth}*^31)%");
    }

//    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU2NzgyOTI0OH0.T45EVWA1bEzjUO86IVOsFX7yDiCyIJda6QqCzrCQuRPunzYscKcrsYbfyLGzQzAIAWr-TBYrixLQU3OiTEkungHxKSkh5LaoVBhFf5z8UxkoX6ALIPfUv6-5JtTeZgMkeFgY8mfE98efvVLjUdIVAle_-55Yu5htNSFBZQEi9Bo";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }

    @Test
    public void date(){
        System.out.println(new Date());
    }
}
