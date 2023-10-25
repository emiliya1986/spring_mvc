package web.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.User;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

   @Autowired
   private Environment env;

   @Bean
   public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
      LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
      entityManager.setDataSource(getDataSource());
      entityManager.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
      entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      entityManager.setJpaProperties(hibProperties());
      return entityManager;
   }

   private Properties hibProperties() {
      Properties properties = new Properties();
      properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
      properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
      return properties;
   }

   @Bean
   public JpaTransactionManager getTransactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
      return transactionManager;
   }

}
