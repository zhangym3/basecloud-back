package com.base.common.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义字段类型
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnType {
    public String type() default "varchar(64)";
    public String name() default "";
    public String sequence() default "";

    public long minvalue() default 0;
    public long start() default 0;
    public Constraints constraints() default @Constraints;

    public boolean id() default false;
    public boolean key() default false;
    public boolean logic() default false;
}