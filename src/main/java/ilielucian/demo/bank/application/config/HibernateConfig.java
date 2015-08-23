package ilielucian.demo.bank.application.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import ilielucian.demo.bank.users.domain.User;
import ilielucian.demo.bank.users.domain.UserRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

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
     * Gets a configured C3P0 {@link ComboPooledDataSource} bean.
     *
     * @return {@link ComboPooledDataSource} configured with DB driver, username and password
     */
    @Bean
    DataSource getDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        // TODO
        // get DB parameters from properties file instead of hard-coded
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/database");
        dataSource.setUser("root");
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
        sessionFactoryBuilder.setProperty("hibernate.cache.use_second_level_cache", "true");
        sessionFactoryBuilder.setProperty("hibernate.cache.use_query_cache", "true");
        sessionFactoryBuilder.
                setProperty("hibernate.cache.region.factory_class",
                        "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        sessionFactoryBuilder.addAnnotatedClass(BankAccount.class);
        sessionFactoryBuilder.addAnnotatedClass(User.class);
        sessionFactoryBuilder.addAnnotatedClass(UserRole.class);
        return sessionFactoryBuilder.buildSessionFactory();
    }

    @Autowired
    @Bean
    HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
