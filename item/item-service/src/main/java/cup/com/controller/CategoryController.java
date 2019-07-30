package cup.com.controller;

import cup.com.dto.AddCategoryReq;
import cup.com.dto.UpdateCategoryReq;
import cup.com.pojo.Category;
import cup.com.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description：商品类目相关接口
 * Author:TengLu
 * Date:2019/7/30
 * Time:12:34
 */
@RestController
@RequestMapping("category")
@Api(tags = "CategoryController", description = "商品类目相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation(value = "根据pid查询类目", notes = "根据pid查询类目")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        if (pid == null || pid < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<Category> categories = categoryService.queryCategoryByPid(pid);
        if (CollectionUtils.isEmpty(categories)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加类目", notes = "添加类目")
    public ResponseEntity<Void> addCategory(@Validated @RequestBody AddCategoryReq req) {
        Category category = new Category();
        BeanUtils.copyProperties(req, category);
        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改类目", notes = "修改类目")
    public ResponseEntity<Void> updateCategory(@Validated @RequestBody UpdateCategoryReq req) {
        Category category = new Category();
        BeanUtils.copyProperties(req, category);
        categoryService.updateCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/delete")
    @ApiOperation(value = "根据id删除类目", notes = "根据id删除类目")
    public ResponseEntity<Void> deleteCategory(@RequestParam(value = "cid") Long cid){
        categoryService.deleteCategory(cid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
