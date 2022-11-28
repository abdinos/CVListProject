package myboot.app1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class CurriculumVitae {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column
    String cvName;

    @OneToOne(targetEntity=Person.class)
    @JoinColumn(name="person_id")
    Person person;

    @OneToMany(targetEntity=Activity.class,cascade={CascadeType.MERGE},orphanRemoval=true)
    private List<Activity> activities = new ArrayList<>();


    public CurriculumVitae(String cvName, Person person) {
        this.cvName = cvName;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
