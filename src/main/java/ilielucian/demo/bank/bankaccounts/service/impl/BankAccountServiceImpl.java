package ilielucian.demo.bank.bankaccounts.service.impl;

import ilielucian.demo.bank.bankaccounts.dao.BankAccountDao;
import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import ilielucian.demo.bank.bankaccounts.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of the {@link BankAccountService} interface.
 * <p/>
 * Created by Lucian Ilie on 15-Aug-15.
 */
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountDao bankAccountDao;

	public BankAccount findBankAccountById(long id) {
		return bankAccountDao.getBankAccountById(id);
	}
}
