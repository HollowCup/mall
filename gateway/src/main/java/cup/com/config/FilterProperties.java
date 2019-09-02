package cup.com.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/2
 * Time:20:33
 */
@Configuration
@Data
public class FilterProperties {
    @Value("${filter.allowPaths}")
    private List<String> allowPaths;
}
