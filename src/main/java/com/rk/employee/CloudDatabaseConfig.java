package com.rk.employee;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.PooledServiceConnectorConfig.PoolConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.cloud.service.relational.DataSourceConfig.ConnectionConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("cloud")
public class CloudDatabaseConfig extends AbstractCloudConfig {
	@Value("${vcap.services.hana_instance_schema.credentials.user}")
	private String username;
	
	@Value("${vcap.services.hana_instance_schema.credentials.password}")
	private String password;
	
	@Value("${vcap.services.hana_instance_schema.credentials.url}")
	private String url;
	
	@Value("${vcap.services.hana_instance_schema.credentials.schema}")
	private String schemaname;
	
	
	
	
	@Bean
    public DataSource dataSource() {
        List<String> dataSourceNames = Arrays.asList("BasicDbcpPooledDataSourceCreator",
                "TomcatJdbcPooledDataSourceCreator", "HikariCpPooledDataSourceCreator",
                "TomcatDbcpPooledDataSourceCreator");
        PoolConfig poolConfig = new PoolConfig(5, 30, 3000);
        ConnectionConfig connConfig = new ConnectionConfig("encrypt=true;validateCertificate=true");
        DataSourceConfig dbConfig = new DataSourceConfig(poolConfig, connConfig, dataSourceNames);
        
       DataSource myConnection = DataSourceBuilder.create()
		.type(HikariDataSource.class)
		.driverClassName(com.sap.db.jdbc.Driver.class.getName())
		.url(url)
		.username(username)
		.password(password)
		.build();	 
       
       try {
    	   myConnection.getConnection().setSchema(schemaname);
       } catch (SQLException e)
       { e.printStackTrace();}
    return myConnection;   
	}
}
