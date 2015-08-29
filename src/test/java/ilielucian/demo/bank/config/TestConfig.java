package ilielucian.demo.bank.config;

import ilielucian.demo.bank.bankaccounts.service.BankAccountService;
import ilielucian.demo.bank.users.service.UserDetailsServiceImpl;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Configuration class for unit tests, providing the mock beans.
 * <p>
 * Created by Lucian Ilie on 24-Aug-15.
 */
@Configuration
public class TestConfig {

    @Bean
    public BankAccountService bankAccountServiceMock() {
        return Mockito.mock(BankAccountService.class);
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return Mockito.mock(UserDetailsServiceImpl.class);
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Bean
    public MockMvc mockMvc() {
        return MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }
}
