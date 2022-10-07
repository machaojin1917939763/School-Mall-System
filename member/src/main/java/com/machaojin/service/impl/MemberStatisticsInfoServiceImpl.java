package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.MemberStatisticsInfoMapper;
import com.machaojin.domain.MemberStatisticsInfo;
import com.machaojin.service.IMemberStatisticsInfoService;

/**
 * 会员统计信息Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class MemberStatisticsInfoServiceImpl implements IMemberStatisticsInfoService 
{
    @Autowired
    private MemberStatisticsInfoMapper memberStatisticsInfoMapper;

    /**
     * 查询会员统计信息
     * 
     * @param id 会员统计信息主键
     * @return 会员统计信息
     */
    @Override
    public MemberStatisticsInfo selectMemberStatisticsInfoById(Long id)
    {
        return memberStatisticsInfoMapper.selectMemberStatisticsInfoById(id);
    }

    /**
     * 查询会员统计信息列表
     * 
     * @param memberStatisticsInfo 会员统计信息
     * @return 会员统计信息
     */
    @Override
    public List<MemberStatisticsInfo> selectMemberStatisticsInfoList(MemberStatisticsInfo memberStatisticsInfo)
    {
        return memberStatisticsInfoMapper.selectMemberStatisticsInfoList(memberStatisticsInfo);
    }

    /**
     * 新增会员统计信息
     * 
     * @param memberStatisticsInfo 会员统计信息
     * @return 结果
     */
    @Override
    public int insertMemberStatisticsInfo(MemberStatisticsInfo memberStatisticsInfo)
    {
        return memberStatisticsInfoMapper.insertMemberStatisticsInfo(memberStatisticsInfo);
    }

    /**
     * 修改会员统计信息
     * 
     * @param memberStatisticsInfo 会员统计信息
     * @return 结果
     */
    @Override
    public int updateMemberStatisticsInfo(MemberStatisticsInfo memberStatisticsInfo)
    {
        return memberStatisticsInfoMapper.updateMemberStatisticsInfo(memberStatisticsInfo);
    }

    /**
     * 批量删除会员统计信息
     * 
     * @param ids 需要删除的会员统计信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberStatisticsInfoByIds(Long[] ids)
    {
        return memberStatisticsInfoMapper.deleteMemberStatisticsInfoByIds(ids);
    }

    /**
     * 删除会员统计信息信息
     * 
     * @param id 会员统计信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberStatisticsInfoById(Long id)
    {
        return memberStatisticsInfoMapper.deleteMemberStatisticsInfoById(id);
    }
}
