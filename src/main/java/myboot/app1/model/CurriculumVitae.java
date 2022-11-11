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

    @OneToMany(targetEntity=Activity.class,cascade={CascadeType.MERGE},orphanRemoval=true)
    private List<Activity> activities = new ArrayList<>();


    public CurriculumVitae(String cvName) {
        this.cvName = cvName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
