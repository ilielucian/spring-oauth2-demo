package ilielucian.demo.bank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Lucian Ilie on 13-Aug-15.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ilielucian.demo.bank")
public class WebAppConfig {

}
