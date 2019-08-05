package cup.com.mapper;

import cup.com.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/7/30
 * Time:22:47
 */
@Repository
public interface BrandMapper extends Mapper<Brand> {
    @Select("SELECT * FROM tb_brand a INNER JOIN tb_category_brand b ON a.id=b.brand_id WHERE b.category_id=#{cid}")
    List<Brand> queryBrandByCategoryId(Long cid);
}
