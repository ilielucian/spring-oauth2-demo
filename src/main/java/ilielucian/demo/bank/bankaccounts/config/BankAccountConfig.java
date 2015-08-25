package ilielucian.demo.bank.bankaccounts.config;

import ilielucian.demo.bank.application.config.WebAppConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * Created by Lucian Ilie on 25-Aug-15.
 */
@Configuration
@ComponentScan(basePackages = "ilielucian.demo.bank.bankaccounts.service")
@Import(WebAppConfig.class)
public class BankAccountConfig {
}
