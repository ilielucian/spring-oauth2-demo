package ilielucian.demo.bank.users.dao.impl;

import ilielucian.demo.bank.users.dao.UserDao;
import ilielucian.demo.bank.users.domain.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Created by Lucian Ilie on 15-Aug-15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User getUserByUsername(String username) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(User.class).setCacheable(true).setCacheRegion("getUserByUsername");
        criteria.add(Restrictions.like("username", username, MatchMode.EXACT));

        List<?> results = criteria.list();

        if (results.size() > 0) {
            return (User) results.get(0);
        } else {
            return null;
        }
    }

}
