package cup.com.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Description：Spu实体类
 * Author:TengLu
 * Date:2019/8/4
 * Time:23:37
 */
@Table(name = "tb_spu")
@Data
public class Spu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // spu id
    private Long id;
    // 商品所属品牌id
    private Long brandId;
    // 1级类目id
    private Long cid1;
    // 2级类目id
    private Long cid2;
    // 3级类目id
    private Long cid3;
    // 标题
    private String title;
    // 子标题
    private String subTitle;
    // 是否上架，0下架，1上架
    private Boolean saleable;
    // 是否有效，0已删除，1有效
    private Boolean valid;
    // 添加时间
    private Date createTime;
    // 最后修改时间
    private Date lastUpdateTime;
}
