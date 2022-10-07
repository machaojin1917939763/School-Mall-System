package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.MemberReceiveAddress;

/**
 * 会员收货地址Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface MemberReceiveAddressMapper 
{
    /**
     * 查询会员收货地址
     * 
     * @param id 会员收货地址主键
     * @return 会员收货地址
     */
    public MemberReceiveAddress selectMemberReceiveAddressById(Long id);

    /**
     * 查询会员收货地址列表
     * 
     * @param memberReceiveAddress 会员收货地址
     * @return 会员收货地址集合
     */
    public List<MemberReceiveAddress> selectMemberReceiveAddressList(MemberReceiveAddress memberReceiveAddress);

    /**
     * 新增会员收货地址
     * 
     * @param memberReceiveAddress 会员收货地址
     * @return 结果
     */
    public int insertMemberReceiveAddress(MemberReceiveAddress memberReceiveAddress);

    /**
     * 修改会员收货地址
     * 
     * @param memberReceiveAddress 会员收货地址
     * @return 结果
     */
    public int updateMemberReceiveAddress(MemberReceiveAddress memberReceiveAddress);

    /**
     * 删除会员收货地址
     * 
     * @param id 会员收货地址主键
     * @return 结果
     */
    public int deleteMemberReceiveAddressById(Long id);

    /**
     * 批量删除会员收货地址
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberReceiveAddressByIds(Long[] ids);
}
