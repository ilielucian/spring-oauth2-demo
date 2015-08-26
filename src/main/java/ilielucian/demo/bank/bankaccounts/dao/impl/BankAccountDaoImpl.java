package ilielucian.demo.bank.bankaccounts.dao.impl;

import ilielucian.demo.bank.bankaccounts.dao.BankAccountDao;
import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class implementing the {@link BankAccountDao} interface.
 * <p>
 * Created by Lucian Ilie on 13-Aug-15.
 */
@Repository
public class BankAccountDaoImpl implements BankAccountDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Gets the {@link BankAccount} result of the search into the DB, by <code>id</code>.
     *
     * @param id ID of the bank account
     * @return the {@link BankAccount} with the ID searched, if found;<br/>
     *         <code>null</code>, if not found
     */
    public BankAccount getBankAccountById(long id) {
        LOGGER.debug("Searching for bank account with id = {}", id);

        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(BankAccount.class);
        criteria.add(Restrictions.eq("id", id));

        List<?> results = criteria.list();

        if (results.size() > 0) {
            LOGGER.debug("Found bank account with id = {}", id);
            return (BankAccount) results.get(0);
        } else {
            LOGGER.debug("Not found bank account with id = {}", id);
            return null;
        }
    }
}
