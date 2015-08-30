package ilielucian.demo.bank.bankaccounts.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Class simulating a simple bank account.
 * <p>
 * Created by Lucian Ilie.
 */
@Entity
@Table(name = "bankaccounts")
public class BankAccount {

    private long id;

    private String holderFirstName;

    private String holderLastName;

    private String holderSsn;

    private BigDecimal balance;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "FIRST_NAME")
    public String getHolderFirstName() {
        return holderFirstName;
    }

    public void setHolderFirstName(String holderFirstName) {
        this.holderFirstName = holderFirstName;
    }

    @Column(name = "LAST_NAME")
    public String getHolderLastName() {
        return holderLastName;
    }

    public void setHolderLastName(String holderLastName) {
        this.holderLastName = holderLastName;
    }

    @Column(name = "SSN")
    public String getHolderSsn() {
        return holderSsn;
    }

    public void setHolderSsn(String holderSsn) {
        this.holderSsn = holderSsn;
    }

    @Column(name = "BALANCE")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
