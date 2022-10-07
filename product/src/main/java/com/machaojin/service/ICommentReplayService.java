package com.machaojin.service;

import java.util.List;
import com.machaojin.domain.CommentReplay;

/**
 * 商品评价回复关系Service接口
 * 
 * @author machaojin
 * @date 2022-10-05
 */

public interface ICommentReplayService 
{
    /**
     * 查询商品评价回复关系
     * 
     * @param id 商品评价回复关系主键
     * @return 商品评价回复关系
     */
    public CommentReplay selectCommentReplayById(Long id);

    /**
     * 查询商品评价回复关系列表
     * 
     * @param commentReplay 商品评价回复关系
     * @return 商品评价回复关系集合
     */
    public List<CommentReplay> selectCommentReplayList(CommentReplay commentReplay);

    /**
     * 新增商品评价回复关系
     * 
     * @param commentReplay 商品评价回复关系
     * @return 结果
     */
    public int insertCommentReplay(CommentReplay commentReplay);

    /**
     * 修改商品评价回复关系
     * 
     * @param commentReplay 商品评价回复关系
     * @return 结果
     */
    public int updateCommentReplay(CommentReplay commentReplay);

    /**
     * 批量删除商品评价回复关系
     * 
     * @param ids 需要删除的商品评价回复关系主键集合
     * @return 结果
     */
    public int deleteCommentReplayByIds(Long[] ids);

    /**
     * 删除商品评价回复关系信息
     * 
     * @param id 商品评价回复关系主键
     * @return 结果
     */
    public int deleteCommentReplayById(Long id);
}
