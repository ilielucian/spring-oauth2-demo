package ilielucian.demo.bank.application.config;

import ilielucian.demo.bank.authorization.config.SpringSecurityConfig;
import ilielucian.demo.bank.bankaccounts.config.BankAccountConfig;
import ilielucian.demo.bank.users.config.UserConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * Created by Lucian Ilie on 25-Aug-15.
 */
@Configuration
@Import({
        WebAppConfig.class, SpringSecurityConfig.class, HibernateConfig.class,
        BankAccountConfig.class, UserConfig.class})
public class ApplicationConfig {
}
