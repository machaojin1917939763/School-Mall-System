package com.machaojin.exception;

/**
 * @author machaojin
 */

public enum BaseCode {
    /**
     *
     */
    VALID_EXCEPTION(1001,"数据校验错误"),
    /**
     *
     */
    UNKNOWN_EXCEPTION(1000,"系统未知错误"),

    DATA_DELETE_EXCEPTION(1002,"删除数据失败");

    private int code;

    private String msg;

    BaseCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
