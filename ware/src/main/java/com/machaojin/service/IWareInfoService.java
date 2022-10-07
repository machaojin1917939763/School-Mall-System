package com.machaojin.service;

import java.util.List;
import com.machaojin.domain.WareInfo;

/**
 * 仓库信息Service接口
 * 
 * @author machaojin
 * @date 2022-10-06
 */

public interface IWareInfoService 
{
    /**
     * 查询仓库信息
     * 
     * @param id 仓库信息主键
     * @return 仓库信息
     */
    public WareInfo selectWareInfoById(Long id);

    /**
     * 查询仓库信息列表
     * 
     * @param wareInfo 仓库信息
     * @return 仓库信息集合
     */
    public List<WareInfo> selectWareInfoList(WareInfo wareInfo);

    /**
     * 新增仓库信息
     * 
     * @param wareInfo 仓库信息
     * @return 结果
     */
    public int insertWareInfo(WareInfo wareInfo);

    /**
     * 修改仓库信息
     * 
     * @param wareInfo 仓库信息
     * @return 结果
     */
    public int updateWareInfo(WareInfo wareInfo);

    /**
     * 批量删除仓库信息
     * 
     * @param ids 需要删除的仓库信息主键集合
     * @return 结果
     */
    public int deleteWareInfoByIds(Long[] ids);

    /**
     * 删除仓库信息信息
     * 
     * @param id 仓库信息主键
     * @return 结果
     */
    public int deleteWareInfoById(Long id);
}
