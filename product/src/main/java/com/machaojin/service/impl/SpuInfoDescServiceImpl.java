package com.machaojin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.SpuInfoDescMapper;
import com.machaojin.domain.SpuInfoDesc;
import com.machaojin.service.ISpuInfoDescService;

/**
 * spu信息介绍Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class SpuInfoDescServiceImpl implements ISpuInfoDescService 
{
    @Autowired
    private SpuInfoDescMapper spuInfoDescMapper;

    /**
     * 查询spu信息介绍
     * 
     * @param spuId spu信息介绍主键
     * @return spu信息介绍
     */
    @Override
    public SpuInfoDesc selectSpuInfoDescBySpuId(Long spuId)
    {
        return spuInfoDescMapper.selectSpuInfoDescBySpuId(spuId);
    }

    /**
     * 查询spu信息介绍列表
     * 
     * @param spuInfoDesc spu信息介绍
     * @return spu信息介绍
     */
    @Override
    public List<SpuInfoDesc> selectSpuInfoDescList(SpuInfoDesc spuInfoDesc)
    {
        return spuInfoDescMapper.selectSpuInfoDescList(spuInfoDesc);
    }

    /**
     * 新增spu信息介绍
     * 
     * @param spuInfoDesc spu信息介绍
     * @return 结果
     */
    @Override
    public int insertSpuInfoDesc(SpuInfoDesc spuInfoDesc)
    {
        return spuInfoDescMapper.insertSpuInfoDesc(spuInfoDesc);
    }

    /**
     * 修改spu信息介绍
     * 
     * @param spuInfoDesc spu信息介绍
     * @return 结果
     */
    @Override
    public int updateSpuInfoDesc(SpuInfoDesc spuInfoDesc)
    {
        return spuInfoDescMapper.updateSpuInfoDesc(spuInfoDesc);
    }

    /**
     * 批量删除spu信息介绍
     * 
     * @param spuIds 需要删除的spu信息介绍主键
     * @return 结果
     */
    @Override
    public int deleteSpuInfoDescBySpuIds(Long[] spuIds)
    {
        return spuInfoDescMapper.deleteSpuInfoDescBySpuIds(spuIds);
    }

    /**
     * 删除spu信息介绍信息
     * 
     * @param spuId spu信息介绍主键
     * @return 结果
     */
    @Override
    public int deleteSpuInfoDescBySpuId(Long spuId)
    {
        return spuInfoDescMapper.deleteSpuInfoDescBySpuId(spuId);
    }
}
