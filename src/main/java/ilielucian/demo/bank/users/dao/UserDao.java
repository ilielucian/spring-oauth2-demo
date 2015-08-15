package ilielucian.demo.bank.users.dao;

import ilielucian.demo.bank.users.domain.User;

/**
 * Created by Lucian Ilie on 15-Aug-15.
 */
public interface UserDao {

    User getUserByUsername(String username);

}
