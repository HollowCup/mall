package cup.com.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Description：商品规格组实体类
 * Author:TengLu
 * Date:2019/8/4
 * Time:19:50
 */
@Table(name = "tb_spec_group")
@Data
public class SpecGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cid;
    private String name;
    @Transient
    private List<SpecParam> params;
}
