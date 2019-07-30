package cup.com.advice;

import cup.com.exception.BusinessException;
import cup.com.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Description：全局异常处理
 * Author:TengLu
 * Date:2019/7/30
 * Time:0:27
 */
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResult> handleException(BusinessException e) {
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
