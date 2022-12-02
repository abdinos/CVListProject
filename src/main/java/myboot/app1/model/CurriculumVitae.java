package myboot.app1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToOne(targetEntity=Person.class,cascade={CascadeType.MERGE},orphanRemoval=true)
    @JoinColumn(name="person_id")
    @JsonManagedReference
    Person person;

    @OneToMany(targetEntity=Activity.class,cascade={CascadeType.MERGE},orphanRemoval=true)
    @JsonManagedReference
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
