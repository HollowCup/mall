package cup.com.service;

import cup.com.pojo.Brand;
import cup.com.pojo.PageResult;
import cup.com.vo.BrandQueryByPageParameter;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/7/30
 * Time:22:40
 */
public interface BrandService {

    PageResult<Brand> queryBrandByPage(BrandQueryByPageParameter req);

    void addBrand(Brand brand, List<Long> categories);

    List<Brand> queryBrandByCategoryId(Long cid);

    Brand queryBrandById(Long id);
}
