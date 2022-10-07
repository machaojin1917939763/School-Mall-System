package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.MemberCollectSpu;

/**
 * 会员收藏的商品Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface MemberCollectSpuMapper 
{
    /**
     * 查询会员收藏的商品
     * 
     * @param id 会员收藏的商品主键
     * @return 会员收藏的商品
     */
    public MemberCollectSpu selectMemberCollectSpuById(Long id);

    /**
     * 查询会员收藏的商品列表
     * 
     * @param memberCollectSpu 会员收藏的商品
     * @return 会员收藏的商品集合
     */
    public List<MemberCollectSpu> selectMemberCollectSpuList(MemberCollectSpu memberCollectSpu);

    /**
     * 新增会员收藏的商品
     * 
     * @param memberCollectSpu 会员收藏的商品
     * @return 结果
     */
    public int insertMemberCollectSpu(MemberCollectSpu memberCollectSpu);

    /**
     * 修改会员收藏的商品
     * 
     * @param memberCollectSpu 会员收藏的商品
     * @return 结果
     */
    public int updateMemberCollectSpu(MemberCollectSpu memberCollectSpu);

    /**
     * 删除会员收藏的商品
     * 
     * @param id 会员收藏的商品主键
     * @return 结果
     */
    public int deleteMemberCollectSpuById(Long id);

    /**
     * 批量删除会员收藏的商品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberCollectSpuByIds(Long[] ids);
}
