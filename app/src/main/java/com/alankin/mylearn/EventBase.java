package com.alankin.mylearn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by alankin on 2017/1/9.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {

    Class listenerType();//接口类对象

    String listenerSetter();//设置监听的方法名

    String methodName();//接口的方法名
}
