package ilielucian.demo.bank.config.persistence;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import ilielucian.demo.bank.users.domain.User;
import ilielucian.demo.bank.users.domain.UserRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Bean configuration class for Persistence / Hibernate related beans.
 * <p>
 * Created by Lucian Ilie.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence.properties")
@ComponentScan(basePackages = {
        "ilielucian.demo.bank.bankaccounts.dao",
        "ilielucian.demo.bank.users.dao"
})
public class PersistenceConfig {

    private static final String PROPERTY_DB_DRIVER = "db.driver";
    private static final String PROPERTY_DB_URL = "db.url";
    private static final String PROPERTY_DB_USERNAME = "db.username";
    private static final String PROPERTY_DB_PASSWORD = "db.password";

    private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_HIBERNATE_2ND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    private static final String PROPERTY_HIBERNATE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    private static final String PROPERTY_HIBERNATE_REGION_FACTORY = "hibernate.cache.region.factory_class";

    @Resource
    private Environment env;

    /**
     * Gets a configured C3P0 {@link ComboPooledDataSource} bean.
     *
     * @return {@link ComboPooledDataSource} configured with DB driver, username and password
     */
    @Bean
    DataSource getDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(env.getRequiredProperty(PROPERTY_DB_DRIVER));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(env.getRequiredProperty(PROPERTY_DB_URL));
        dataSource.setUser(env.getRequiredProperty(PROPERTY_DB_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_DB_PASSWORD));
        return dataSource;
    }

    @Autowired
    @Bean
    SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource);

        // set Hibernate properties
        sessionFactoryBuilder
                .setProperty(PROPERTY_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));
        sessionFactoryBuilder
                .setProperty(PROPERTY_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROPERTY_HIBERNATE_FORMAT_SQL));
        sessionFactoryBuilder
                .setProperty(PROPERTY_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT));
        sessionFactoryBuilder
                .setProperty(PROPERTY_HIBERNATE_2ND_LEVEL_CACHE, env.getRequiredProperty(PROPERTY_HIBERNATE_2ND_LEVEL_CACHE));
        sessionFactoryBuilder
                .setProperty(PROPERTY_HIBERNATE_QUERY_CACHE, env.getRequiredProperty(PROPERTY_HIBERNATE_QUERY_CACHE));
        sessionFactoryBuilder.
                setProperty(PROPERTY_HIBERNATE_REGION_FACTORY, env.getRequiredProperty(PROPERTY_HIBERNATE_REGION_FACTORY));

        // set annotated classes
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
