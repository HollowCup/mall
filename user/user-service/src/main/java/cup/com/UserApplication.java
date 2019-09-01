package cup.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/28
 * Time:17:20
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@MapperScan("cup.com.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
