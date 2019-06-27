package com.iwant.doilikePro.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {
		//测试 地址 http://localhost:你配置的端口/druid/login.html  原文地址: https://www.cnblogs.com/feiyangbahu/p/9842363.html
	 	@Bean
	    public ServletRegistrationBean druidServlet() {
	 		// 主要实现web监控的配置处理
	 		//表示进行druid监控的配置处理操作
	        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
	        //白名单
	        servletRegistrationBean.addInitParameter("allow", "127.0.0.1,129.168.1.11");
	        //黑名单[只是个例子]
	        servletRegistrationBean.addInitParameter("deny", "129.168.1.12");
	        //用户名
	        servletRegistrationBean.addInitParameter("loginUsername", "root");
	        //密码
	        servletRegistrationBean.addInitParameter("loginPassword", "root");
	        //是否可以重置数据源
	        servletRegistrationBean.addInitParameter("resetEnable", "false");
	        
	        return servletRegistrationBean;

	    }
	    @Bean    //监控
	    public FilterRegistrationBean filterRegistrationBean(){
	        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
	        filterRegistrationBean.setFilter(new WebStatFilter());
	        filterRegistrationBean.addUrlPatterns("/*");//所有请求进行监控处理
	        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");//排除
	        return filterRegistrationBean;
	    }
	    @Bean
	    @ConfigurationProperties(prefix = "spring.datasource")
	    public DataSource druidDataSource() {
	        return new DruidDataSource();
	    }
}
