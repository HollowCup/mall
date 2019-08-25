package cup.com.client;

import cup.com.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/8
 * Time:17:16
 */
@FeignClient(value = "item-service")
public interface BrandClient extends BrandApi {
}
