package com.csy.o2o.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SessionfactoryConfiguration {

    @Autowired
    private DataSource data;

    //mybatis-config.xml配置文件
    private static String mybatisConfig;

    @Value("${mybatis_config_file}")
    public void setMybatisConfig(String mybatisConfig) {
        SessionfactoryConfiguration.mybatisConfig = mybatisConfig;
    }
    private static String mapperPath;

    @Value("${mapper_path}")
    public void setMapperPath(String mapperPath) {
        SessionfactoryConfiguration.mapperPath = mapperPath;
    }

    @Value("${type_alias_package}")
    private String typeAliasesPackage;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //mybatis配置文件扫描
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfig));
        //扫描文件路径
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageScanPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageScanPath));
        //设置datasource
        sqlSessionFactoryBean.setDataSource(data);
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        return sqlSessionFactoryBean;
    }
}
