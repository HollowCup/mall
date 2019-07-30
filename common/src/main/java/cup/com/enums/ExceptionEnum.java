package cup.com.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description：异常枚举类
 * Author:TengLu
 * Date:2019/7/30
 * Time:1:32
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    ;
    private int code;
    private String msg;
}
