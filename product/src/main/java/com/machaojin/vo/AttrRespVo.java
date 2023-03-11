package com.machaojin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttrRespVo extends AttrVo implements Serializable {
    /**
     * 			"catelogName": "手机/数码/手机", //所属分类名字
     * 			"groupName": "主体", //所属分组名字
     */
    private String catelogName;
    private String groupName;

    private Long[] catelogPath;
}
