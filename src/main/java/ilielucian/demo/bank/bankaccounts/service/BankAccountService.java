package ilielucian.demo.bank.bankaccounts.service;

import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import ilielucian.demo.bank.bankaccounts.exception.BankAccountNotFoundException;

/**
 * Service for bank accounts.
 * <p>
 * Created by Lucian Ilie on 15-Aug-15.
 */
public interface BankAccountService {

    /**
     * Fetches the {@link BankAccount} with the <code>id</code> sent as parameter.
     *
     * @param id ID of the bank account
     * @return the bank account with the <code>id</code> searched
     * @throws BankAccountNotFoundException, if no bank account if found with the id passed
     */
    BankAccount findBankAccountById(long id) throws BankAccountNotFoundException;
}
