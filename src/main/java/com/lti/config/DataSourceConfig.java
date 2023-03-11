package com.lti.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.order")
    public DataSourceProperties orderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource orderDataSource(@Qualifier("orderDataSourceProperties") DataSourceProperties orderDataSourceProperties) {
        return orderDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Qualifier("orderTemplate")
    public NamedParameterJdbcTemplate orderParameterJdbcTemplate(@Qualifier("orderDataSource") DataSource orderDataSource){
        return new NamedParameterJdbcTemplate(orderDataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.factory")
    public DataSourceProperties factoryDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource factoryDataSource(@Qualifier("factoryDataSourceProperties") DataSourceProperties factoryDataSourceProperties) {
        return factoryDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Qualifier("factoryTemplate")
    public NamedParameterJdbcTemplate factoryParameterJdbcTemplate(@Qualifier("factoryDataSource") DataSource factoryDataSource){
        return new NamedParameterJdbcTemplate(factoryDataSource);
    }
}
