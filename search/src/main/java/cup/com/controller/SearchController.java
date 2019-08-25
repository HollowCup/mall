package cup.com.controller;

import cup.com.bo.SpuBo;
import cup.com.client.GoodsClient;
import cup.com.pojo.Goods;
import cup.com.pojo.PageResult;
import cup.com.pojo.SearchRequest;
import cup.com.pojo.SearchResult;
import cup.com.repository.GoodsRepository;
import cup.com.service.SearchService;
import cup.com.service.impl.SearchServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/11
 * Time:23:26
 */
@RestController
@RequestMapping("search")
@Api(tags = "SearchController", description = "搜索相关接口")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("page")
    @ApiOperation(value = "搜索商品接口", notes = "搜索商品接口")
    public ResponseEntity<SearchResult> search(@RequestBody SearchRequest request) {
        SearchResult result = searchService.search(request);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
}
