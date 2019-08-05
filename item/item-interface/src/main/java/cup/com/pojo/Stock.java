package cup.com.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/5
 * Time:14:47
 */
@Table(name = "tb_stock")
@Data
public class Stock {
    @Id
    private Long skuId;
    private Integer seckillStock;
    private Integer seckillTotal;
    private Long stock;
}
