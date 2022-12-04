package com.machaojin.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author machaojin
 * 自定义的校验器
 */
public class ListValuesConstraintValidator implements ConstraintValidator<ListValue,Integer> {

    private final Set<Integer> flag = new HashSet<>();

    /**
     * 初始化方法
     * @param constraintAnnotation 获取到注解里的值
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        for (int value : values) {
            flag.add(value);
        }
    }

    /**
     * 判断是否校验成功
     * @param integer 字段提交过来的信息，需要校验的字段
     * @param constraintValidatorContext 整个校验的上下文信息
     * @return 返回校验结果
     */
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return flag.contains(integer);
    }
}
