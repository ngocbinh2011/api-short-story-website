package com.personal.story.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.personal.story.layer.application.repository",
        entityManagerFactoryRef = "entityArticleManagerFactory",
        transactionManagerRef = "transactionArticleManagerFactory"
)
public class MysqlDatasourceConfig {

    @Bean("mysqlArticleDatasourceProperties")
    public DataSourceProperties dataSourceProperties(){
        Settings settings = Settings.getInstance();
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceProperties.setUrl(
                String.format("jdbc:mysql://localhost:%d/%s",
                        settings.MYSQL_ARTICLE_PORT,
                        settings.MYSQL_ARTICLE_DBNAME)
        );
        dataSourceProperties.setUsername(settings.MYSQL_ARTICLE_USERNAME);
        dataSourceProperties.setPassword(settings.MYSQL_ARTICLE_PASSWORD);
        return dataSourceProperties;
    }

    @Bean("mysqlArticleDatasource")
    @Primary
    public DataSource mysqlWriteDatasource(
            @Qualifier("mysqlArticleDatasourceProperties") DataSourceProperties dataSourceProperties){

        HikariDataSource hikariDataSource =
                dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        hikariDataSource.setMaximumPoolSize(Settings.getInstance().MYSQL_ARTICLE_POOL_SIZE);
        hikariDataSource.setConnectionTimeout(20000);
        return hikariDataSource;
    }

    @Bean("entityArticleManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("mysqlArticleDatasource") DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(false);
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPackagesToScan(
                "com.personal.story.layer.application.entity"
        );
        return entityManagerFactoryBean;
    }

    @Bean("transactionArticleManagerFactory")
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityArticleManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }


}
