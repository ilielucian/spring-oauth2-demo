package ilielucian.demo.bank.users.dao;

import ilielucian.demo.bank.users.domain.User;

/**
 * DAO for getting a User from the DB.
 * <p/>
 * Created by Lucian Ilie on 15-Aug-15.
 */
public interface UserDao {

    /**
     * Gets a {@link User} object from the DB.
     *
     * @param username the user name to get, as <code>String</code>
     * @return the {@link User} object with the username given as parameter, if found;<br/>
     *         <code>null</code>, if not found.
     */
    User getUserByUsername(String username);

}
