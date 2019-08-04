package cup.com.controller;

import cup.com.pojo.SpecGroup;
import cup.com.pojo.SpecParam;
import cup.com.service.SpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/4
 * Time:20:06
 */
@RestController
@RequestMapping("spec")
@Api(tags = "SpecificationController", description = "规格参数相关接口")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    @GetMapping("/groups/{cid}")
    @ApiOperation(value = "根据cid规格参数组", notes = "根据cid规格参数组")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid") Long cid) {
        List<SpecGroup> groups = specificationService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/queryParamsByGid")
    @ApiOperation(value = "根据gid查询规格参数", notes = "根据gid查询规格参数")
    public ResponseEntity<List<SpecParam>> queryParamsByGid(@RequestParam("gid") Long gid) {
        List<SpecParam> params = specificationService.queryParamsByGid(gid);
        if (CollectionUtils.isEmpty(params)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }
}
