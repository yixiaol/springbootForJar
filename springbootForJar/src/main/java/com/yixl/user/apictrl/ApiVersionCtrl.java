package com.yixl.user.apictrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yixl.aliyun.Aliyun;
import com.yixl.base.version.ApiVersion;
import com.yixl.paramcheck.entity.AuthorizeIn;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("{version}")
@ApiVersion(1)
public class ApiVersionCtrl {

    @Value("${server.port}")
    String port;
    
    @Autowired
    private Aliyun aliyun;
    
    /**
     * http://127.0.0.1:8081/yixlapi/v1/hello
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String home(String name) {
        return "hi "+name+",i am from port:" +port+aliyun;
    }

    
    

    
//    部署到容器中 需要 继承 SpringBootServletInitializer 覆盖configure方法
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(ApiVersionCtrl.class);
//    }
}