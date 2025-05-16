package com.base.common.core.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义约束的
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {
    public boolean primarykey() default false;
    public boolean allowNull() default true;
    public boolean unique() default false;
    public boolean autoincrement() default false;
}