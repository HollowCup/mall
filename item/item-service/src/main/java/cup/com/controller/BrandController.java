package cup.com.controller;

import cup.com.pojo.Brand;
import cup.com.pojo.PageResult;
import cup.com.service.BrandService;
import cup.com.vo.BrandQueryByPageParameter;
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
 * Date:2019/7/30
 * Time:22:39
 */
@RestController
@RequestMapping("brand")
@Api(tags = "BrandController", description = "商品品牌相关接口")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询品牌", notes = "分页查询品牌")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                              @RequestParam(value = "rows", defaultValue = "5") Integer rows,
                                                              @RequestParam(value = "sortBy", required = false) String sortBy,
                                                              @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                              @RequestParam(value = "key", required = false) String key) {
        BrandQueryByPageParameter brandQueryByPageParameter = new BrandQueryByPageParameter(page, rows, sortBy, desc, key);
        PageResult<Brand> result = brandService.queryBrandByPage(brandQueryByPageParameter);
        if (CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加品牌", notes = "添加品牌")
    public ResponseEntity<Void> addBrand(@RequestBody Brand brand, @RequestParam("categories") List<Long> categories){
        brandService.addBrand(brand, categories);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("cid/{cid}")
    @ApiOperation(value = "根据分类id查询品牌信息", notes = "根据分类id查询品牌信息")
    public ResponseEntity<List<Brand>> queryBrandByCategoryId(@PathVariable("cid") Long cid){
        List<Brand> list = brandService.queryBrandByCategoryId(cid);
        if (list == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "品牌id查询品牌信息", notes = "品牌id查询品牌信息")
    public ResponseEntity<Brand> queryBrandById(@PathVariable("id") Long id){
        Brand brand = brandService.queryBrandById(id);
        if (brand == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(brand);
    }
}
