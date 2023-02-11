package com.machaojin.basecode;

public class Ware {
    public enum PurchaseDetailCode{
        /**
         * 新建
         */
        CREATE(0),
        /**
         * 已分配
         */
        ASSIGNED(1),

        /**
         * 正在采购
         */
        PURCHASING(2),

        /**
         * 采购失败
         */
        ERROR(4),

        /**
         * 采购完成
         */
        FISHED(3);


        PurchaseDetailCode(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        PurchaseDetailCode(Integer code) {
            this.code = code;
        }

        private Integer code;
        private String msg;

        PurchaseDetailCode() {

        }
        PurchaseDetailCode(String msg) {
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

    public enum PurchaseCode{
        /**
         * 新建
         */
        CREATE(0),
        /**
         * 已分配
         */
        ASSIGNED(1),

        /**
         * 已领取
         */
        PURCHASING(2),

        /**
         * 有异常
         */
        ERROR(4),

        /**
         * 已完成
         */
        FISHED(3);


        PurchaseCode(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        PurchaseCode(Integer code) {
            this.code = code;
        }

        private Integer code;
        private String msg;

        PurchaseCode() {

        }
        PurchaseCode(String msg) {
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
