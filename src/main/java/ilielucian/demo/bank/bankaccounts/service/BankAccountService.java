package ilielucian.demo.bank.bankaccounts.service;

import ilielucian.demo.bank.bankaccounts.domain.BankAccount;

/**
 * Service for bank accounts.
 * <p/>
 * Created by Lucian Ilie on 15-Aug-15.
 */
public interface BankAccountService {

	/**
	 * Fetches the {@link BankAccount} with the <code>id</code> sent as parameter.
	 *
	 * @param id ID of the bank account
	 * @return the bank account with the <code>id</code> searched
	 */
	BankAccount findBankAccountById(long id);
}
