package cup.com.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description：
 * Author:TengLu
 * Date:2019/7/30
 * Time:10:32
 */
@Data
public class AddCategoryReq {
    @NotNull(message = "类目名称不能为空")
    @ApiModelProperty(value = "类目名称")
    private String name;
    @NotNull(message = "父类目id不能为空")
    @ApiModelProperty(value = "父类目id,顶级类目填0")
    private Long parentId;
    @NotNull(message = "是否为父节点不能为空")
    @ApiModelProperty(value = "是否为父节点，0为否，1为是")
    private Boolean isParent;
    @NotNull(message = "排序指数不能为空")
    @ApiModelProperty(value = "排序指数，越小越靠前")
    private Integer sort;
}
