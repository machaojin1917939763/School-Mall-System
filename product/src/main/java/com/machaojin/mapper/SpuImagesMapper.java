package com.machaojin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machaojin.domain.SpuImages;
import com.machaojin.domain.SpuInfo;

/**
 * spu图片Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface SpuImagesMapper extends BaseMapper<SpuImages>
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
     * 删除spu图片
     * 
     * @param id spu图片主键
     * @return 结果
     */
    public int deleteSpuImagesById(Long id);

    /**
     * 批量删除spu图片
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpuImagesByIds(Long[] ids);
}
