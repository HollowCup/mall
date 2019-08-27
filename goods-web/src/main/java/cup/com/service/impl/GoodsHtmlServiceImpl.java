package cup.com.service.impl;

import cup.com.service.GoodsHtmlService;
import cup.com.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/27
 * Time:16:16
 */
@Service
public class GoodsHtmlServiceImpl implements GoodsHtmlService {

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private GoodsService goodsService;

    @Override
    public void createHtml(Long spuId) {
        // 初始化运行上下文
        Context context = new Context();
        // 设置数据模型
        context.setVariables(goodsService.loadData(spuId));

        PrintWriter printWriter = null;
        try {
            // 把静态文件生成到服务器本地
            File file = new File("E:\\software\\nginx-1.15.0\\temp\\" + spuId + ".html");
            printWriter = new PrintWriter(file);
            // 执行页面静态化方法
            templateEngine.process("item", context, printWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

    }
}
