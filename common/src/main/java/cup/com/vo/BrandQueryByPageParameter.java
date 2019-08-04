package cup.com.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description：
 * Author:TengLu
 * Date:2019/7/30
 * Time:22:33
 */
@Data
@AllArgsConstructor
public class BrandQueryByPageParameter {
    @ApiModelProperty(value = "当前页")
    private Integer page;
    @ApiModelProperty(value = "每页大小")
    private Integer rows;
    @ApiModelProperty(value = "排序字段")
    private String sortBy;
    @ApiModelProperty(value = "是否为降序")
    private Boolean desc;
    @ApiModelProperty(value = "搜索关键词")
    private String key;
}
