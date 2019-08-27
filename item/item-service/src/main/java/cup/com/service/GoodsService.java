package cup.com.service;

import cup.com.bo.SpuBo;
import cup.com.pojo.PageResult;
import cup.com.pojo.Sku;
import cup.com.pojo.Spu;
import cup.com.pojo.SpuDetail;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/4
 * Time:23:48
 */
public interface GoodsService {
    PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows);

    void saveGoods(SpuBo spuBo);

    SpuDetail querySpuDetailBySpuId(Long spuId);

    List<Sku> querySkusBySpuId(Long spuId);

    void updateGoods(SpuBo spuBo);

    Spu querySpuById(Long id);
}
