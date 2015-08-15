package ilielucian.demo.bank.bankaccounts.controller;

import ilielucian.demo.bank.bankaccounts.dao.BankAccountDao;
import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import ilielucian.demo.bank.bankaccounts.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Lucian Ilie on 13-Aug-15.
 */
@RestController
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	@RequestMapping(value = "/bankaccount/{id}", method = RequestMethod.GET, produces = "application/json")
	public BankAccount bankAccount(@PathVariable long id) {
		return bankAccountService.findBankAccountById(id);
	}
}
