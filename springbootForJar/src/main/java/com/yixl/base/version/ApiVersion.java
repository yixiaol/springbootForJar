package com.yixl.base.version;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 定义api请求的版本
 * @author yixl
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    /**
     * 标识版本号
     * @return
     */
    int value();
}