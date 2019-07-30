package cup.com.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description：
 * Author:TengLu
 * Date:2019/7/30
 * Time:12:51
 */
@Data
public class UpdateCategoryReq {
    @NotNull
    @ApiModelProperty(value = "类目id")
    private Long id;
    @NotNull
    @ApiModelProperty(value = "类目名称")
    private String name;
    @NotNull
    @ApiModelProperty(value = "父类目id,顶级类目填0")
    private Long parentId;
    @NotNull
    @ApiModelProperty(value = "是否为父节点，0为否，1为是")
    private Boolean isParent;
    @NotNull
    @ApiModelProperty(value = "排序指数，越小越靠前")
    private Integer sort;
}
