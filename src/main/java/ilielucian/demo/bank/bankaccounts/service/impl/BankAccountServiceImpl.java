package ilielucian.demo.bank.bankaccounts.service.impl;

import ilielucian.demo.bank.bankaccounts.dao.BankAccountDao;
import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import ilielucian.demo.bank.bankaccounts.exception.BankAccountNotFoundException;
import ilielucian.demo.bank.bankaccounts.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the {@link BankAccountService} interface.
 * <p>
 * Created by Lucian Ilie on 15-Aug-15.
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountDao bankAccountDao;

    @Transactional(readOnly = true, rollbackFor = BankAccountNotFoundException.class)
    public BankAccount findBankAccountById(long id) throws BankAccountNotFoundException {
        BankAccount found =  bankAccountDao.getBankAccountById(id);

        if (found == null) {
            throw new BankAccountNotFoundException("No entry found with id: " + id);
        }

        return found;
    }
}
