package ilielucian.demo.bank.bankaccounts.dao.impl;

import ilielucian.demo.bank.bankaccounts.dao.BankAccountDao;
import ilielucian.demo.bank.bankaccounts.domain.BankAccount;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class implementing the {@link BankAccountDao} interface.
 * <p>
 * Created by Lucian Ilie on 13-Aug-15.
 */
@Repository
public class BankAccountDaoImpl implements BankAccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Gets the {@link BankAccount} result of the search into the DB, by <code>id</code>.
     *
     * @param id ID of the bank account
     * @return the {@link BankAccount} with the ID searched, if found; <code>null</code>, if not found
     */
    @Transactional
    public BankAccount getBankAccountById(long id) {

        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(BankAccount.class);
        criteria.add(Restrictions.eq("id", id));

        List<?> results = criteria.list();

        if (results == null || results.size() == 0) {
            return null;
        } else {
            return (BankAccount) results.get(0);
        }
    }
}
