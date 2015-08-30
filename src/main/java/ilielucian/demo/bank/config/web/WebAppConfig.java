package ilielucian.demo.bank.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configuration class for Spring MVC.
 * <p>
 * Created by Lucian Ilie.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "ilielucian.demo.bank.bankaccounts.controller",
        "ilielucian.demo.bank.application.controller"
})
public class WebAppConfig {

    /**
     * Creates a new view resolver bean for the web app controllers.
     *
     * @return a new view resolver
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(0);
        return viewResolver;
    }
}
