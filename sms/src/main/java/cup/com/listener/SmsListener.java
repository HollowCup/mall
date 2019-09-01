package cup.com.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import cup.com.config.SmsProperties;
import cup.com.utils.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/30
 * Time:11:18
 */
@Component
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;
    @Autowired
    private SmsProperties smsProperties;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = ".sms.queue",durable = "true"),
            exchange = @Exchange(value = "sms.exchange",ignoreDeclarationExceptions = "true"),
            key = {"verify_code_sms"}
    ))
    public void sendSms(Map<String,String> msg){
        if (CollectionUtils.isEmpty(msg)){
            return;
        }
        String phone = msg.get("phone");
        String code = msg.get("code");

        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(code)){
            try {
                smsUtils.sendSms(phone, code, smsProperties.getSignName(), smsProperties.getVerifyCodeTemplate());
            }catch (ClientException e){
                return;
            }
        }
    }
}
