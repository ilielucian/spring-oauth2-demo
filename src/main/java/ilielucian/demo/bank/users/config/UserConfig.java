package ilielucian.demo.bank.users.config;

import ilielucian.demo.bank.config.security.SpringSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring configuration class for users.
 * <p>
 * Created by Lucian Ilie.
 */
@Configuration
@ComponentScan(basePackages = "ilielucian.demo.bank.users.service")
@Import(SpringSecurityConfig.class)
public class UserConfig {
}
