package cup.com.config;

import cup.com.utils.RsaUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/2
 * Time:12:16
 */
@Data
@Configuration
public class JwtProperties {

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);


    /**
     * 公钥地址
     */
    @Value("${jwt.pubKeyPath}")
    private String pubKeyPath;

    /**
     * 公钥
     */
    private PublicKey publicKey;


    /**
     * cookie名字
     */
    @Value("${jwt.cookieName}")
    private String cookieName;


    /**
     * @PostConstruct :在构造方法执行之后执行该方法
     */
    @PostConstruct
    public void init() {
        try {
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            logger.error("初始化公钥失败！", e);
            throw new RuntimeException();
        }
    }
}