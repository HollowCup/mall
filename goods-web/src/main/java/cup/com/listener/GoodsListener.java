package cup.com.listener;

import cup.com.service.GoodsHtmlService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/28
 * Time:15:24
 */
@Component
public class GoodsListener {

    @Autowired
    private GoodsHtmlService goodsHtmlService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "create.index.queue", durable = "true"),
            exchange = @Exchange(
                    value = "item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC),
            key = {"item.insert", "item.update"}))
    public void save(Long id) {
        if (id == null) {
            return;
        }
        goodsHtmlService.createHtml(id);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "delete.index.queue", durable = "true"),
            exchange = @Exchange(
                    value = "item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC),
            key = "item.delete"))
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        goodsHtmlService.deleteHtml(id);
    }
}
