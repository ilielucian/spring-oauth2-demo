package ilielucian.demo.bank.authorization.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 *
 * Created by Lucian Ilie on 15-Aug-15.
 */
@Configuration
class OAuth2ServerConfig {

    private static final String BANK_RESOURCE_ID = "bank";

    @Configuration
    @EnableResourceServer
    static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(BANK_RESOURCE_ID).stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers()
                        .antMatchers("/admin/**", "/bankaccount/**", "/oauth/users/**", "/oauth/clients/**")
                    .and()
                    .authorizeRequests()
                        .antMatchers("/admin/**")
                            .access("#oauth2.hasScope('read')")
                        .antMatchers("/bankaccount/**")
                            .access("#oauth2.hasScope('trust') or (!#oauth2.isOAuth() and hasRole('ROLE_ADMIN'))");
        }
    }
}
