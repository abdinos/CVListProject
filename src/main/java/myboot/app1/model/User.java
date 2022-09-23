package myboot.app1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column
    private Long id;
    @Basic
    @Column
    private String firstName;
    @Basic
    @Column
    private String lastName;
    @Basic
    @Column
    private String birthDate;
    @Basic
    @Column
    private String adress;
    @Basic
    @Column
    private String email;
    @Basic
    @Column
    private String website;
    @Basic
    @Column
    private String password;

    public Long getId() {
        return id;
    }

}
