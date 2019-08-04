package cup.com.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Description：商品规格参数实体类
 * Author:TengLu
 * Date:2019/8/4
 * Time:19:53
 */
@Table(name = "tb_spec_param")
@Data
public class SpecParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cid;
    private Long groupId;
    private String name;
    @Column(name = "'numeric'")
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;
}
