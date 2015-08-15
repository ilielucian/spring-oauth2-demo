package ilielucian.demo.bank.bankaccounts.dao;

import ilielucian.demo.bank.bankaccounts.domain.BankAccount;

/**
 * Data Access Object for a {@link BankAccount}.
 * <p>
 * Created by Lucian Ilie on 13-Aug-15.
 */
public interface BankAccountDao {

    /**
     * Gets the {@link BankAccount} result of the search into the DB, by <code>id</code>.
     *
     * @param id ID of the bank account
     * @return the {@link BankAccount} with the ID searched, if found;
     *          <br/><code>null</code>, if not found
     */
    BankAccount getBankAccountById(long id);
}
