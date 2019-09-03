package cup.com.service.impl;

import cup.com.client.GoodsClient;
import cup.com.interceptor.LoginInterceptor;
import cup.com.pojo.Cart;
import cup.com.pojo.Sku;
import cup.com.pojo.UserInfo;
import cup.com.service.CartService;
import cup.com.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/3
 * Time:17:48
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private GoodsClient goodsClient;

    private static String KEY_PREFIX = "user:cart:";


    @Override
    public void addCart(Cart cart) {

        // 获取用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        // 查询购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());

        String key = cart.getSkuId().toString();
        Integer num = cart.getNum();

        // 判断当前的商品是否在购物车中
        if (hashOperations.hasKey(cart.getSkuId().toString())) {
            // 在，更新数量
            String cartJson = hashOperations.get(key).toString();
            cart = JsonUtils.parse(cartJson, Cart.class);
            cart.setNum(cart.getNum() + num);
        } else {
            // 不在，新增购物车
            Sku sku = goodsClient.querySkuBySkuId(cart.getSkuId());
            cart.setUserId(userInfo.getId());
            cart.setTitle(sku.getTitle());
            cart.setOwnSpec(sku.getOwnSpec());
            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            cart.setPrice(sku.getPrice());
        }
        hashOperations.put(key, JsonUtils.serialize(cart));

    }

    @Override
    public List<Cart> queryCarts() {
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        if (redisTemplate.hasKey(KEY_PREFIX + userInfo.getId())) {
            return null;
        }
        // 获取用户的购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());
        // 获取购物车Map中所有Cart值的集合
        List<Object> cartsJson = hashOperations.values();
        // 如果购物车集合为空，直接返回null
        if (CollectionUtils.isEmpty(cartsJson)) {
            return null;
        }
        return cartsJson.stream().map(cartJson -> JsonUtils.parse(cartsJson.toString(), Cart.class)).collect(Collectors.toList());
    }

    @Override
    public void updateNum(Cart cart) {
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        if (redisTemplate.hasKey(KEY_PREFIX + userInfo.getId())) {
            return ;
        }
        Integer num = cart.getNum();
        // 获取用户的购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());
        String cartJson = hashOperations.get(cart.getSkuId().toString()).toString();
        cart = JsonUtils.parse(cartJson, Cart.class);
        cart.setNum(num);
        hashOperations.put(cart.getSkuId().toString(),JsonUtils.serialize(cart));
    }
}
