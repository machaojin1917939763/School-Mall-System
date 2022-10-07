package com.machaojin.mapper;

import java.util.List;
import com.machaojin.domain.SpuComment;

/**
 * 商品评价Mapper接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@org.apache.ibatis.annotations.Mapper
public interface SpuCommentMapper 
{
    /**
     * 查询商品评价
     * 
     * @param id 商品评价主键
     * @return 商品评价
     */
    public SpuComment selectSpuCommentById(Long id);

    /**
     * 查询商品评价列表
     * 
     * @param spuComment 商品评价
     * @return 商品评价集合
     */
    public List<SpuComment> selectSpuCommentList(SpuComment spuComment);

    /**
     * 新增商品评价
     * 
     * @param spuComment 商品评价
     * @return 结果
     */
    public int insertSpuComment(SpuComment spuComment);

    /**
     * 修改商品评价
     * 
     * @param spuComment 商品评价
     * @return 结果
     */
    public int updateSpuComment(SpuComment spuComment);

    /**
     * 删除商品评价
     * 
     * @param id 商品评价主键
     * @return 结果
     */
    public int deleteSpuCommentById(Long id);

    /**
     * 批量删除商品评价
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpuCommentByIds(Long[] ids);
}
