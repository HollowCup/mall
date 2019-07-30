package cup.com.service.impl;

import cup.com.constant.CategoryConstant;
import cup.com.mapper.CategoryMapper;
import cup.com.pojo.Category;
import cup.com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/7/30
 * Time:8:52
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryByPid(Long pid) {

        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId", pid);

        return categoryMapper.selectByExample(example);
    }

    @Override
    public void addCategory(Category category) {
        // 插入类目数据
        categoryMapper.insert(category);
        // 保证父节点is_parent字段为true
        Category parent = new Category();
        parent.setId(category.getParentId());
        parent.setIsParent(true);
        categoryMapper.updateByPrimaryKeySelective(parent);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public void deleteCategory(Long cid) {
        Category deleteCategory = categoryMapper.selectByPrimaryKey(cid);
        if (deleteCategory != null && !deleteCategory.getId().equals(CategoryConstant.ROOT_CATEGORY_ID)) {
            if (deleteCategory.getIsParent()) {
                // 如果不为3级类目，递归获取该类目下所有子类目id
                List<Long> deleteIds = new ArrayList<>();
                deleteIds.add(deleteCategory.getId());
                getAllDeleteCategoryIds(deleteIds,cid);
                // 执行删除
                Example example = new Example(Category.class);
                example.createCriteria().andIn("id", deleteIds);
                categoryMapper.deleteByExample(example);
            } else {
                // 三级类目直接删除
                categoryMapper.deleteByPrimaryKey(deleteCategory);
            }
        }
    }

    private void getAllDeleteCategoryIds(List<Long> deleteIds, Long cid) {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId", cid);
        List<Category> childCategorys = categoryMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(childCategorys)) {
            for (Category childCategory : childCategorys) {
                deleteIds.add(childCategory.getId());
                if (childCategory.getIsParent()) {
                    getAllDeleteCategoryIds(deleteIds, childCategory.getId());
                }
            }
        }
    }
}
