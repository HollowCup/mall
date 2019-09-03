package cup.com.client;

import cup.com.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/3
 * Time:19:02
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
