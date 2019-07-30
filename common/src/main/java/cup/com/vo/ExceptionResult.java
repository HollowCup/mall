package cup.com.vo;

import cup.com.enums.ExceptionEnum;
import lombok.Data;

/**
 * Description：异常返回结果
 * Author:TengLu
 * Date:2019/7/30
 * Time:1:38
 */
@Data
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
