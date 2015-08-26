package ilielucian.demo.bank.bankaccounts.dao;

import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import ilielucian.demo.bank.bankaccounts.exception.BankAccountNotFoundException;
import ilielucian.demo.bank.bankaccounts.service.BankAccountService;
import ilielucian.demo.bank.bankaccounts.service.impl.BankAccountServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

/**
 * Test class for the {@link BankAccountDao}.
 * <p>
 * Created by Lucian Ilie on 26-Aug-15.
 */
public class BankAccountDaoTest {

    private static final int ID = 1; // although model requires long, used int because of Hamcrest problem
    private static final String HOLDER_FIRST_NAME = "Lucian";
    private static final String HOLDER_LAST_NAME = "Ilie";
    private static final String HOLDER_SSN = "1234567890123";
    private static final BigDecimal BALANCE = new BigDecimal(6021.33);

    private BankAccountService bankAccountServiceMock;
    private BankAccountDao bankAccountDaoMock;

    @Before
    public void setUp() {
        bankAccountDaoMock = mock(BankAccountDao.class);
        bankAccountServiceMock = new BankAccountServiceImpl(bankAccountDaoMock);
    }

    @Test
    public void getBankAccountById_ExistingBankAccount() throws BankAccountNotFoundException {
        BankAccount expected = new BankAccount();
        expected.setId(ID);
        expected.setHolderFirstName(HOLDER_FIRST_NAME);
        expected.setHolderLastName(HOLDER_LAST_NAME);
        expected.setHolderSsn(HOLDER_SSN);
        expected.setBalance(BALANCE);

        when(bankAccountDaoMock.getBankAccountById(ID)).thenReturn(expected);

        BankAccount found = bankAccountServiceMock.findBankAccountById(ID);

        verify(bankAccountDaoMock, times(1)).getBankAccountById(ID);
        verifyNoMoreInteractions(bankAccountDaoMock);

        assertThat(found, is(expected));
    }

    @Test(expected = BankAccountNotFoundException.class)
    public void getBankAccountById_NonExistingBankAccount() throws BankAccountNotFoundException {
        when(bankAccountDaoMock.getBankAccountById(ID)).thenReturn(null);

        bankAccountServiceMock.findBankAccountById(ID);

        verify(bankAccountDaoMock, times(1)).getBankAccountById(ID);
        verifyNoMoreInteractions(bankAccountDaoMock);
    }
}
