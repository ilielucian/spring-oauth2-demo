package ilielucian.demo.bank.users.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class representing a User Role object in an authorization level sense.
 * <p>
 * Created by Lucian Ilie.
 */
@Entity
@Table(name = "user_roles", uniqueConstraints =
@UniqueConstraint(columnNames = {"role", "username"}))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "userRole")
@SuppressWarnings("unused")
public class UserRole {

    private int userRoleId;
    private User user;
    private String role;

    public UserRole() {
    }

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_ID", unique = true, nullable = false)
    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "role", nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
