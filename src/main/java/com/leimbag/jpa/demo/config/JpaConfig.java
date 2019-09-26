package com.leimbag.jpa.demo.config;

import com.leimbag.jpa.demo.repository.BaseRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author leimbag
 */
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class, basePackages ="com.leimbag.jpa.demo.repository")
@EnableTransactionManagement
public class JpaConfig {
}
