package cup.com.service.impl;

import cup.com.client.BrandClient;
import cup.com.client.CategoryClient;
import cup.com.client.GoodsClient;
import cup.com.client.SpecificationClient;
import cup.com.pojo.*;
import cup.com.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/26
 * Time:17:15
 */
@Service
public class GoodServiceImpl implements GoodsService {

    @Autowired
    private BrandClient brandClient;
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private SpecificationClient specificationClient;

    @Override
    public Map<String, Object> loadData(Long spuId) {

        Map<String, Object> model = new HashMap<>();

        // 根据SpuId查询spu
        Spu spu = goodsClient.querySpuById(spuId);

        // 查询SpuDetail
        SpuDetail spuDetail = goodsClient.querySpuDetailBySpuId(spuId);

        // 查询分类：Map<String,Object>
        List<Long> cids = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
        List<String> names = categoryClient.queryNameByIds(cids);

        // 初始化一个分类的map
        List<HashMap<String, Object>> categories = new ArrayList<>();
        for (int i = 0; i < cids.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", cids.get(i));
            map.put("name", names.get(i));
            categories.add(map);
        }

        // 查询品牌
        Brand brand = brandClient.queryBrandById(spu.getBrandId());

        // skus
        List<Sku> skus = goodsClient.querySkusBySpuId(spuId);

        // 查询规格参数组
        List<SpecGroup> groups = specificationClient.queryGroupsWithParam(spu.getCid3());
        // 查询特殊的规格参数
        List<SpecParam> params = specificationClient.queryParams(null, spu.getCid3(), false, null);
        // 初始化特殊规格参数的map
        HashMap<Long, String> paramMap = new HashMap<>();
        params.forEach(param -> {
            paramMap.put(param.getId(), param.getName());
        });

        model.put("spu", spu);
        model.put("spuDetail", spuDetail);
        model.put("categories", categories);
        model.put("brand", brand);
        model.put("skus", skus);
        model.put("groups", groups);
        model.put("paramMap", paramMap);

        return model;
    }
}
