package com.xiexin.shiro;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 的web配置
 * 目的： 因为shiro可以和 很多 项目适配， 那么我们是web项目，就需要 配置成web 的 securityManger
 * ， 又 因为是 web项目， 所以 需要使用  过滤器 来  配置  需要拦截的请求，和非拦截的请求，
 *
 */
@Configuration  // 配置类的注解， 表明该类是 配置类，  该注解 是 配置 的意思， 顶替的是 xml中的配置。
            // 优先于 其他注解优先执行。
public class ShiroConfig {
    // 1. shiroconfig 需要指明 Realm 是谁 ， 并且把这个 realm 创建出来， 这个创建指的是， 优先于 其他的 controller ，service 等
    // 对象 优先创建。
    @Bean
    public Realm getMybatisRealm(){
        MybatisRealm realm = new MybatisRealm();
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("md5");
//        matcher.setHashIterations(1024);
//        realm.setCredentialsMatcher(matcher); // 注入匹配，  注入 加密加盐的匹配
        return realm;
    }

    // 2. 指派  securityManager  ，因为是我们是web项目所以 shi  websecurityManager
    @Bean
    public DefaultWebSecurityManager getSecurityManager (Realm realm){
         DefaultWebSecurityManager sm= new DefaultWebSecurityManager();
         sm.setRealm(realm);
         sm.setRememberMeManager(cookieRememberMeManager());
         return sm;
    }
    // 以上， 仙女们和妈妈桑 就 勾搭再一起了。
    // 3. 剩男  subject ， 他需要 用 过滤器来获取。
    @Bean
    public ShiroFilterFactoryBean getFilter(DefaultWebSecurityManager sm){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(sm);
        // 使用 过滤器
        Map map = new LinkedHashMap<>(); // 这个map 是有序的。
        // 不拦截的页面！！！
        map.put("/page/login","anon");  // 谢欣讲的 anon 匿名的 ， 任何请求都可以  去访问
        map.put("/page/storelist","anon");  //谢欣讲的



        map.put("/userinfo/loginByShiro","anon"); // 登录的方法也不拦截
//        map.put("/admin/reginByShiro","anon"); // 注册的方法也不拦截

       map.put("/page/add","anon"); // 注册的方法也不拦截  谢欣讲的
//        map.put("/*/**","authc");   // authc 需要登录
        map.put("/*/**","user");   // user 记住我的需要登录
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map); // 把 拦截的 顺序放入到 linkedmap中！！
        return shiroFilterFactoryBean;
    }


    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @Author:      谢欣
     * @UpdateUser:
     * @Version:     0.0.1
     * @param securityManager
     * @return       org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @throws
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
    // 记住我 的 功能 ， 需要 依赖于 cookie
    // cookie 的管理对象
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(remeberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        // cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        //手动设置对称加密秘钥，防止重启系统后系统生成新的随机秘钥，防止导致客户端cookie无效
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j3Y+R1aSn5BOlAA=="));
        return cookieRememberMeManager;
    }

    //记住我的cookie
    @Bean
    public SimpleCookie remeberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //设置cookie 的时间
        //防止 xss 读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        simpleCookie.setMaxAge(60 * 60 * 24 * 30);
        return simpleCookie;
    }

    /*
    统一异常
    * 任何项目，都会有异常，这种异常是怎么来的
    * 1、代码本身有逻辑问题
    * 2、顾客不规范操作
    * 当 1和2 情况出现，会在 浏览器上面显示 错误的提示，极度不美观，吓到客户
    *
    * 全局异常处理有3种方式
    *
    * 1、使用 @ExceptionHandler 注解
     2、实现 HandlerExceptionResolver 接口
      3、使用 @ControllerAdvice 注解
    *
    * 执行范围  @ExceptionHandler < @ControllerAdvice < HandlerExceptionResolver
    * 一起出现只会执行最小的！！！
    *
    * 会其中一种即可！！！！
    * */


}
