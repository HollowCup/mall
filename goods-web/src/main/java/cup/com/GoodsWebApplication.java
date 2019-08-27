package cup.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Description：详情微服务
 * Author:TengLu
 * Date:2019/8/25
 * Time:19:25
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GoodsWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsWebApplication.class, args);
    }
}
