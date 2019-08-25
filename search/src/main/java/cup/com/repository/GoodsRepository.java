package cup.com.repository;

import cup.com.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/11
 * Time:16:48
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
