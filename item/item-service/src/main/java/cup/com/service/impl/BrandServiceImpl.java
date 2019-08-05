package cup.com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cup.com.mapper.BrandMapper;
import cup.com.mapper.CategoryBrandMapper;
import cup.com.pojo.Brand;
import cup.com.pojo.CategoryBrand;
import cup.com.pojo.PageResult;
import cup.com.service.BrandService;
import cup.com.vo.BrandQueryByPageParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/7/30
 * Time:22:40
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public PageResult<Brand> queryBrandByPage(BrandQueryByPageParameter req) {
        // 开启分页助手
        PageHelper.startPage(req.getPage(), req.getRows());

        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(req.getSortBy())) {
            example.setOrderByClause(req.getSortBy() + (req.getDesc() ? " DESC" : " ASC"));
        }
        if (StringUtils.isNotBlank(req.getKey())) {
            example.createCriteria()
                    .orLike("name", req.getKey() + "%")
                    .orEqualTo("letter", req.getKey().toUpperCase());
        }
        List<Brand> list = brandMapper.selectByExample(example);

        // 获取分页信息
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        // 返回自定义分页结果
        return new PageResult<>(pageInfo.getTotal(), Long.valueOf(pageInfo.getPages()), pageInfo.getList());
    }

    @Override
    public void addBrand(Brand brand, List<Long> categories) {
        brandMapper.insertSelective(brand);
        // 添加品牌及类目关系表，使用批量插入
        ArrayList<CategoryBrand> insertObjs = new ArrayList<>(categories.size());
        for (Long categoryId : categories) {
            CategoryBrand obj = new CategoryBrand();
            obj.setBrandId(brand.getId());
            obj.setCategoryId(categoryId);
            insertObjs.add(obj);
        }
        categoryBrandMapper.insertList(insertObjs);
    }

    @Override
    public List<Brand> queryBrandByCategoryId(Long cid) {
        return brandMapper.queryBrandByCategoryId(cid);
    }
}
