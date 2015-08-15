package ilielucian.demo.bank.users.service;

import ilielucian.demo.bank.users.domain.User;

/**
 * Created by Lucian Ilie on 15-Aug-15.
 */
public interface UserService {

    User findUserByUsername(String username);

}
