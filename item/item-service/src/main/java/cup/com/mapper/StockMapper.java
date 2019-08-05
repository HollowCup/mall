package cup.com.mapper;

import cup.com.pojo.Stock;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/5
 * Time:15:03
 */
@Repository
public interface StockMapper extends Mapper<Stock> {
}
