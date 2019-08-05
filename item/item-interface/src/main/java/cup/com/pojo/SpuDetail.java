package cup.com.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description：SpuDetail实体类
 * Author:TengLu
 * Date:2019/8/4
 * Time:23:39
 */
@Table(name = "tb_spu_detail")
@Data
public class SpuDetail {
    @Id
    private Long spuId;
    private String description;
    private String genericSpec;
    private String specialSpec;
    private String packingList;
    private String afterService;
}
