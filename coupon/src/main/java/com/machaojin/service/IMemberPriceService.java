package com.machaojin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.machaojin.domain.MemberPrice;

/**
 * 商品会员价格Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface IMemberPriceService extends IService<MemberPrice>
{
    /**
     * 查询商品会员价格
     * 
     * @param id 商品会员价格主键
     * @return 商品会员价格
     */
    public MemberPrice selectMemberPriceById(Long id);

    /**
     * 查询商品会员价格列表
     * 
     * @param memberPrice 商品会员价格
     * @return 商品会员价格集合
     */
    public List<MemberPrice> selectMemberPriceList(MemberPrice memberPrice);

    /**
     * 新增商品会员价格
     * 
     * @param memberPrice 商品会员价格
     * @return 结果
     */
    public int insertMemberPrice(MemberPrice memberPrice);

    /**
     * 修改商品会员价格
     * 
     * @param memberPrice 商品会员价格
     * @return 结果
     */
    public int updateMemberPrice(MemberPrice memberPrice);

    /**
     * 批量删除商品会员价格
     * 
     * @param ids 需要删除的商品会员价格主键集合
     * @return 结果
     */
    public int deleteMemberPriceByIds(Long[] ids);

    /**
     * 删除商品会员价格信息
     * 
     * @param id 商品会员价格主键
     * @return 结果
     */
    public int deleteMemberPriceById(Long id);
}
