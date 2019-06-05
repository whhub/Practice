package com.mj.scada.log;

import java.lang.annotation.*;

/*
 *Author:ZouHeng
 *Des:自定义注解类
 *Date:2019-06-01 16:11
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog
{
    String value() default "";
}
