package com.yixl.base.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yixl.aliyun.Aliyun;
import com.yixl.base.interceptor.ApiInterceptor;
import com.yixl.base.version.CustomRequestMappingHandlerMapping;

@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new ApiInterceptor());
	}

	@Bean
	public ApiInterceptor interceptor() {
		return new ApiInterceptor();
	}

	/**
	 * 取出 application.properties配置文件中的value
	 */
	@Value("${appKey}")
	private String appKey;
	@Value("${appSecret}")
	private String appSecret;
	@Value("${bucket}")
	private String bucket;
	@Value("${endPoint}")
	private String endPoint;

	/**
	 * 注入bean 
	 * 使用方式如下 ： 
	 * @Autowired
	 * private Aliyun aliyun;
	 * 
	 * 
	 * @return
	 */
	@Bean
	public Aliyun aliyun() {
		return Aliyun.options().setAppKey(appKey).setAppSecret(appSecret)
				.setBucket(bucket).setEndPoint(endPoint).build();
	}

	/**
	 * 接口增加版本控制
	 */
	@Override
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
		handlerMapping.setOrder(0);
		handlerMapping.setInterceptors(getInterceptors());
		return handlerMapping;
	}

	/**
	 * 接入fastjson
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		/*
		 * 1.需要先定义一个convert转换消息的对象； 2.添加fastjson的配置信息，比如是否要格式化返回的json数据
		 * 3.在convert中添加配置信息 4.将convert添加到converters中
		 */
		// 1.定义一个convert转换消息对象
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		// 2.添加fastjson的配置信息，比如：是否要格式化返回json数据
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastConverter);
	}
}
