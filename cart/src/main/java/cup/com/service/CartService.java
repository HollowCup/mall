package cup.com.service;

import cup.com.pojo.Cart;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/3
 * Time:17:42
 */
public interface CartService {
    void addCart(Cart cart);

    List<Cart> queryCarts();

    void updateNum(Cart cart);
}
