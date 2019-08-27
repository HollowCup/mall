package cup.com.client;

import cup.com.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/8
 * Time:17:17
 */
@FeignClient(value = "item-service")
public interface SpecificationClient extends SpecificationApi {
}
