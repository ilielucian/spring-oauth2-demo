package ilielucian.demo.bank.bankaccounts.controller;

import ilielucian.demo.bank.application.config.WebAppConfig;
import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import ilielucian.demo.bank.bankaccounts.service.BankAccountService;
import ilielucian.demo.bank.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * Created by Lucian Ilie on 24-Aug-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebAppConfig.class})
@WebAppConfiguration
public class BankAccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private BankAccountService bankAccountServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(bankAccountServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findBankAccountById_ExistingBankAccount() throws Exception {

        BankAccount found = new BankAccount();
        found.setId(1);
        found.setHolderFirstName("Lucian");
        found.setHolderLastName("Ilie");
        found.setHolderSsn("1234567890123");
        found.setBalance(new BigDecimal(6021.33));

        when(bankAccountServiceMock.findBankAccountById(1)).thenReturn(found);

        mockMvc.perform(get("/bankaccount/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.holderFirstName", is("Lucian")))
                .andExpect(jsonPath("$.holderLastName", is("Ilie")));

        verify(bankAccountServiceMock, times(1)).findBankAccountById(1);
        verifyNoMoreInteractions(bankAccountServiceMock);
    }
}
