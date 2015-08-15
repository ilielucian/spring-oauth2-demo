package ilielucian.demo.bankaccounts.rest.dao;

import ilielucian.demo.bankaccounts.rest.domain.BankAccount;

/**
 * Created by Lucian Ilie on 13-Aug-15.
 */
public interface BankAccountDao {

	BankAccount getBankAccountById(long id);
}
