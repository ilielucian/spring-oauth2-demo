package ilielucian.demo.bank.config.authorization;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Configuration class for OAuth2 resource server and authorization server.
 * <p>
 * Created by Lucian Ilie.
 */
@Configuration
public class OAuth2ServerConfig {

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
                    // Since we want the protected resources to be accessible in the UI as well we need
                    // session creation to be allowed (from oauth2 example)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    // apply oauth2 authentication for the admin and bank account pages
                    .requestMatchers()
                        .antMatchers("/admin/**", "/bankaccount/**")
                    .and()
                    // allow access only if has oauth2 trust scope and trusted client role
                    // or is authenticated as user/admin
                    .authorizeRequests()
                        .antMatchers("/admin/**")
                            .access("(#oauth2.clientHasRole('ROLE_TRUSTED_CLIENT') and #oauth2.hasScope('trust')) or " +
                                    "(!#oauth2.isOAuth() and hasRole('ROLE_ADMIN'))")
                        .antMatchers("/bankaccount/**")
                        // experiment: this setting overrides the security config which had role admin
                            .access("(#oauth2.clientHasRole('ROLE_TRUSTED_CLIENT') and #oauth2.hasScope('trust')) or " +
                                    "(!#oauth2.isOAuth() and hasRole('ROLE_USER'))");
        }
    }

    @Configuration
    @EnableAuthorizationServer
    static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Bean
        TokenStore tokenStore() {
            // TODO replace with JDBC token store
            return new InMemoryTokenStore();
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore());
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    // TODO replace with JDBC client details service
                    .inMemory()
                        .withClient("bank-client")
                        .resourceIds(BANK_RESOURCE_ID)
                    // configure only for authorization code flow
                        .authorizedGrantTypes("authorization_code")
                        .authorities("ROLE_TRUSTED_CLIENT")
                        .scopes("trust")
                        .secret("secret");
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.allowFormAuthenticationForClients();
        }
    }
}
