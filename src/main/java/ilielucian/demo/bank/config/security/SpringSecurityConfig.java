package ilielucian.demo.bank.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for the Spring security settings of the application.
 * <p>
 * Created by Lucian Ilie on 15-Aug-15.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configures the authorization method based on a custom
     * {@link ilielucian.demo.bank.users.service.UserDetailsServiceImpl}.
     *
     * @param authBuilder the {@link AuthenticationManagerBuilder} bean
     * @throws Exception
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        // authentication provided by a UserDetailsService, with encrypted password
        authBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Configures the security settings of the application.
     *
     * @param http the HTTP security for HTTP requests
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // allow access without authentication to welcome and login pages
                .authorizeRequests()
                    .antMatchers("/login", "/").permitAll()
                .and()
                // allow access only with role ADMIN to admin and bank account pages
                .authorizeRequests()
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/bankaccount/**").access("hasRole('ROLE_ADMIN')")
                .and()
                // allow access only with at least role USER for the rest of the pages
                // here are included the oauth2 endpoints
                .authorizeRequests()
                    .anyRequest().hasRole("USER")
                .and()
                // configure form login on the login page
                .formLogin()
                    .loginPage("/login").failureUrl("/login?error")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                .logout()
                    .logoutSuccessUrl("/login?logout")
                .and()
                // enable csrf (Cross Site Request Forgery protection)
                .csrf()
                // TODO replace it, as it is overridden by the oauth2 config
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}
