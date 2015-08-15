package ilielucian.demo.bankaccounts.config;

import ilielucian.demo.bankaccounts.rest.dao.BankAccountDao;
import ilielucian.demo.bankaccounts.rest.dao.impl.BankAccountDaoImpl;
import ilielucian.demo.bankaccounts.rest.domain.BankAccount;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by Lucian Ilie on 13-Aug-15.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ilielucian.demo.bankaccounts")
@EnableTransactionManagement
public class WebAppConfig {


	@Bean
	public BankAccountDao bankAccountDao() {
		return new BankAccountDaoImpl();
	}

	@Bean(name = "dataSource")
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
		LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionFactoryBuilder.setProperty("hibernate.show_sql", "true");
		sessionFactoryBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		sessionFactoryBuilder.addAnnotatedClass(BankAccount.class);
		return sessionFactoryBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = 
				new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

}
