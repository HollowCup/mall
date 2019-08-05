package cup.com.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/5
 * Time:14:35
 */
@Table(name = "tb_sku")
@Data
public class Sku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String ownSpec;
    private String indexes;
    private Boolean enable;
    private Date createTime;
    private Date lastUpdateTime;
    @Transient
    private Long stock;
}
