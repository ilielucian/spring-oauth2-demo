package ilielucian.demo.bank.config;

import ilielucian.demo.bank.bankaccounts.service.BankAccountService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

/**
 *
 * Created by Lucian Ilie on 24-Aug-15.
 */
@Configuration
public class TestConfig {

    public static final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Bean
    public BankAccountService bankAccountServiceMock() {
        return Mockito.mock(BankAccountService.class);
    }


}
