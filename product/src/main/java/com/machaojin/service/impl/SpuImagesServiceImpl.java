package com.machaojin.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.domain.SpuInfoDesc;
import com.machaojin.mapper.SpuInfoDescMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.SpuImagesMapper;
import com.machaojin.domain.SpuImages;
import com.machaojin.service.ISpuImagesService;

/**
 * spu图片Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesMapper, SpuImages> implements ISpuImagesService
{
    @Autowired
    private SpuImagesMapper spuImagesMapper;

    /**
     * 查询spu图片
     * 
     * @param id spu图片主键
     * @return spu图片
     */
    @Override
    public SpuImages selectSpuImagesById(Long id)
    {
        return spuImagesMapper.selectSpuImagesById(id);
    }

    /**
     * 查询spu图片列表
     * 
     * @param spuImages spu图片
     * @return spu图片
     */
    @Override
    public List<SpuImages> selectSpuImagesList(SpuImages spuImages)
    {
        return spuImagesMapper.selectSpuImagesList(spuImages);
    }

    /**
     * 新增spu图片
     * 
     * @param spuImages spu图片
     * @return 结果
     */
    @Override
    public int insertSpuImages(SpuImages spuImages)
    {
        return spuImagesMapper.insertSpuImages(spuImages);
    }

    /**
     * 修改spu图片
     * 
     * @param spuImages spu图片
     * @return 结果
     */
    @Override
    public int updateSpuImages(SpuImages spuImages)
    {
        return spuImagesMapper.updateSpuImages(spuImages);
    }

    /**
     * 批量删除spu图片
     * 
     * @param ids 需要删除的spu图片主键
     * @return 结果
     */
    @Override
    public int deleteSpuImagesByIds(Long[] ids)
    {
        return spuImagesMapper.deleteSpuImagesByIds(ids);
    }

    /**
     * 删除spu图片信息
     * 
     * @param id spu图片主键
     * @return 结果
     */
    @Override
    public int deleteSpuImagesById(Long id)
    {
        return spuImagesMapper.deleteSpuImagesById(id);
    }
}
