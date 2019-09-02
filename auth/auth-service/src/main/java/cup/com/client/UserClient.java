package cup.com.client;

import cup.com.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/2
 * Time:12:52
 */
@FeignClient("user-service")
public interface UserClient extends UserApi {
}
