package ilielucian.demo.bank.users.service;

import ilielucian.demo.bank.users.dao.UserDao;
import ilielucian.demo.bank.users.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Custom service for fetching Spring Security {@link User}s.
 * <p/>
 * Created by Lucian Ilie.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Fetches a Spring Security {@link User} using the application {@link UserDao}.
     *
     * @param username the username to be fetched, as <code>String</code>
     * @return the Spring Security {@link User}
     */
    @Transactional
    public User loadUserByUsername(String username) {
        ilielucian.demo.bank.users.domain.User user = userDao.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        List<GrantedAuthority> grantedAuthorities =
                buildUserAuthority(user.getUserRoleSet());

        return buildUserForAuthentication(user, grantedAuthorities);
    }

    /**
     * Builds a Spring Security {@link User}.
     *
     * @param user the application {@link ilielucian.demo.bank.users.domain.User}
     * @param grantedAuthorities list of Spring {@link GrantedAuthority}
     * @return a Spring Security {@link User} built upon passed parameters
     */
    public User buildUserForAuthentication(
            ilielucian.demo.bank.users.domain.User user,
            List<GrantedAuthority> grantedAuthorities) {

        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, grantedAuthorities);
    }

    /**
     * Builds a list of Spring Security {@link GrantedAuthority} from a set of
     * application {@link UserRole}s.
     *
     * @param userRoles set of {@link UserRole}s
     * @return list of {@link GrantedAuthority} for the passed parameter
     */
    public List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRole userRole : userRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return new ArrayList<>(grantedAuthorities);
    }
}
