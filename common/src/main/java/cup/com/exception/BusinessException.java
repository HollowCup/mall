package cup.com.exception;

import cup.com.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description：
 * Author:TengLu
 * Date:2019/7/30
 * Time:1:34
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BusinessException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
