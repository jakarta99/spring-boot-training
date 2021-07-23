package tw.com.softleader.training.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "integrationEntityManagerFactory",
        transactionManagerRef = "integrationTransactionManager",
        basePackages = {"tw.com.softleader.training.integration"})
public class IntegrationDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.integration-datasource")
    public DataSourceProperties integrationDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    
    @Bean
    @ConfigurationProperties("spring.integration-datasource.configuration")
    public HikariDataSource integrationDataSource(DataSourceProperties integrationDataSourceProperties) {
        return integrationDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    
    
    @Bean(name = "integrationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean integrationEntityManagerFactory(
            EntityManagerFactoryBuilder integrationEntityManagerFactoryBuilder, @Qualifier("integrationDataSource") DataSource integrationDataSource) {

        Map<String, String> integrationJpaProperties = new HashMap<>();
        integrationJpaProperties.put("hibernate.hbm2ddl.auto", "create");
        integrationJpaProperties.put("hibernate.show_sql", "true");
        integrationJpaProperties.put("hibernate.format_sql", "true");

        return integrationEntityManagerFactoryBuilder
                .dataSource(integrationDataSource)
                .packages("tw.com.softleader.training.integration")
                .persistenceUnit("integrationDataSource")
                .properties(integrationJpaProperties)
                .build();
    }

    @Bean
    public PlatformTransactionManager integrationTransactionManager(
            @Qualifier("integrationEntityManagerFactory") EntityManagerFactory integrationEntityManagerFactory) {

        return new JpaTransactionManager(integrationEntityManagerFactory);
    }
    
    
}
