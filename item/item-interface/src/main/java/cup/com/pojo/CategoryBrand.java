package cup.com.pojo;

import lombok.Data;

import javax.persistence.Table;

/**
 * Description：品牌类目关系实体类
 * Author:TengLu
 * Date:2019/7/31
 * Time:10:36
 */
@Table(name = "tb_category_brand")
@Data
public class CategoryBrand {
    private Long categoryId;
    private Long brandId;
}
