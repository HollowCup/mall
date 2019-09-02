package cup.com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/2
 * Time:11:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;

    private String username;
}
