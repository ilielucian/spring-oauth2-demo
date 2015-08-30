package ilielucian.demo.bank.config.application;

import ilielucian.demo.bank.config.authorization.OAuth2ServerConfig;
import ilielucian.demo.bank.config.web.WebAppConfig;
import ilielucian.demo.bank.config.security.SpringSecurityConfig;
import ilielucian.demo.bank.bankaccounts.config.BankAccountConfig;
import ilielucian.demo.bank.config.persistence.PersistenceConfig;
import ilielucian.demo.bank.users.config.UserConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * Created by Lucian Ilie on 25-Aug-15.
 */
@Configuration
@Import({
        WebAppConfig.class, SpringSecurityConfig.class, PersistenceConfig.class,
        BankAccountConfig.class, UserConfig.class, OAuth2ServerConfig.class})
public class ApplicationConfig {
}
