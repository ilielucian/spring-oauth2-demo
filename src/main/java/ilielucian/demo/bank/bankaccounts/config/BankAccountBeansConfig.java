package ilielucian.demo.bank.bankaccounts.config;

import ilielucian.demo.bank.bankaccounts.dao.BankAccountDao;
import ilielucian.demo.bank.bankaccounts.dao.impl.BankAccountDaoImpl;
import ilielucian.demo.bank.bankaccounts.service.BankAccountService;
import ilielucian.demo.bank.bankaccounts.service.impl.BankAccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Lucian Ilie on 15-Aug-15.
 */
@Configuration
@ComponentScan(basePackages = "ilielucian.demo.bank.bankaccounts")
public class BankAccountBeansConfig {

	@Bean
	public BankAccountService bankAccountService() {
		return new BankAccountServiceImpl();
	}

	@Bean
	public BankAccountDao bankAccountDao() {
		return new BankAccountDaoImpl();
	}
}
