package cup.com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cup.com.bo.SpuBo;
import cup.com.mapper.*;
import cup.com.pojo.*;
import cup.com.service.CategoryService;
import cup.com.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description：
 * Author:TengLu
 * Date:2019/8/4
 * Time:23:48
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    SpuMapper spuMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SpuDetailMapper spuDetailMapper;
    @Autowired
    SkuMapper skuMapper;
    @Autowired
    StockMapper stockMapper;

    @Override
    public PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows) {

        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        // 添加查询条件
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        // 添加上下架的过滤条件
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }

        // 添加分页
        PageHelper.startPage(page, rows);
        // 执行查询，获取spu
        List<Spu> spus = spuMapper.selectByExample(example);
        PageInfo<Spu> pageInfo = new PageInfo<>(spus);

        // spu转换成spuBO集合
        List<SpuBo> spuBos = spus.stream().map(spu -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);
            // 查询品牌名称
            Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
            if(brand!=null){
                spuBo.setBname(brand.getName());
            }
            // 查询分类名称
            List<String> nameList = categoryService.queryNameByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(nameList, "/"));
            return spuBo;
        }).collect(Collectors.toList());
        // 返回PageResult<SpuBo>
        return new PageResult<>(pageInfo.getTotal(), Long.valueOf(pageInfo.getPages()), spuBos);
    }

    @Override
    @Transactional
    public void saveGoods(SpuBo spuBo) {
        // 先新增spu
        spuBo.setId(null);
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        spuMapper.insertSelective(spuBo);

        // 再去新增spuDetail
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        spuDetailMapper.insertSelective(spuDetail);

        // 新增sku、新增stock
        saveSkuAndStock(spuBo);

    }

    @Override
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        return spuDetailMapper.selectByPrimaryKey(spuId);
    }

    @Override
    public List<Sku> querySkusBySpuId(Long spuId) {
        Sku record = new Sku();
        record.setSpuId(spuId);
        List<Sku> skus = skuMapper.select(record);
        skus.forEach(sku -> {
            Stock stock = stockMapper.selectByPrimaryKey(sku.getId());
            sku.setStock(stock.getStock());
        });
        return skus;
    }

    @Override
    public void updateGoods(SpuBo spuBo) {
        // 根据spuUd查询要删除的sku
        Sku record = new Sku();
        record.setSpuId(spuBo.getId());
        List<Sku> skus = skuMapper.select(record);
        skus.forEach(sku -> {
            // 删除stock
            stockMapper.deleteByPrimaryKey(sku.getId());
        });
        // 删除sku
        Sku sku = new Sku();
        sku.setSpuId(spuBo.getId());
        skuMapper.delete(sku);
        // 新增sku、新增stock
        saveSkuAndStock(spuBo);
        // 更新spu和spuDetail
        spuBo.setValid(null);
        spuBo.setSaleable(null);
        spuBo.setCreateTime(null);
        spuBo.setLastUpdateTime(new Date());
        spuMapper.updateByPrimaryKeySelective(spuBo);
        spuDetailMapper.updateByPrimaryKeySelective(spuBo.getSpuDetail());
    }

    private void saveSkuAndStock(SpuBo spuBo) {
        spuBo.getSkus().forEach(sku -> {
            // 新增sku
            sku.setId(null);
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            skuMapper.insertSelective(sku);
            // 新增stock
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockMapper.insertSelective(stock);
        });
    }
}
