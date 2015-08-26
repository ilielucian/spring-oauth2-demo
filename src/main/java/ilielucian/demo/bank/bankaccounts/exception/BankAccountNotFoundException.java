package ilielucian.demo.bank.bankaccounts.exception;

/**
 * Exception thrown when a User is not found by searching the DB.
 * <p>
 * Created by Lucian Ilie on 26-Aug-15.
 */
public class BankAccountNotFoundException extends Exception {

    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
