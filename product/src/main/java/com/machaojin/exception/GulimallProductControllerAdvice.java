package com.machaojin.exception;

import com.ruoyi.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author machaojin
 *
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.machaojin.controller")
public class GulimallProductControllerAdvice {


    /**
     * 数据删除异常
     */
    @ExceptionHandler(DataDeleteException.class)
    public AjaxResult data(DataDeleteException e){
        log.error(e.getMessage());
        //获取捕获的异常
        return AjaxResult.error(BaseCode.DATA_DELETE_EXCEPTION.getMsg());
    }


    /**
     * 用于拦截前端传过来的不安全数据，后面做校验的数据
     * @param exception 异常
     * @return 返回错误代码
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult handlerException(MethodArgumentNotValidException exception){
        log.error("发生了异常{},异常类型是{}",exception.getMessage(),exception.getClass());
        //获取捕获的异常
        BindingResult bindingResult = exception.getBindingResult();
        Map<String,String> map = new HashMap<>();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        allErrors.forEach((all) -> {
            map.put(all.getObjectName(),all.getDefaultMessage());
        });
        return AjaxResult.error(BaseCode.VALID_EXCEPTION.getMsg(),map);
    }

    /**
     * 处理所有的未知错误
     * @param throwable 所有异常的父类
     * @return 返回错误代码
     */
    @ExceptionHandler(value = Throwable.class)
    public AjaxResult allException(Throwable throwable){
        log.error("未知错误{}，错误类型{},错在哪{}}",throwable.getMessage(),throwable.getClass(),throwable.getStackTrace());
        return AjaxResult.error(BaseCode.UNKNOWN_EXCEPTION.getCode(), BaseCode.UNKNOWN_EXCEPTION.getMsg());
    }

}
