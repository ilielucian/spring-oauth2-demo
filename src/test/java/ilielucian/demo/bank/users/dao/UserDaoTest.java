package ilielucian.demo.bank.users.dao;

import ilielucian.demo.bank.users.domain.User;
import ilielucian.demo.bank.users.domain.UserRole;
import ilielucian.demo.bank.users.service.UserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * Created by Lucian Ilie on 29-Aug-15.
 */
public class UserDaoTest {

    private static final String USERNAME;
    private static final User EXPECTED_USER;
    private static final UserRole EXPECTED_USER_ROLE;
    private static final Set<UserRole> EXPECTED_USER_ROLES;

    /**
     * Build the User & User Roles mocks.
     */
    static {
        USERNAME = "lucica";

        EXPECTED_USER = new User();
        EXPECTED_USER.setUsername(USERNAME);
        EXPECTED_USER.setPassword("wysiwyg");
        EXPECTED_USER.setEnabled(true);

        EXPECTED_USER_ROLE = new UserRole();
        EXPECTED_USER_ROLE.setUserRoleId(132);
        EXPECTED_USER_ROLE.setUser(EXPECTED_USER);
        EXPECTED_USER_ROLE.setRole("ROLE_USER");
        EXPECTED_USER_ROLES = new HashSet<>();
        EXPECTED_USER_ROLES.add(EXPECTED_USER_ROLE);

        EXPECTED_USER.setUserRoleSet(EXPECTED_USER_ROLES);
    }

    private UserDao userDaoMock;
    private UserDetailsServiceImpl userServiceMock;

    @Before
    public void setUp() {
        userDaoMock = Mockito.mock(UserDao.class);
        userServiceMock = new UserDetailsServiceImpl(userDaoMock);
    }

    @Test
    public void loadUserByUsername_ExistingUser() {
        // build Spring User Details from the User entity
        List<GrantedAuthority> expectedAuthority = userServiceMock.buildUserAuthority(EXPECTED_USER_ROLES);
        org.springframework.security.core.userdetails.User expectedUserDetails =
                userServiceMock.buildUserForAuthentication(EXPECTED_USER, expectedAuthority);

        // mock a return object from dao
        when(userDaoMock.getUserByUsername(USERNAME)).thenReturn(EXPECTED_USER);

        // call the service
        org.springframework.security.core.userdetails.User foundUserDetails =
                userServiceMock.loadUserByUsername(USERNAME);

        // verify the results
        verify(userDaoMock, times(1)).getUserByUsername(USERNAME);
        verifyNoMoreInteractions(userDaoMock);

        assertThat(foundUserDetails, is(expectedUserDetails));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_NonExistingUser() {
        when(userDaoMock.getUserByUsername(USERNAME)).thenReturn(null);

        userServiceMock.loadUserByUsername(USERNAME);

        verify(userDaoMock, times(1)).getUserByUsername(USERNAME);
        verifyNoMoreInteractions(userDaoMock);
    }
}
