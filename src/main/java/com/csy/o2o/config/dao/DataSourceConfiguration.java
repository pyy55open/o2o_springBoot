package com.csy.o2o.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

@Configuration
@MapperScan("com.csy.o2o.dao")
public class DataSourceConfiguration {

    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    /**
     * 生成与spring-dao.xml对应的bean dataSource
     *
     * @return
     * @throws PropertyVetoException
     */
    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(jdbcDriver);
        comboPooledDataSource.setJdbcUrl(jdbcUrl);
        comboPooledDataSource.setUser(jdbcUsername);
        comboPooledDataSource.setPassword(jdbcPassword);
        // 配置c3p0连接池的私有属性
        // 连接池最大线程数
        comboPooledDataSource.setMaxPoolSize(30);
        // 连接池最小线程数
        comboPooledDataSource.setMinPoolSize(10);
        // 关闭连接后不自动commit
        comboPooledDataSource.setAutoCommitOnClose(false);
        // 连接超时时间
        comboPooledDataSource.setCheckoutTimeout(10000);
        // 连接失败重试次数
        comboPooledDataSource.setAcquireRetryAttempts(2);
        return comboPooledDataSource;
    }
}
