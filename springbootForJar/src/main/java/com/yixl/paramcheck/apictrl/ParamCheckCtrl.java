package com.yixl.paramcheck.apictrl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yixl.paramcheck.entity.AuthorizeIn;

/**
 * 表单校验
 * @author yixl
 *
 */
@RestController
public class ParamCheckCtrl {
	@RequestMapping("authorize")
    public void authorize(@Valid AuthorizeIn authorize, BindingResult result){
        if(result.hasFieldErrors()){
            List<FieldError> errorList = result.getFieldErrors();
            //通过断言抛出参数不合法的异常   -> Lambda 表达式（java8 特性）
            errorList.stream().forEach(item -> Assert.isTrue(false,item.getDefaultMessage()));
        }
    }
}
