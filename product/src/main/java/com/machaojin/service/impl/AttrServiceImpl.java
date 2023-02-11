package com.machaojin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.machaojin.basecode.Product;
import com.machaojin.domain.*;
import com.machaojin.exception.DataDeleteException;
import com.machaojin.mapper.AttrAttrgroupRelationMapper;
import com.machaojin.mapper.AttrGroupMapper;
import com.machaojin.mapper.CategoryMapper;
import com.machaojin.vo.AttrRelationVo;
import com.machaojin.vo.AttrReqVo;
import com.machaojin.vo.AttrVo;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.machaojin.mapper.AttrMapper;
import com.machaojin.service.IAttrService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 商品属性Service业务层处理
 * 
 * @author machaojin
 * @date 2022-10-05
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper,Attr> implements IAttrService
{
    @Autowired
    private AttrMapper attrMapper;


    @Autowired
    private AttrServiceImpl attrService;

    @Autowired
    private AttrGroupMapper attrGroupMapper;

    @Autowired
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private AttrGroupServiceImpl service;



    /**
     * 查询商品属性
     * 
     * @param attrId 商品属性主键
     * @return 商品属性
     */
    @Override
    public AttrReqVo selectAttrByAttrId(Long attrId)
    {
        //查询到属性主体
        Attr attr = attrMapper.selectAttrByAttrId(attrId);
        AttrReqVo attrReqVo = new AttrReqVo();
        BeanUtils.copyProperties(attr,attrReqVo);
        //根据属性主体查询分类，获取分类名字
        List<Category> categories = categoryMapper.selectCategoryList(null);
        Category category = null;
        for (Category category1 : categories){
            if (category1.getCatId().equals(attr.getCatelogId())){
                category = category1;
                break;
            }
        }
        if (category != null){
            //查询分类名字
            AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.selectOne(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrId, attr.getAttrId()));
            attrReqVo.setCatelogName(category.getName());
            //查询分类数据
            List<Long> path = new ArrayList<>();
            service.getPath(category,path,categories);
            //反转分类路径
            List<Long> newPath = new ArrayList<>();
            int size = path.size() - 1;
            while (path.size() != 0){
                newPath.add(path.remove(size--));
            }
            //插入分类路径
            attrReqVo.setCatelogPath(newPath);

            if (attrAttrgroupRelation != null && Product.ProductCode.PRODUCT_TYPE_BASE.getCode().equals(attr.getAttrType())){
                //  是规格参数才查询分组，否则就不查询
                   //查询分组名字
                   AttrGroup attrGroup = attrGroupMapper.selectOne(new LambdaQueryWrapper<AttrGroup>().eq(AttrGroup::getAttrGroupId, attrAttrgroupRelation.getAttrGroupId()));
                   if (attrGroup != null){
                       attrReqVo.setGroupName(attrGroup.getAttrGroupName());
                       //分组 ID
                       attrReqVo.setAttrGroupId(attrGroup.getAttrGroupName());
                   }
            }
        }
        return attrReqVo;
    }

    /**
     * 查询商品属性列表
     * 
     * @param attr 商品属性
     * @return 商品属性
     */
    @Override
    public List<Attr> selectAttrList(Attr attr)
    {
        return attrMapper.selectAttrList(attr);
    }

    /**
     * 新增商品属性
     * 
     * @param attr 商品属性
     * @return 结果
     */
    @Override
    public int insertAttr(Attr attr)
    {
        return attrMapper.insertAttr(attr);
    }

    /**
     * 修改商品属性
     * 
     * @param attr 商品属性
     * @return 结果
     */
    @Override
    public int updateAttr(AttrVo attr)
    {
        //基础属性才添加分组
        if (attr.getAttrType().equals(Product.ProductCode.PRODUCT_TYPE_BASE.getCode())){
            //同时修改关联表中的信息,把关联表中的分组 ID 修改成传递过来的数据
            AttrAttrgroupRelation attrAttrgroupRelation = new AttrAttrgroupRelation();
            attrAttrgroupRelation.setAttrId(attr.getAttrId());
            attrAttrgroupRelation.setAttrGroupId(Long.parseLong(attr.getAttrGroupId()));
            AttrAttrgroupRelation relation = attrAttrgroupRelationMapper.selectOne(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrId, attr.getAttrId()));
            //如果存在就修改，不存在就添加
            if (relation == null){
                List<AttrAttrgroupRelation> relations = new ArrayList<>();
                relations.add(attrAttrgroupRelation);
                attrAttrgroupRelationMapper.insertAttrAttrgroupRelation(relations);
            }else{
                attrAttrgroupRelationMapper.update(attrAttrgroupRelation,new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrId,attr.getAttrId()));
            }
        }
        Attr attr1 = new Attr();
        BeanUtils.copyProperties(attr,attr1);
        return attrMapper.updateAttr(attr1);
    }

    /**
     * 批量删除商品属性
     * 
     * @param attrIds 需要删除的商品属性主键
     * @return 结果
     */
    @Override
    public int deleteAttrByAttrIds(Long[] attrIds)
    {

        List<Long> longList = new ArrayList<>();
        Arrays.stream(attrIds).forEach((a)->{
            if (attrAttrgroupRelationMapper.selectList(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrId,a)) != null){
                longList.add(a);
            }
        });
        if (longList.size() == 0){
            throw new DataDeleteException("没有数据可以删除");
        }

        return attrMapper.deleteAttrByAttrIds(longList.toArray(new Long[0]));
    }

    /**
     * 删除商品属性信息
     * 
     * @param attrId 商品属性主键
     * @return 结果
     */
    @Override
    public int deleteAttrByAttrId(Long attrId)
    {
        if (attrAttrgroupRelationMapper.selectList(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrId,attrId)) != null){
            throw new DataDeleteException("数据关联，不能删除");
        }
        return attrMapper.deleteAttrByAttrId(attrId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertAttrVo(AttrVo attr) {
        //保存基本数据
        Attr attra = new Attr();
        BeanUtils.copyProperties(attr,attra);

        boolean i = this.save(attra);
        int i1 = 0;
        if (attr.getAttrType().equals(Product.ProductCode.PRODUCT_TYPE_BASE.getCode())){
            //保存关联关系
            AttrAttrgroupRelation attrAttrgroupRelation = new AttrAttrgroupRelation();

            attrAttrgroupRelation.setAttrGroupId(Long.parseLong(attr.getAttrGroupId()));
            attrAttrgroupRelation.setAttrId(attra.getAttrId());
            List<AttrAttrgroupRelation> relations = new ArrayList<>();
            relations.add(attrAttrgroupRelation);
             i1 = attrAttrgroupRelationMapper.insertAttrAttrgroupRelation(relations);
        }
        return i || (i1 > 0) ? 2 : 0;
    }

    @Override
    public Page<AttrReqVo> selectAttrList(Page<Attr> page, Map<String, String> params,String id,String type) {
        Page<AttrReqVo> voPage = new Page<>();

        //判断是销售属性还是基础属性
        if (Product.ProductCode.PRODUCT_TYPE_BASE.getMsg().equalsIgnoreCase(type)){
            type = Product.ProductCode.PRODUCT_TYPE_BASE.getCode().toString();
        }else {
            type = Product.ProductCode.PRODUCT_TYPE_SALE.getCode().toString();
        }

        //分开查询销售属性和基础属性，基础属性就有分组信息，而销售属性就没有
        LambdaQueryWrapper<Attr> lambdaQueryWrapper = new LambdaQueryWrapper<Attr>().eq(Attr::getAttrType,type);


        lambdaQueryWrapper.like(!StringUtils.isEmpty((String) params.get("key")),Attr::getAttrName,(String) params.get("key"));

        //如果前端传递过来的 ID 是 0 ，就查询全部
        if (Product.ProductCode.PRODUCT_TYPE_FIND_TYPE.getCode().toString().equalsIgnoreCase(id)){
            this.page(page,lambdaQueryWrapper);
        }else{
            //sidx: 'id',//排序字段
            //   order: 'asc/desc',//排序方式

            String sidx = params.get("sidx");
            String order = params.get("order");

            //条件查询
            lambdaQueryWrapper.eq(Attr::getCatelogId,id);

            lambdaQueryWrapper.orderBy(!StringUtils.isEmpty(sidx) && !StringUtils.isEmpty(order), "ASC".equals(order),Attr::getAttrId);
            this.page(page,lambdaQueryWrapper);

        }

        BeanUtils.copyProperties(page,voPage,"records");
        List<Attr> records = page.getRecords();


        String finalType = type;


        List<AttrReqVo> collect = records.stream().map((a) -> {

            AttrReqVo attrReqVo = new AttrReqVo();
            BeanUtils.copyProperties(a, attrReqVo);
            //"catelogName": "手机/数码/手机", //所属分类名字
            //			"groupName": "主体", //所属分组名字
            Category category = categoryMapper.selectCategoryByCatId(a.getCatelogId());
            if (category != null){
                attrReqVo.setCatelogName(category.getName());
                //是基础属性就设置分组，否则就不设置
                if (Product.ProductCode.PRODUCT_TYPE_BASE.getCode().toString().equalsIgnoreCase(finalType)){
                    System.out.println("-=-=-==-=-=-=" + finalType);
                    AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.selectOne(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrId, a.getAttrId()));
                    if (attrAttrgroupRelation != null){
                        AttrGroup attrGroup = attrGroupMapper.selectOne(new LambdaQueryWrapper<AttrGroup>().eq(AttrGroup::getAttrGroupId, attrAttrgroupRelation.getAttrGroupId()));
                        attrReqVo.setGroupName(attrGroup.getAttrGroupName());

                    }
                }
            }
            return attrReqVo;

        }).collect(Collectors.toList());

        voPage.setRecords(collect);
        return voPage;
    }

    /**
     * 根据分组 ID 查询与之关联的商品属性
     * @param attrgroupId
     * @return
     */
    @Override
    public List<Attr> selectAttrListAll(Long attrgroupId) {

        List<AttrAttrgroupRelation> attrAttrgroupRelations = attrAttrgroupRelationMapper.selectList(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrGroupId, attrgroupId));
        if (attrAttrgroupRelations == null || attrAttrgroupRelations.size() == 0){
            return new ArrayList<>();
        }
        List<Long> collect = attrAttrgroupRelations.stream().map(AttrAttrgroupRelation::getAttrId).collect(Collectors.toList());

        return attrService.listByIds(collect);
    }
}
