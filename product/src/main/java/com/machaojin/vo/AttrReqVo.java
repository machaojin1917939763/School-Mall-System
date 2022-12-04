package com.machaojin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author machaojin
 * @version 1.0
 * @time 2022/11/24 19:59 星期四
 * "catelogName": "手机/数码/手机", //所属分类名字
 * 			"groupName": "主体", //所属分组名字
 */

public class AttrReqVo extends AttrVo{
    private String catelogName;
    private String groupName;
    private List<Long> catelogPath;





    public String getCatelogName() {
        return catelogName;
    }

    public void setCatelogName(String catelogName) {
        this.catelogName = catelogName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Long> getCatelogPath() {
        return catelogPath;
    }

    public void setCatelogPath(List<Long> catelogPath) {
        this.catelogPath = catelogPath;
    }

    @Override
    public String toString() {
        return "AttrReqVo{" +
                "catelogName='" + catelogName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", catelogPath=" + catelogPath +
                '}' + super.toString();
    }
}
