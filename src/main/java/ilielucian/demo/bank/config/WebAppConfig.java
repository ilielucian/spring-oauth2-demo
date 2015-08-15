package ilielucian.demo.bank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Configuration class for Spring MVC.
 *
 * Created by Lucian Ilie on 13-Aug-15.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ilielucian.demo.bank")
class WebAppConfig {

}
