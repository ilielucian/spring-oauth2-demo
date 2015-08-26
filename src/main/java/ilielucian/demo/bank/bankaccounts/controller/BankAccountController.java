package ilielucian.demo.bank.bankaccounts.controller;

import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import ilielucian.demo.bank.bankaccounts.exception.BankAccountNotFoundException;
import ilielucian.demo.bank.bankaccounts.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple Spring REST Controller for bank account service.
 * <p>
 * Created by Lucian Ilie on 13-Aug-15.
 */
@RestController
public class BankAccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    private BankAccountService bankAccountService;

    @RequestMapping(value = "/bankaccount/{id}")
    public BankAccount bankAccount(@PathVariable long id) throws BankAccountNotFoundException {
        LOGGER.debug("Searching for bank account with id = {}", id);
        BankAccount found = bankAccountService.findBankAccountById(id);

        LOGGER.debug("Found bank account with id = {} and information = {}", id, found);
        return found;
    }
}
