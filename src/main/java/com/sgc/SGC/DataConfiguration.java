package com.sgc.SGC;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class DataConfiguration {
	
	@Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
	

	/*
	@Bean
	public DataSource dataSource() throws URISyntaxException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("mysql://us-cdbr-iron-east-03.cleardb.net/heroku_e8e993c63c850f4?reconnect=true");
		dataSource.setUsername("bc71a305c71f5b");
		dataSource.setPassword("a851b34a");
		
		System.out.println("TESTE3-1");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		System.out.println("TESTE3-2");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		System.out.println("TESTE3-3");
		dataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-03.cleardb.net:3306/heroku_e8e993c63c850f4?reconnect=true");
		System.out.println("TESTE3-4");
		dataSource.setUsername("bc71a305c71f5b");
		System.out.println("TESTE3-5");
		dataSource.setPassword("a851b34a");
		System.out.println("TESTE3-6");
		
		URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        
		System.out.println("TESTE3-7");
		return dataSource;	
		
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		adapter.setPrepareConnection(true);
		return adapter;
	}
	*/
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
	    return new SpringSecurityDialect();
	}
	/*
	@Bean
    public EntityManagerFactory entityManagerFactory() throws URISyntaxException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.TRUE);
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setShowSql(Boolean.TRUE);
        vendorAdapter.setPrepareConnection(true);
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        System.out.println("TESTE");
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        System.out.println("TESTE1");
        factory.setJpaVendorAdapter(vendorAdapter);
        System.out.println("TESTE2");
        factory.setPackagesToScan("com.sgc.SGC.");
        System.out.println("TESTE3");
        factory.setDataSource(dataSource());
        System.out.println("TESTE4");
        factory.afterPropertiesSet();
        System.out.println("TESTE5");
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        System.out.println("TESTE6");
        return factory.getObject();
    }
	*/
}
