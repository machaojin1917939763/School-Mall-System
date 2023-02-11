package com.machaojin.service.impl;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.domain.Attr;
import com.machaojin.domain.AttrAttrgroupRelation;
import com.machaojin.domain.Category;
import com.machaojin.exception.DataDeleteException;
import com.machaojin.mapper.AttrAttrgroupRelationMapper;
import com.machaojin.mapper.AttrMapper;
import com.machaojin.vo.AttrRelationVo;
import com.machaojin.vo.AttrVo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.AttrGroupMapper;
import com.machaojin.domain.AttrGroup;
import com.machaojin.service.IAttrGroupService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 属性分组Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Slf4j
@Service

public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper,AttrGroup> implements IAttrGroupService
{
    @Autowired
    private AttrGroupMapper attrGroupMapper;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Autowired
    private AttrMapper attrMapper;

    /**
     * 查询属性分组
     * 
     * @param attrGroupId 属性分组主键
     * @return 属性分组
     */
    @Override
    public AttrGroup selectAttrGroupByAttrGroupId(Long attrGroupId)
    {
        //包括查询所有的 category 然后查询传入的当前路径
        //查出所有的分类数据
        List<Category> list = categoryService.selectCategoryList(null);

        //查询出需要的分类数据
        AttrGroup attrGroup = attrGroupMapper.selectAttrGroupByAttrGroupId(attrGroupId);

        //路径
        List<Long> path = new ArrayList<>();

        for (Category category : list) {
            if (attrGroup.getCatelogId().equals(category.getCatId())){
                getPath(category,path,list);
                break;
            }
        }


        Collections.reverse(path);
        log.info("路径为：{}",path);
        attrGroup.setCatelogPath(path);

        return attrGroup;
    }



    public void getPath(Category attrGroupId,List<Long> path,List<Category> list){

        if (attrGroupId.getParentCid() == 0){
            path.add(attrGroupId.getCatId());
            return;
        }
        path.add(attrGroupId.getCatId());
        for (Category category : list) {
            if (attrGroupId.getParentCid().equals(category.getCatId())) {
                getPath(category,path,list);
            }
        }
    }


    /**
     * 查询属性分组列表
     * 
     * @param attrGroup 属性分组
     * @return 属性分组
     */
    @Override
    public List<AttrGroup> selectAttrGroupList(AttrGroup attrGroup)
    {
        return attrGroupMapper.selectAttrGroupList(attrGroup);
    }

    /**
     * 新增属性分组
     * 
     * @param attrGroup 属性分组
     * @return 结果
     */
    @Override
    public int insertAttrGroup(AttrGroup attrGroup)
    {
        return attrGroupMapper.insertAttrGroup(attrGroup);
    }

    /**
     * 修改属性分组
     * 
     * @param attrGroup 属性分组
     * @return 结果
     */
    @Override
    public int updateAttrGroup(AttrGroup attrGroup)
    {
        return attrGroupMapper.updateAttrGroup(attrGroup);
    }

    /**
     * 批量删除属性分组
     * 
     * @param attrGroupIds 需要删除的属性分组主键
     * @return 结果
     */
    @Override
    public int deleteAttrGroupByAttrGroupIds(Long[] attrGroupIds)
    {
        List<Long> longList = new ArrayList<>();
        Arrays.stream(attrGroupIds).forEach((a)->{
            if (attrAttrgroupRelationMapper.selectList(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrGroupId,a)) != null){
                longList.add(a);
            }
        });
        if (longList.size() == 0){
            throw new DataDeleteException("没有数据可以删除");
        }

        return attrGroupMapper.deleteAttrGroupByAttrGroupIds(longList.toArray(new Long[0]));
    }

    /**
     * 删除属性分组信息
     * 
     * @param attrGroupId 属性分组主键
     * @return 结果
     */
    @Override
    public int deleteAttrGroupByAttrGroupId(Long attrGroupId)
    {
        if (attrAttrgroupRelationMapper.selectList(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrGroupId,attrGroupId)) != null){
            throw new DataDeleteException("数据关联，不能删除");
        }
        return attrGroupMapper.deleteAttrGroupByAttrGroupId(attrGroupId);
    }

    /**
     * 查询属性分组列表
     * @param params {
     *    page: 1,//当前页码
     *    limit: 10,//每页记录数
     *    sidx: 'id',//排序字段
     *    order: 'asc/desc',//排序方式
     *    key: '华为'//检索关键字
     * }
     * @param  catalogId id
     * @return 属性分组集合
     */
    @Override
    public Page<AttrGroup> selectAttrGroupListForList(Map<String, Object> params, String catalogId) {

        LambdaQueryWrapper<AttrGroup> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        Page<AttrGroup> page = new Page<>(Long.parseLong((String) params.get("page")),Long.parseLong((String) params.get("limit")));

        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(catalogId),AttrGroup::getCatelogId,catalogId);

        boolean flag = StringUtils.isNotEmpty((String) params.get("sidx")) && "asc".equals(params.get("sidx"));
        if (params.get("key") == null){
            return this.page(page,lambdaQueryWrapper);
        }
        if ("0".equals(catalogId)){
            return this.page(page);
        }
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty((String) params.get("key")),AttrGroup::getCatelogId,params.get("key"))

                .and((a)->{
                    a.eq(AttrGroup::getAttrGroupId,params.get("key")).or().like(AttrGroup::getDescript,params.get("key"));
                    })
                 .orderBy(StringUtils.isNotEmpty((String) params.get("sidx")),flag,AttrGroup::getAttrGroupId);

        return this.page(page,lambdaQueryWrapper);
    }

    /**
     * 根据属性的分组信息删除属性关系表里面的数据
     * @param attrGroupIds
     * @return
     */
    @Override
    public int deleteFor(AttrRelationVo[] attrGroupIds) {

        //转转成集合

        List<AttrRelationVo> collect = Arrays.stream(attrGroupIds).map((attrRelationVo -> {
            AttrRelationVo attrRelation = new AttrRelationVo();
            BeanUtils.copyProperties(attrRelationVo, attrRelation);
            return attrRelation;
        })).collect(Collectors.toList());
        return attrAttrgroupRelationMapper.deleteFor(collect);
    }

    /**
     * 查找没有被属性分组绑定的基本属性
     * @param attrGroupId
     * @param params
     * @return
     */
    @Override
    public List<Attr> listFor(String attrGroupId, Map<Object, Object> params) {
        //2、查询所有的attr
        List<Attr> allAttr = attrMapper.selectList(new LambdaQueryWrapper<Attr>().like(StringUtils.isNotEmpty(params.get("key").toString()),Attr::getAttrName,params.get("key")));
        if (allAttr == null || allAttr.size() == 0){
            return new ArrayList<>();
        }
        HashMap<Long,Attr> attrs = new HashMap<>();

        allAttr.forEach(attr -> attrs.put(attr.getAttrId(),attr));

        //3、查询attr_group和attr的关系表attr_group_relation表中的所有的attrGroupId数据
        List<AttrAttrgroupRelation> attrAttrgroupRelations = attrAttrgroupRelationMapper.selectList(null);
        if (attrAttrgroupRelations == null || attrAttrgroupRelations.size() == 0){
            return allAttr;
        }
        List<Long> inRelationAttrId = attrAttrgroupRelations.stream().map(AttrAttrgroupRelation::getAttrId).collect(Collectors.toList());
        //4、比对attr所有的数据和查询出关联的数据，没在的就返回
        List<Attr> newAttr = new ArrayList<>();
        allAttr.forEach((a) -> {
            if(!inRelationAttrId.contains(a.getAttrId())){
                newAttr.add(attrs.get(a.getAttrId()));
            }
        });
        if (params.get("limit") == null || params.get("key") == null){
            return newAttr;
        }
        //物理分页
        int pageSize = Integer.parseInt(params.get("limit").toString());
        int page = Integer.parseInt(params.get("page").toString());

        if(pageSize >= newAttr.size()){
            return newAttr;
        }else{
            List<Attr> attrList = new ArrayList<>();
            int size = newAttr.size();
            int pageFlag = 0;
            while (size > pageSize){
                size -= pageSize;
                pageFlag++;
            }
            if(pageFlag <= page){
                for (int i = newAttr.size() - 1 - size; i < newAttr.size(); i++) {
                    attrList.add(newAttr.get(i));
                }
            }else{
                int start = page * pageSize - 1;
                for (int i = 0; i < pageSize; i++) {
                    attrList.add(newAttr.get(start + i));
                }
            }
            return attrList;
        }


    }

    /**
     * 根据分类id查询出所有的分组信息，然后再查出每个分组信息关联的所有属性
     * @param categoryId
     * @return
     */
    @Override
    public List<AttrGroup> listAllAttr(Long categoryId) {
        //查询出所有的attrgroup
        List<AttrGroup> attrGroups = attrGroupMapper.selectList(new LambdaQueryWrapper<AttrGroup>().eq(AttrGroup::getCatelogId, categoryId));
        if (attrGroups != null && attrGroups.size() > 0){
            attrGroups.forEach((attrGroup -> {
                List<AttrVo> withAttrGroup = new ArrayList<>();
                List<AttrAttrgroupRelation> relations = attrAttrgroupRelationMapper.selectList(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrGroupId, attrGroup.getAttrGroupId()));
                relations.forEach((relation) -> {
                    AttrVo attrVo = new AttrVo();
                    BeanUtils.copyProperties(attrMapper.selectAttrByAttrId(relation.getAttrId()),attrVo);
                    withAttrGroup.add(attrVo);
                });

                attrGroup.setAttrs(withAttrGroup);
            }));
        }
        return attrGroups;
    }
}
