package cup.com.mapper;

import cup.com.pojo.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/28
 * Time:17:22
 */
@Repository
public interface UserMapper extends Mapper<User> {
}
