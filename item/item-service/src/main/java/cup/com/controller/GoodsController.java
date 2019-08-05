package cup.com.controller;

import cup.com.bo.SpuBo;
import cup.com.pojo.PageResult;
import cup.com.pojo.Sku;
import cup.com.pojo.SpuDetail;
import cup.com.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/4
 * Time:23:45
 */
@RestController
@RequestMapping("goods")
@Api(tags = "GoodsController", description = "商品相关接口")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/spu/page")
    @ApiOperation(value = "分页查询Spu", notes = "分页查询Spu")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable", defaultValue = "true") Boolean saleable) {
        PageResult<SpuBo> result = goodsService.querySpuByPage(key, saleable, page, rows);
        if (result == null || CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增商品", notes = "新增商品")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo) {
        goodsService.saveGoods(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改商品", notes = "修改商品")
    public ResponseEntity<Void> updateGoods(@RequestBody SpuBo spuBo){
        goodsService.updateGoods(spuBo);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/spu/detail/{spuId}")
    @ApiOperation(value = "根据spuId查询SpuDetail", notes = "根据spuId查询SpuDetail")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId") Long spuId) {
        SpuDetail spuDetail = goodsService.querySpuDetailBySpuId(spuId);
        if (spuDetail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(spuDetail);
    }

    @GetMapping("/sku/list")
    @ApiOperation(value = "根据spuId查询Sku集合", notes = "根据spuId查询Sku集合")
    public ResponseEntity<List<Sku>> querySkusBySpuId(@RequestParam("spuId") Long spuId) {

        List<Sku> skus = goodsService.querySkusBySpuId(spuId);
        if (CollectionUtils.isEmpty(skus)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(skus);
    }
}
