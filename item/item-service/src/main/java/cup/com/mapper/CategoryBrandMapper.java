package cup.com.mapper;

import cup.com.pojo.CategoryBrand;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Description：
 * Author:TengLu
 * Date:2019/7/30
 * Time:8:49
 */
@Repository
public interface CategoryBrandMapper extends Mapper<CategoryBrand>, InsertListMapper<CategoryBrand> {
}
