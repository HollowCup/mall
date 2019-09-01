package cup.com.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/29
 * Time:22:51
 */
@Data
@ConfigurationProperties(prefix = "sms")
@Configuration
public class SmsProperties {

    String accessKeyId;

    String accessKeySecret;

    String signName;

    String verifyCodeTemplate;

}
