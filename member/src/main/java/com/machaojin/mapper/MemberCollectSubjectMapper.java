package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.MemberCollectSubject;

/**
 * 会员收藏的专题活动Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface MemberCollectSubjectMapper 
{
    /**
     * 查询会员收藏的专题活动
     * 
     * @param id 会员收藏的专题活动主键
     * @return 会员收藏的专题活动
     */
    public MemberCollectSubject selectMemberCollectSubjectById(Long id);

    /**
     * 查询会员收藏的专题活动列表
     * 
     * @param memberCollectSubject 会员收藏的专题活动
     * @return 会员收藏的专题活动集合
     */
    public List<MemberCollectSubject> selectMemberCollectSubjectList(MemberCollectSubject memberCollectSubject);

    /**
     * 新增会员收藏的专题活动
     * 
     * @param memberCollectSubject 会员收藏的专题活动
     * @return 结果
     */
    public int insertMemberCollectSubject(MemberCollectSubject memberCollectSubject);

    /**
     * 修改会员收藏的专题活动
     * 
     * @param memberCollectSubject 会员收藏的专题活动
     * @return 结果
     */
    public int updateMemberCollectSubject(MemberCollectSubject memberCollectSubject);

    /**
     * 删除会员收藏的专题活动
     * 
     * @param id 会员收藏的专题活动主键
     * @return 结果
     */
    public int deleteMemberCollectSubjectById(Long id);

    /**
     * 批量删除会员收藏的专题活动
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberCollectSubjectByIds(Long[] ids);
}
