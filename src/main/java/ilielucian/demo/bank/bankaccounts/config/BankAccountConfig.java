package ilielucian.demo.bank.bankaccounts.config;

import ilielucian.demo.bank.config.web.WebAppConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring configuration class for the bank accounts.
 * <p>
 * Created by Lucian Ilie.
 */
@Configuration
@ComponentScan(basePackages = "ilielucian.demo.bank.bankaccounts.service")
@Import(WebAppConfig.class)
public class BankAccountConfig {
}
