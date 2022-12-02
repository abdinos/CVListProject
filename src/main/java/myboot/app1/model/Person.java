package myboot.app1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
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

    @OneToOne(targetEntity=CurriculumVitae.class,cascade={CascadeType.MERGE},orphanRemoval=true)
    @JsonBackReference
    CurriculumVitae curriculumVitae;



    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public Person( String firstName, String lastName, String birthDate, String adress, String email, String website, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.adress = adress;
        this.email = email;
        this.website = website;
        this.password = password;
    }
}
