package cup.com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/13
 * Time:20:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult extends PageResult<Goods>{
    private List<Map<String,Object>> categories;
    private List<Brand> brands;
    private List<Map<String,Object>> specs;

    public SearchResult(Long total, Integer totalPage, List<Goods> items,
                        List<Map<String,Object>> categories, List<Brand> brands,
                        List<Map<String,Object>> specs) {
        super(total, Long.valueOf(totalPage), items);
        this.categories = categories;
        this.brands = brands;
        this.specs = specs;
    }
}
