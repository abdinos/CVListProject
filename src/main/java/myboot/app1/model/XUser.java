package myboot.app1.model;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XUser {

    @Id
    String username;

    @Basic
    String password;

    @ElementCollection(fetch = FetchType.EAGER)
    Set<String> roles;

    @OneToOne(targetEntity=Person.class,cascade={CascadeType.MERGE},orphanRemoval=true)
    Person person;

    public XUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}