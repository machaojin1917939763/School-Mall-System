package com.machaojin.vo;

import lombok.Data;

/**
 * @author machaojin
 * @version 1.0
 * @time 2022/11/25 20:08 星期五
 */
@Data
public class AttrRelationVo {
    private String attrId;
    private String attrGroupId;

    public AttrRelationVo(String attrId, String attrGroupId) {
        this.attrId = attrId;
        this.attrGroupId = attrGroupId;
    }

    public AttrRelationVo() {
    }

}
