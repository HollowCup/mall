package cup.com.controller;

import cup.com.service.GoodsHtmlService;
import cup.com.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/25
 * Time:23:35
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsHtmlService goodsHtmlService;

    @GetMapping("item/{id}.html")
    public String toItemPage(@PathVariable("id")Long id,Model model){

        Map<String, Object> map = goodsService.loadData(id);

        model.addAllAttributes(map);

        goodsHtmlService.createHtml(id);
        return "item";
    }
}
