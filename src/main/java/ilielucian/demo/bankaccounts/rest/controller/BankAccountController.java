package ilielucian.demo.bankaccounts.rest.controller;

import ilielucian.demo.bankaccounts.rest.dao.BankAccountDao;
import ilielucian.demo.bankaccounts.rest.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Lucian Ilie on 13-Aug-15.
 */
@Controller
public class BankAccountController {

	@Autowired
	private BankAccountDao bankAccountDao;

	@RequestMapping(value = "/bankaccount/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	BankAccount bankAccount(@PathVariable long id) {
		return bankAccountDao.getBankAccountById(id);
	}
}
