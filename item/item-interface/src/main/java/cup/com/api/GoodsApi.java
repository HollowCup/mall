package cup.com.api;

import cup.com.bo.SpuBo;
import cup.com.pojo.PageResult;
import cup.com.pojo.Sku;
import cup.com.pojo.Spu;
import cup.com.pojo.SpuDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("goods")
public interface GoodsApi {
    @GetMapping("/spu/detail/{spuId}")
    SpuDetail querySpuDetailBySpuId(@PathVariable("spuId") Long spuId);

    @GetMapping("/spu/page")
    PageResult<SpuBo> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable", defaultValue = "true") Boolean saleable);

    @GetMapping("/sku/list")
    List<Sku> querySkusBySpuId(@RequestParam("spuId") Long spuId);

    @GetMapping("{id}")
    Spu querySpuById(@PathVariable("id") Long id);
}
