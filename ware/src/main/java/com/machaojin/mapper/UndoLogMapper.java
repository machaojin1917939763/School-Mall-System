package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.UndoLog;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@org.apache.ibatis.annotations.Mapper
public interface UndoLogMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UndoLog selectUndoLogById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param undoLog 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UndoLog> selectUndoLogList(UndoLog undoLog);

    /**
     * 新增【请填写功能名称】
     * 
     * @param undoLog 【请填写功能名称】
     * @return 结果
     */
    public int insertUndoLog(UndoLog undoLog);

    /**
     * 修改【请填写功能名称】
     * 
     * @param undoLog 【请填写功能名称】
     * @return 结果
     */
    public int updateUndoLog(UndoLog undoLog);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUndoLogById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUndoLogByIds(Long[] ids);
}
