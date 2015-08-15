package ilielucian.demo.bank.config;

import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Bean configuration class for Hibernate related beans.
 * <p>
 * Created by Lucian Ilie on 15-Aug-15.
 */
@Configuration
@ComponentScan(basePackages = "ilielucian.demo.bank")
@EnableTransactionManagement
class HibernateConfig {

    /**
     * Gets a configured {@link BasicDataSource} bean.
     *
     * @return {@link BasicDataSource} configured with DB driver, username and password
     */
    @Bean
    DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        // TODO
        // get DB parameters from properties file instead of hard-coded
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/database");
        dataSource.setUsername("root");
        dataSource.setPassword("admin123");
        return dataSource;
    }

    @Autowired
    @Bean
    SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionFactoryBuilder =
                new LocalSessionFactoryBuilder(dataSource);
        sessionFactoryBuilder.setProperty("hibernate.show_sql", "true");

        // TODO
        // get DB parameters from properties file instead of hard-coded
        sessionFactoryBuilder
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        sessionFactoryBuilder.addAnnotatedClass(BankAccount.class);

        return sessionFactoryBuilder.buildSessionFactory();
    }

    @Autowired
    @Bean
    HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
