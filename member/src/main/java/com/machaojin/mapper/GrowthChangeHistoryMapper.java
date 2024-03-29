package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.GrowthChangeHistory;

/**
 * 成长值变化历史记录Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface GrowthChangeHistoryMapper 
{
    /**
     * 查询成长值变化历史记录
     * 
     * @param id 成长值变化历史记录主键
     * @return 成长值变化历史记录
     */
    public GrowthChangeHistory selectGrowthChangeHistoryById(Long id);

    /**
     * 查询成长值变化历史记录列表
     * 
     * @param growthChangeHistory 成长值变化历史记录
     * @return 成长值变化历史记录集合
     */
    public List<GrowthChangeHistory> selectGrowthChangeHistoryList(GrowthChangeHistory growthChangeHistory);

    /**
     * 新增成长值变化历史记录
     * 
     * @param growthChangeHistory 成长值变化历史记录
     * @return 结果
     */
    public int insertGrowthChangeHistory(GrowthChangeHistory growthChangeHistory);

    /**
     * 修改成长值变化历史记录
     * 
     * @param growthChangeHistory 成长值变化历史记录
     * @return 结果
     */
    public int updateGrowthChangeHistory(GrowthChangeHistory growthChangeHistory);

    /**
     * 删除成长值变化历史记录
     * 
     * @param id 成长值变化历史记录主键
     * @return 结果
     */
    public int deleteGrowthChangeHistoryById(Long id);

    /**
     * 批量删除成长值变化历史记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGrowthChangeHistoryByIds(Long[] ids);
}
