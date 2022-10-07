package com.machaojin.service;

import java.util.List;
import com.machaojin.domain.SpuImages;

/**
 * spu图片Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface ISpuImagesService 
{
    /**
     * 查询spu图片
     * 
     * @param id spu图片主键
     * @return spu图片
     */
    public SpuImages selectSpuImagesById(Long id);

    /**
     * 查询spu图片列表
     * 
     * @param spuImages spu图片
     * @return spu图片集合
     */
    public List<SpuImages> selectSpuImagesList(SpuImages spuImages);

    /**
     * 新增spu图片
     * 
     * @param spuImages spu图片
     * @return 结果
     */
    public int insertSpuImages(SpuImages spuImages);

    /**
     * 修改spu图片
     * 
     * @param spuImages spu图片
     * @return 结果
     */
    public int updateSpuImages(SpuImages spuImages);

    /**
     * 批量删除spu图片
     * 
     * @param ids 需要删除的spu图片主键集合
     * @return 结果
     */
    public int deleteSpuImagesByIds(Long[] ids);

    /**
     * 删除spu图片信息
     * 
     * @param id spu图片主键
     * @return 结果
     */
    public int deleteSpuImagesById(Long id);
}
