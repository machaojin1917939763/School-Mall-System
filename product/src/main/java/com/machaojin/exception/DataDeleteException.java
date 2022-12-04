package com.machaojin.exception;

/**
 * @author machaojin
 * @version 1.0
 * @time 2022/11/21 16:14 星期一
 * 数据删除异常
 */
public class DataDeleteException extends RuntimeException{

    public DataDeleteException(String message) {
        super(message);

    }
}
