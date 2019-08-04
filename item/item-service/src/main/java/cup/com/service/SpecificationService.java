package cup.com.service;

import cup.com.pojo.SpecGroup;
import cup.com.pojo.SpecParam;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/4
 * Time:20:04
 */
public interface SpecificationService {
    List<SpecGroup> queryGroupsByCid(Long cid);

    List<SpecParam> queryParamsByGid(Long gid);
}
