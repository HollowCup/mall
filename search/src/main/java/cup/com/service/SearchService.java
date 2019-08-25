package cup.com.service;

import cup.com.pojo.*;

import java.io.IOException;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/9
 * Time:11:15
 */
public interface SearchService {
    Goods buildGoods(Spu spu) throws IOException;

    SearchResult search(SearchRequest request);
}
