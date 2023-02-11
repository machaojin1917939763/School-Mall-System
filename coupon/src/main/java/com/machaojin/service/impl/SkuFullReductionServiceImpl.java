package com.machaojin.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.machaojin.domain.SkuLadder;
import com.machaojin.dto.MemberPrice;
import com.machaojin.dto.SkuReductionTo;
import com.machaojin.mapper.MemberPriceMapper;
import com.machaojin.mapper.SkuLadderMapper;
import com.machaojin.service.IMemberPriceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.SkuFullReductionMapper;
import com.machaojin.domain.SkuFullReduction;
import com.machaojin.service.ISkuFullReductionService;

/**
 * 商品满减信息Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class SkuFullReductionServiceImpl implements ISkuFullReductionService 
{
    @Autowired
    private SkuFullReductionMapper skuFullReductionMapper;

    @Autowired
    private SkuLadderMapper skuLadderMapper;

    @Autowired
    private IMemberPriceService memberPriceService;

    /**
     * 查询商品满减信息
     * 
     * @param id 商品满减信息主键
     * @return 商品满减信息
     */
    @Override
    public SkuFullReduction selectSkuFullReductionById(Long id)
    {
        return skuFullReductionMapper.selectSkuFullReductionById(id);
    }

    /**
     * 查询商品满减信息列表
     * 
     * @param skuFullReduction 商品满减信息
     * @return 商品满减信息
     */
    @Override
    public List<SkuFullReduction> selectSkuFullReductionList(SkuFullReduction skuFullReduction)
    {
        return skuFullReductionMapper.selectSkuFullReductionList(skuFullReduction);
    }

    /**
     * 新增商品满减信息
     * 
     * @param skuFullReduction 商品满减信息
     * @return 结果
     */
    @Override
    public int insertSkuFullReduction(SkuFullReduction skuFullReduction)
    {
        return skuFullReductionMapper.insertSkuFullReduction(skuFullReduction);
    }

    /**
     * 修改商品满减信息
     * 
     * @param skuFullReduction 商品满减信息
     * @return 结果
     */
    @Override
    public int updateSkuFullReduction(SkuFullReduction skuFullReduction)
    {
        return skuFullReductionMapper.updateSkuFullReduction(skuFullReduction);
    }

    /**
     * 批量删除商品满减信息
     * 
     * @param ids 需要删除的商品满减信息主键
     * @return 结果
     */
    @Override
    public int deleteSkuFullReductionByIds(Long[] ids)
    {
        return skuFullReductionMapper.deleteSkuFullReductionByIds(ids);
    }

    /**
     * 删除商品满减信息信息
     * 
     * @param id 商品满减信息主键
     * @return 结果
     */
    @Override
    public int deleteSkuFullReductionById(Long id)
    {
        return skuFullReductionMapper.deleteSkuFullReductionById(id);
    }

    @Override
    public int saveReductions(SkuReductionTo skuReductionTo) {
        //保存商品阶梯价格
        SkuLadder skuLadder = new SkuLadder();
        skuLadder.setSkuId(skuReductionTo.getSkuId());
        skuLadder.setFullCount(Long.parseLong(skuReductionTo.getFullCount() + ""));
        skuLadder.setDiscount(skuReductionTo.getDiscount());
        skuLadder.setAddOther(skuReductionTo.getCountStatus());
        if (skuReductionTo.getFullCount() > 0){
            skuLadderMapper.insertSkuLadder(skuLadder);
        }
        //保存商品满减信息
        SkuFullReduction skuFullReduction = new SkuFullReduction();
        BeanUtils.copyProperties(skuReductionTo,skuFullReduction);
        if (skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) > 0){
            this.insertSkuFullReduction(skuFullReduction);
        }
        //保存商品阶梯价格
        List<MemberPrice> memberPrice = skuReductionTo.getMemberPrice();
        if (memberPrice != null && memberPrice.size() > 0){
            List<com.machaojin.domain.MemberPrice> collect = memberPrice.stream().map((member) -> {
                com.machaojin.domain.MemberPrice memberPrice1 = new com.machaojin.domain.MemberPrice();
                memberPrice1.setSkuId(skuReductionTo.getSkuId());
                memberPrice1.setMemberLevelId(member.getId());
                memberPrice1.setMemberLevelName(member.getName());
                memberPrice1.setMemberPrice(member.getPrice());
                memberPrice1.setAddOther(1);
                return memberPrice1;
            }).filter((item) -> {
               return item.getMemberPrice().compareTo(new BigDecimal("0")) > 0;
            }).collect(Collectors.toList());
            boolean b = memberPriceService.saveBatch(collect);
        }
        return 1;
    }
}
