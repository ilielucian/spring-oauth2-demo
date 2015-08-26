package ilielucian.demo.bank.bankaccounts.controller;

import ilielucian.demo.bank.bankaccounts.exception.BankAccountNotFoundException;
import ilielucian.demo.bank.config.web.WebAppConfig;
import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import ilielucian.demo.bank.bankaccounts.service.BankAccountService;
import ilielucian.demo.bank.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
 * Test class for the {@link BankAccountController}.
 *
 * Created by Lucian Ilie on 24-Aug-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebAppConfig.class})
@WebAppConfiguration
public class BankAccountControllerTest {

    private static final int ID = 1; // although model requires long, used int because of Hamcrest problem
    private static final String HOLDER_FIRST_NAME = "Lucian";
    private static final String HOLDER_LAST_NAME = "Ilie";
    private static final String HOLDER_SSN = "1234567890123";
    private static final BigDecimal BALANCE = new BigDecimal(6021.33);

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
        found.setId(ID);
        found.setHolderFirstName(HOLDER_FIRST_NAME);
        found.setHolderLastName(HOLDER_LAST_NAME);
        found.setHolderSsn(HOLDER_SSN);
        found.setBalance(BALANCE);

        when(bankAccountServiceMock.findBankAccountById(ID)).thenReturn(found);

        mockMvc.perform(get("/bankaccount/{id}", ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.holderFirstName", is(HOLDER_FIRST_NAME)))
                .andExpect(jsonPath("$.holderLastName", is(HOLDER_LAST_NAME)))
                .andExpect(jsonPath("$.holderSsn", is(HOLDER_SSN)))
                .andExpect(jsonPath("$.balance", is(BALANCE)));

        verify(bankAccountServiceMock, times(1)).findBankAccountById(ID);
        verifyNoMoreInteractions(bankAccountServiceMock);
    }

    @Test
    public void findBankAccountById_NonExistingBankAccount() throws Exception {
        when(bankAccountServiceMock.findBankAccountById(ID)).thenThrow(new BankAccountNotFoundException(""));

        mockMvc.perform(get("/bankaccount/{id}", ID))
                .andExpect(status().isNotFound());

        verify(bankAccountServiceMock, times(1)).findBankAccountById(ID);
        verifyNoMoreInteractions(bankAccountServiceMock);
    }


}
