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
 * Created by Lucian Ilie on 15-Aug-15.
 */
@Configuration
@ComponentScan(basePackages = "ilielucian.demo.bank")
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/database");
        dataSource.setUsername("root");
        dataSource.setPassword("admin123");
        return dataSource;
    }

    @Autowired
    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionFactoryBuilder =
                new LocalSessionFactoryBuilder(dataSource);
        sessionFactoryBuilder.setProperty("hibernate.show_sql", "true");
        sessionFactoryBuilder
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        sessionFactoryBuilder.addAnnotatedClass(BankAccount.class);
        return sessionFactoryBuilder.buildSessionFactory();
    }

    @Autowired
    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
