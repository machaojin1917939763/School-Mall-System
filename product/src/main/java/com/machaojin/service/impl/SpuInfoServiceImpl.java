package com.machaojin.service.impl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.basecode.Product;
import com.machaojin.domain.*;
import com.machaojin.domain.Attr;
import com.machaojin.dto.SkuReductionTo;
import com.machaojin.dto.SpuBoundsDto;
import com.machaojin.dto.WareSkuDto;
import com.machaojin.dto.es.SpuUpDto;
import com.machaojin.exception.BaseCode;
import com.machaojin.exception.SkuNotStockException;
import com.machaojin.feign.CouponFeignService;
import com.machaojin.feign.SearchFeignClients;
import com.machaojin.feign.WareFeignService;
import com.machaojin.service.*;
import com.machaojin.vo.*;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.SpuInfoMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * spu信息Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper,SpuInfo> implements ISpuInfoService {

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private ISpuInfoDescService spuInfoDescService;

    @Autowired
    private ISpuImagesService spuImagesService;

    @Autowired
    private IProductAttrValueService productAttrValueService;

    @Autowired
    private IAttrService attrService;

    @Autowired
    private ISkuInfoService skuInfoService;

    @Autowired
    private ISkuImagesService skuImagesService;

    @Autowired
    private ISkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private CouponFeignService couponFeignService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private WareFeignService wareFeignService;

    @Autowired
    private SearchFeignClients searchFeignClients;

    /**
     * 查询spu信息
     * 
     * @param id spu信息主键
     * @return spu信息
     */
    @Override
    public SpuInfo selectSpuInfoById(Long id)
    {
        return spuInfoMapper.selectSpuInfoById(id);
    }

    /**
     * 查询spu信息列表
     * 
     * @param spuInfo spu信息
     * @param key
     * @return spu信息
     */
    @Override
    public List<SpuInfo> selectSpuInfoList(SpuInfo spuInfo, String key)
    {
        return spuInfoMapper.selectSpuInfoList(spuInfo,key);
    }

    /**
     * 新增spu信息
     * 
     * @param spuInfo spu信息
     * @return 结果
     */
    @Override
    public int insertSpuInfo(SpuInfo spuInfo)
    {
        spuInfo.setCreateTime(DateUtils.getNowDate());
        return spuInfoMapper.insertSpuInfo(spuInfo);
    }

    /**
     * 修改spu信息
     * 
     * @param spuInfo spu信息
     * @return 结果
     */
    @Override
    public int updateSpuInfo(SpuInfo spuInfo)
    {
        spuInfo.setUpdateTime(DateUtils.getNowDate());
        //查询出信息，发送给es，进行保存
        //1、查询pms_sku_info表，根据spuId查询
        List<SkuInfo> skuInfos = skuInfoService.list(new LambdaQueryWrapper<SkuInfo>().eq(SkuInfo::getSpuId, spuInfo.getId()));
        List<Long> skuIds = skuInfos.stream().map((SkuInfo::getSkuId)).collect(Collectors.toList());
        //远程查询库存
        AjaxResult hasStockInfos = wareFeignService.getHasStockInfos(skuIds);
        HashMap<Long,Boolean> map = (HashMap<Long, Boolean>) hasStockInfos.get("map");
        //查询所有的销售属性attrs
        List<Attr> list = attrService.list(new LambdaQueryWrapper<Attr>().eq(Attr::getAttrType,"0"));

        HashSet<Long> attrNames = new HashSet<>();
        list.forEach((a) -> {
            attrNames.add(a.getAttrId());
        });

        List<SpuUpDto> spuUpDtos = skuInfos.stream().map((sku) -> {
            SpuUpDto spuUpDto = new SpuUpDto();
            BeanUtils.copyProperties(sku, spuUpDto);
            //解决字段不一样问题
            spuUpDto.setSkuPrice(sku.getPrice());
            spuUpDto.setSkuImg(sku.getSkuDefaultImg());
            //hasScore、hasStock、brandId、catalogId、brandName、BrandImg、catalogName、attrs
            //根据CategoryId查询出Catalog信息
            Category category = categoryService.selectCategoryByCatId(sku.getCatalogId());
            spuUpDto.setCatalogName(category.getName());
            //根据品牌id查询品牌信息
            Brand brand = brandService.selectBrandByBrandId(sku.getBrandId());
            spuUpDto.setBrandImg(brand.getLogo());
            spuUpDto.setBrandName(brand.getName());
            //设置商品热度
            spuUpDto.setHotScore(0L);
            //看商品是否有库存，如果有，就能正常上架，如果没有就不能上架,需要发送远程请求
            /*
            在远程调用接口查询库存的时候，如果有多个数据，一起发送key8节省很多时间
             */
            if (map == null){
                spuUpDto.setHasStock(false);
            }else{
                Boolean aBoolean = map.get(sku.getSkuId());
                if (aBoolean == null){
                    spuUpDto.setHasStock(false);
                }
                spuUpDto.setHasStock(aBoolean);
            }
            //查询出所有关联的属性
            List<SkuSaleAttrValue> attrValues = skuSaleAttrValueService.list(new LambdaQueryWrapper<SkuSaleAttrValue>().eq(SkuSaleAttrValue::getSkuId, sku.getSkuId()));
            List<SpuUpDto.Attrs> attrs = new ArrayList<>();
            for (SkuSaleAttrValue value : attrValues) {
                if (attrNames.contains(value.getAttrId())){
                    SpuUpDto.Attrs attr = new SpuUpDto.Attrs();
                    BeanUtils.copyProperties(value,attr);
                    attrs.add(attr);
                }
            }
            spuUpDto.setAttrs(attrs);
            return spuUpDto;
        }).collect(Collectors.toList());

        //发送远程请求请求es保存数据
        boolean up = false;
        try {
           up  = searchFeignClients.productUp(spuUpDtos);
        } catch (IOException e) {
            log.error("上架失败");
            e.printStackTrace();
        }
        log.error(up + "");
        if(!up){
            spuInfo.setPublishStatus(Product.ProductCode.SPU_UP.getCode());
            spuInfo.setUpdateTime(new Date());
            return spuInfoMapper.updateSpuInfo(spuInfo);
        }else {
            return 0;
        }
    }

    /**
     * 批量删除spu信息
     * 
     * @param ids 需要删除的spu信息主键
     * @return 结果
     */
    @Override
    public int deleteSpuInfoByIds(Long[] ids)
    {
        return spuInfoMapper.deleteSpuInfoByIds(ids);
    }

    /**
     * 删除spu信息信息
     * 
     * @param id spu信息主键
     * @return 结果
     */
    @Override
    public int deleteSpuInfoById(Long id)
    {
        return spuInfoMapper.deleteSpuInfoById(id);
    }

    @Override
    public int saveProduct(SpuSaveVo spuSaveVo) {
        //1、保存spu基本信息
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCreateTime(new Date());
        spuInfo.setUpdateTime(new Date());
        BeanUtils.copyProperties(spuSaveVo,spuInfo);
        this.insertSpuInfo(spuInfo);
        //2、保存spu的描述图片
        List<String> decript = spuSaveVo.getDecript();
        SpuInfoDesc spuInfoDesc = new SpuInfoDesc();
        spuInfoDesc.setSpuId(spuInfo.getId());
        spuInfoDesc.setDecript(String.join(",",decript));
        spuInfoDescService.insertSpuInfoDesc(spuInfoDesc);
        //3、保存spu的图片集
        List<String> images = spuSaveVo.getImages();
        if (images != null && images.size() > 0){
            List<SpuImages> collect = images.stream().map((image) -> {
                SpuImages spuImages = new SpuImages();
                spuImages.setSpuId(spuInfo.getId());
                spuImages.setImgUrl(image);
                return spuImages;
            }).collect(Collectors.toList());
            spuImagesService.saveBatch(collect);
        }
        //4、保存spu的规格参数
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        if (baseAttrs != null && baseAttrs.size() > 0){
            List<ProductAttrValue> collect = baseAttrs.stream().map((attr) -> {
                ProductAttrValue productAttrValue = new ProductAttrValue();
                productAttrValue.setAttrId(attr.getAttrId());
                productAttrValue.setAttrValue(attr.getAttrValues());
                productAttrValue.setQuickShow(attr.getShowDesc());
                productAttrValue.setSpuId(spuInfo.getId());
                Attr id = attrService.getById(attr.getAttrId());
                productAttrValue.setAttrName(id.getAttrName());
                return productAttrValue;
            }).collect(Collectors.toList());
            productAttrValueService.saveBatch(collect);
        }
        //5、保存spu的积分信息
        Bounds bounds = spuSaveVo.getBounds();
        SpuBoundsDto spuBoundsDto = new SpuBoundsDto();
        BeanUtils.copyProperties(bounds, spuBoundsDto);
        spuBoundsDto.setSpuId(spuInfo.getId());
        couponFeignService.saveBounds(spuBoundsDto);

        //6、保存当前spu对应的所有sku信息
        //6.1、sku基本信息
        List<Skus> skus = spuSaveVo.getSkus();
        if (skus != null && skus.size() > 0){
            skus.forEach((sku) -> {
                List<Images> images1 = sku.getImages();
                String defaultImage = "";
                if (images1 != null && images1.size() > 0){
                    for (Images img : images1) {
                        if (img.getDefaultImg() == 1){
                            defaultImage = img.getImgUrl();
                        }
                    }
                }
                SkuInfo skuInfo = new SkuInfo();
                BeanUtils.copyProperties(sku,skuInfo);
                skuInfo.setBrandId(spuInfo.getBrandId());
                skuInfo.setCatalogId(spuInfo.getCatalogId());
                skuInfo.setSaleCount(0L);
                skuInfo.setSpuId(spuInfo.getId());
                skuInfo.setSkuDefaultImg(defaultImage);
                skuInfoService.save(skuInfo);
                //6.1、sku图片信息
                Long skuId = skuInfo.getSkuId();
                log.error(skuId.toString());
                List<SkuImages> collect = sku.getImages().stream().filter((img) -> {
                    return img.getImgUrl() != null;
                }).map((img) -> {
                    SkuImages skuImages = new SkuImages();
                    skuImages.setSkuId(skuId);
                    skuImages.setImgUrl(img.getImgUrl());
                    skuImages.setDefaultImg(Long.parseLong(img.getDefaultImg() + ""));
                    return skuImages;
                }).collect(Collectors.toList());
                skuImagesService.saveBatch(collect);
                //6.1、sku销售属性信息
                List<com.machaojin.vo.Attr> attr = sku.getAttr();
                if (attr != null && attr.size() > 0){
                    List<SkuSaleAttrValue> attrValues = attr.stream().map((sale) -> {
                        SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
                        BeanUtils.copyProperties(sale, skuSaleAttrValue);
                        skuSaleAttrValue.setSkuId(skuId);
                        return skuSaleAttrValue;
                    }).collect(Collectors.toList());
                    skuSaleAttrValueService.saveBatch(attrValues);
                }
                //6.1、sku优惠、满减等信息
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(sku,skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                couponFeignService.saveReduction(skuReductionTo);
            });
        }
        return 1;
    }
}
