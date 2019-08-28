package cup.com.listener;

import cup.com.service.SearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/28
 * Time:15:24
 */
@Component
public class GoodsListener {

    @Autowired
    private SearchService searchService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "create.index.queue", durable = "true"),
            exchange = @Exchange(
                    value = "item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC),
            key = {"item.insert", "item.update"}))
    public void save(Long id) throws IOException {
        if (id == null) {
            return;
        }
        searchService.save(id);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "delete.index.queue", durable = "true"),
            exchange = @Exchange(
                    value = "item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC),
            key = {"item.delete"}))
    public void delete(Long id) throws IOException {
        if (id == null) {
            return;
        }
        searchService.delete(id);
    }
}
