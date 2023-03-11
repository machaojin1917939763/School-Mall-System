package com.machaojin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttrGroupRelationVo implements Serializable {

    //"attrId":1,"attrGroupId":2
    private Long attrId;
    private Long attrGroupId;
}
