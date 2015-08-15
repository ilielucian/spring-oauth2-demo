package ilielucian.demo.bank.users.service.impl;

import ilielucian.demo.bank.users.dao.UserDao;
import ilielucian.demo.bank.users.domain.User;
import ilielucian.demo.bank.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lucian Ilie on 15-Aug-15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User findUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
