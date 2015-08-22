package ilielucian.demo.bank.users.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a User object.
 * <p/>
 * Created by Lucian Ilie on 15-Aug-15.
 */
@Entity
@Table(name = "users")
public class User {

    private String username;
    private String password;
    private boolean enabled;
    private Set<UserRole> userRoleSet = new HashSet<>(1);

    @Id
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ENABLED")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(mappedBy = "user")
    public Set<UserRole> getUserRoleSet() {
        return userRoleSet;
    }

    public void setUserRoleSet(Set<UserRole> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }
}
