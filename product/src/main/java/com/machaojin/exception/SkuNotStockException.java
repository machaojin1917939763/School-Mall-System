package com.machaojin.exception;

/**
 * 商品没有库存
 */
public class SkuNotStockException extends RuntimeException{
    public SkuNotStockException(String message) {
        super(message);
    }
}
