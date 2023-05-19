package com.congduantools.distribute.config.shiro

import com.congduantools.distribute.common.shiro.realm.UserRealm
import org.apache.shiro.SecurityUtils
import org.apache.shiro.mgt.SecurityManager
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Role
import javax.servlet.Filter


/**
 * author： 马世鹏
 *
 *
 * time: 2023/5/19
 *
 *
 * desc:
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
class ShiroConfig {
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    fun shiroFilterFactoryBean(securityManager: DefaultWebSecurityManager?): ShiroFilterFactoryBean {
        val factoryBean = ShiroFilterFactoryBean()
        factoryBean.securityManager = securityManager
        val filterMap: MutableMap<String, Filter> = HashMap(20)
        filterMap["why"] = LoginFilter()
        factoryBean.filters = filterMap
        return factoryBean
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    fun getSecurityManage(userRealm: UserRealm?): DefaultWebSecurityManager {
        val manager = DefaultWebSecurityManager()
        manager.setRealm(userRealm)
        SecurityUtils.setSecurityManager(manager)
        return manager
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    fun userRealm(): UserRealm {
        return UserRealm()
    }

    /**
     * 开启shiro aop注解支持 使用代理方式所以需要开启代码支持
     * 一定要写入上面advisorAutoProxyCreator（）自动代理。不然AOP注解不会生效
     * 不想配置可以在pom文件中加入
     * <dependency>
     * <groupId>org.springframework.boot</groupId>
     * <artifactId>spring-boot-starter-aop</artifactId>
    </dependency> *
     *
     * @param securityManager
     * @return
     */
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    fun authorizationAttributeSourceAdvisor(securityManager: SecurityManager): AuthorizationAttributeSourceAdvisor {
        val authorizationAttributeSourceAdvisor = AuthorizationAttributeSourceAdvisor()
        authorizationAttributeSourceAdvisor.securityManager = securityManager
        return authorizationAttributeSourceAdvisor
    }
}
