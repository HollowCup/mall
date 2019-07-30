package cup.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/7/30
 * Time:0:11
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@MapperScan("cup.com.mapper")
public class Item {
    public static void main(String[] args) {
        SpringApplication.run(Item.class, args);
    }

    /**
     * swagger的配置
     *
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("cup.com")) // 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build().ignoredParameterTypes(ApiIgnore.class);
    }
}
