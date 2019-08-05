package cup.com.service.impl;

import cup.com.mapper.SpecGroupMapper;
import cup.com.mapper.SpecParamMapper;
import cup.com.pojo.SpecGroup;
import cup.com.pojo.SpecParam;
import cup.com.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/4
 * Time:20:04
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;


    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        return specGroupMapper.select(record);
    }

    @Override
    public List<SpecParam> queryParamsByGid(Long gid) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        return specParamMapper.select(record);
    }

    @Override
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return specParamMapper.select(record);
    }
}
