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
 * Global application configuration class. Used to centralize all configs
 * of the application and keep each config separated.
 * <p>
 * Created by Lucian Ilie.
 */
@Configuration
@Import({
        WebAppConfig.class, SpringSecurityConfig.class, PersistenceConfig.class,
        BankAccountConfig.class, UserConfig.class, OAuth2ServerConfig.class})
public class ApplicationConfig {
}
