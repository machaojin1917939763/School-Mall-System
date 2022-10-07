package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.UndoLogMapper;
import com.machaojin.domain.UndoLog;
import com.machaojin.service.IUndoLogService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-06
 */
@Service
public class UndoLogServiceImpl implements IUndoLogService 
{
    @Autowired
    private UndoLogMapper undoLogMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UndoLog selectUndoLogById(Long id)
    {
        return undoLogMapper.selectUndoLogById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param undoLog 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UndoLog> selectUndoLogList(UndoLog undoLog)
    {
        return undoLogMapper.selectUndoLogList(undoLog);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param undoLog 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUndoLog(UndoLog undoLog)
    {
        return undoLogMapper.insertUndoLog(undoLog);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param undoLog 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUndoLog(UndoLog undoLog)
    {
        return undoLogMapper.updateUndoLog(undoLog);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUndoLogByIds(Long[] ids)
    {
        return undoLogMapper.deleteUndoLogByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUndoLogById(Long id)
    {
        return undoLogMapper.deleteUndoLogById(id);
    }
}
