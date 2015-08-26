package ilielucian.demo.bank.bankaccounts.controller;

import ilielucian.demo.bank.bankaccounts.exception.BankAccountNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * Created by Lucian Ilie on 26-Aug-15.
 */
@ControllerAdvice(basePackages = "ilielucian.demo.bank.bankaccounts.controller")
public class BankAccountErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountErrorHandler.class);

    @ExceptionHandler(BankAccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleBankAccountNotFoundException(BankAccountNotFoundException e) {
        LOGGER.debug("Handling a 404 error on a bank account not found.");
    }
}
