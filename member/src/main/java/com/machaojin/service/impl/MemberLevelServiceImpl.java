package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.MemberLevelMapper;
import com.machaojin.domain.MemberLevel;
import com.machaojin.service.IMemberLevelService;

/**
 * 会员等级Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class MemberLevelServiceImpl implements IMemberLevelService 
{
    @Autowired
    private MemberLevelMapper memberLevelMapper;

    /**
     * 查询会员等级
     * 
     * @param id 会员等级主键
     * @return 会员等级
     */
    @Override
    public MemberLevel selectMemberLevelById(Long id)
    {
        return memberLevelMapper.selectMemberLevelById(id);
    }

    /**
     * 查询会员等级列表
     * 
     * @param memberLevel 会员等级
     * @return 会员等级
     */
    @Override
    public List<MemberLevel> selectMemberLevelList(MemberLevel memberLevel)
    {
        return memberLevelMapper.selectMemberLevelList(memberLevel);
    }

    /**
     * 新增会员等级
     * 
     * @param memberLevel 会员等级
     * @return 结果
     */
    @Override
    public int insertMemberLevel(MemberLevel memberLevel)
    {
        return memberLevelMapper.insertMemberLevel(memberLevel);
    }

    /**
     * 修改会员等级
     * 
     * @param memberLevel 会员等级
     * @return 结果
     */
    @Override
    public int updateMemberLevel(MemberLevel memberLevel)
    {
        return memberLevelMapper.updateMemberLevel(memberLevel);
    }

    /**
     * 批量删除会员等级
     * 
     * @param ids 需要删除的会员等级主键
     * @return 结果
     */
    @Override
    public int deleteMemberLevelByIds(Long[] ids)
    {
        return memberLevelMapper.deleteMemberLevelByIds(ids);
    }

    /**
     * 删除会员等级信息
     * 
     * @param id 会员等级主键
     * @return 结果
     */
    @Override
    public int deleteMemberLevelById(Long id)
    {
        return memberLevelMapper.deleteMemberLevelById(id);
    }
}
