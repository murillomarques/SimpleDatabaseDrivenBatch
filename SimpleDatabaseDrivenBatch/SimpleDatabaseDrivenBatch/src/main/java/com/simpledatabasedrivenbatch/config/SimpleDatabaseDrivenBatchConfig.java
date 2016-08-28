package com.simpledatabasedrivenbatch.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages ="com.simpledatabasedrivenbatch.dao")
@Import(DataSourceAutoConfiguration.class)
@PropertySource("classpath:application.properties")
public class SimpleDatabaseDrivenBatchConfig {
	
	

}
