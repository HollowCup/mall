package cup.com.client;

import cup.com.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/8
 * Time:17:15
 */
@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}
