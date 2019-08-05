package cup.com.bo;

import cup.com.pojo.Sku;
import cup.com.pojo.Spu;
import cup.com.pojo.SpuDetail;
import lombok.Data;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/5
 * Time:9:00
 */
@Data
public class SpuBo extends Spu {
    private String cname;
    private String bname;
    private SpuDetail spuDetail;
    private List<Sku> skus;
}
