package com.machaojin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.domain.*;
import com.machaojin.domain.Attr;
import com.machaojin.dto.SkuReductionTo;
import com.machaojin.dto.SpuBoundsDto;
import com.machaojin.feign.CouponFeignService;
import com.machaojin.service.*;
import com.machaojin.vo.*;
import com.ruoyi.common.utils.DateUtils;
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
        return spuInfoMapper.updateSpuInfo(spuInfo);
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
