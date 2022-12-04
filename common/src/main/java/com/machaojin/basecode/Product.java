package com.machaojin.basecode;

import io.swagger.models.auth.In;

/**
 * @author machaojin
 * @version 1.0
 * @time 2022/11/25 16:46 星期五
 */
public class Product {

    public enum ProductCode{
        /**
         * 销售属性
         */
        PRODUCT_TYPE_SALE(0,"sale"),
        /**
         * 基础属性
         */
        PRODUCT_TYPE_BASE(1,"base"),

        /**
         * 查询类型，是否是全部
         */
        PRODUCT_TYPE_FIND_TYPE(0);


        ProductCode(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        ProductCode(Integer code) {
            this.code = code;
        }

        private Integer code;
        private String msg;

        ProductCode() {

        }
        ProductCode(String msg) {
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
