import cup.com.SearchApplication;
import cup.com.bo.SpuBo;
import cup.com.client.GoodsClient;
import cup.com.pojo.Goods;
import cup.com.pojo.PageResult;
import cup.com.repository.GoodsRepository;
import cup.com.service.impl.SearchServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/11
 * Time:16:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class ElasticsearchTest {
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private SearchServiceImpl searchService;
    @Autowired
    private GoodsClient goodsClient;

    @Test
    public void test() {
        elasticsearchTemplate.createIndex(Goods.class);
        elasticsearchTemplate.putMapping(Goods.class);

        Integer page = 1;
        Integer rows = 200;
        do {
            // 分批查询spu,获取分页结果集
            PageResult<SpuBo> result = goodsClient.querySpuByPage(page, rows, null, null);
            // 获取当前页的数据
            List<SpuBo> items = result.getItems();
            // 遍历spubo集合转化为List<Goods>
            List<Goods> goodsList = items.stream().map(spuBo -> {
                try {
                    return searchService.buildGoods(spuBo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            goodsRepository.saveAll(goodsList);

            // 获取当前页的数据条数，如果是最后一页，没有100条
            rows = items.size();
            // 每次循环页码加1
            page++;
        } while (rows == 100);
    }
}
