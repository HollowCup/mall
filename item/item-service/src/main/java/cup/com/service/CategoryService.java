package cup.com.service;

import cup.com.exception.BusinessException;
import cup.com.pojo.Category;

import java.util.List;

/**
 * Description：商品类目相关接口
 * Author:TengLu
 * Date:2019/7/30
 * Time:8:51
 */
public interface CategoryService {

    /**
     * 根据pid查询分类
     * @param pid
     * @return
     */
    List<Category> queryCategoryByPid(Long pid) throws BusinessException;
    /**
     * 添加类目
     * @param category
     */
    void addCategory(Category category);
    /**
     * 修改类目
     * @param category
     */
    void updateCategory(Category category);
    /**
     * 删除类目
     * @param cid
     */
    void deleteCategory(Long cid);
}
