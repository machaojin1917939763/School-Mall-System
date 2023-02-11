package com.machaojin.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.domain.SpuInfoDesc;
import com.machaojin.mapper.SpuInfoDescMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.CommentReplayMapper;
import com.machaojin.domain.CommentReplay;
import com.machaojin.service.ICommentReplayService;

/**
 * 商品评价回复关系Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper, CommentReplay> implements ICommentReplayService
{
    @Autowired
    private CommentReplayMapper commentReplayMapper;

    /**
     * 查询商品评价回复关系
     * 
     * @param id 商品评价回复关系主键
     * @return 商品评价回复关系
     */
    @Override
    public CommentReplay selectCommentReplayById(Long id)
    {
        return commentReplayMapper.selectCommentReplayById(id);
    }

    /**
     * 查询商品评价回复关系列表
     * 
     * @param commentReplay 商品评价回复关系
     * @return 商品评价回复关系
     */
    @Override
    public List<CommentReplay> selectCommentReplayList(CommentReplay commentReplay)
    {
        return commentReplayMapper.selectCommentReplayList(commentReplay);
    }

    /**
     * 新增商品评价回复关系
     * 
     * @param commentReplay 商品评价回复关系
     * @return 结果
     */
    @Override
    public int insertCommentReplay(CommentReplay commentReplay)
    {
        return commentReplayMapper.insertCommentReplay(commentReplay);
    }

    /**
     * 修改商品评价回复关系
     * 
     * @param commentReplay 商品评价回复关系
     * @return 结果
     */
    @Override
    public int updateCommentReplay(CommentReplay commentReplay)
    {
        return commentReplayMapper.updateCommentReplay(commentReplay);
    }

    /**
     * 批量删除商品评价回复关系
     * 
     * @param ids 需要删除的商品评价回复关系主键
     * @return 结果
     */
    @Override
    public int deleteCommentReplayByIds(Long[] ids)
    {
        return commentReplayMapper.deleteCommentReplayByIds(ids);
    }

    /**
     * 删除商品评价回复关系信息
     * 
     * @param id 商品评价回复关系主键
     * @return 结果
     */
    @Override
    public int deleteCommentReplayById(Long id)
    {
        return commentReplayMapper.deleteCommentReplayById(id);
    }
}
