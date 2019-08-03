package com.furesky.cms.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration
public class DataSourceConfig {
	@Value("${spring.datasource.url}")
	private String jdbcUrl;

	@Value("${spring.datasource.driver-class-name}")
	private String jdbcDriverClassName;

	@Value("${spring.datasource.username}")
	private String jdbcUsername;

	@Value("${spring.datasource.password}")
	private String jdbcPassword;

	/**
	 * 配置数据库
	 * 
	 * @return
	 */
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		// 数据库驱动
		datasource.setDriverClassName(jdbcDriverClassName);
		// 相应驱动的jdbcUrl
		datasource.setUrl(jdbcUrl);
		// 数据库的用户名
		datasource.setUsername(jdbcUsername);
		// 数据库的密码
		datasource.setPassword(jdbcPassword);
		// 每个分区最大的连接数
		datasource.setMaxActive(20);
		// 每个分区最小的连接数
		datasource.setMinIdle(5);
		return datasource;
	}
}
