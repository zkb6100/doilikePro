package com.iwant.doilikePro.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
	
	// 参考 来自 https://blog.csdn.net/taojin12/article/details/88343990
	
	/**
     * 注入自定义的 Realm
     * @return MyRealm
     */
   /* @Bean
    public MyRealm myAuthRealm() {
        MyRealm myRealm = new MyRealm();
        logger.info("====myRealm注册完成=====");
        return myRealm;
    }*/

	  /**
     * 注入安全管理器
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        // 将自定义 Realm 加进来
       // DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(myAuthRealm());
    	  DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        logger.info("====securityManager注册完成====");
        return securityManager;
    }

	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		// 定义 shiroFactoryBean
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		// 是指自定义的 securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		//设置 默认登录的url , 身份失败 会访问改 url
		shiroFilterFactoryBean.setLoginUrl("/login");
		//设置 成功之后要跳转的连接 
		shiroFilterFactoryBean.setSuccessUrl("/success");
		//是指 未授权界面, 权限认证失败会访问该 url
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
		
		// LinkedHashMap 有序,进行 顺序拦截配置
		Map<String, String> filterChainMap = new LinkedHashMap<>();
		
		// 配置可以匿名访问的地址 更具实际情况自行添加 目前先放入一些 可能需要过滤的静态资源 anon 表示直接放行
		filterChainMap.put("/css/**", "anon");
		filterChainMap.put("/images/**", "anon");
		filterChainMap.put("/js/**", "anon");

		//登录 url 放行
		filterChainMap.put("/login", "anon");
		// 下面 开始加入 需要身份验证的url
		filterChainMap.put("/user/admin*", "authc");
		// 开头的用户需要 角色认证
		filterChainMap.put("/user/student*/**", "roles[admin]");
		   // “/user/teacher” 开头的用户需要权限认证，是“user:create”才允许
        filterChainMap.put("/user/teacher*/**", "perms[\"user:create\"]");
		
        //配置 logout  登出操作
        filterChainMap.put("/logout", "logout");
        
        //设置 shiroFilterFactoryBean 的 FilterChainDefintionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        logger.info("====shiroFilterFactoryBean注册完成====");
        return shiroFilterFactoryBean;
        
        
        
	}
	
}
