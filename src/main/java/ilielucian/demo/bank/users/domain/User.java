package ilielucian.demo.bank.users.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Lucian Ilie on 15-Aug-15.
 */
@Entity
@Table(name = "users")
public class User {

    private String username;

    @Id
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
