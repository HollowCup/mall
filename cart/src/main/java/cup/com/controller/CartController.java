package cup.com.controller;

import cup.com.pojo.Cart;
import cup.com.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/3
 * Time:17:40
 */
@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("addCart")
    @ApiOperation(value = "添加购物车", notes = "添加购物车")
    public ResponseEntity<Void> addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("queryCarts")
    @ApiOperation(value = "查询购物车", notes = "查询购物车")
    public ResponseEntity<List<Cart>> queryCarts(){
        List<Cart> carts = cartService.queryCarts();
        if(CollectionUtils.isEmpty(carts)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carts);
    }

    @PutMapping
    public ResponseEntity<Void> updateNum(@RequestBody Cart cart){
        cartService.updateNum(cart);
        return ResponseEntity.noContent().build();
    }
}
