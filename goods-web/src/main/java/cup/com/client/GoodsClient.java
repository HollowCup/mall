package cup.com.client;

import cup.com.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/8
 * Time:16:55
 */
@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {
}
