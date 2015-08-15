package ilielucian.demo.bankaccounts.rest.dao.impl;

import ilielucian.demo.bankaccounts.rest.dao.BankAccountDao;
import ilielucian.demo.bankaccounts.rest.domain.BankAccount;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lucian Ilie on 13-Aug-15.
 */
@Repository
public class BankAccountDaoImpl implements BankAccountDao {

	@Autowired
	private SessionFactory sessionFactory;

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
