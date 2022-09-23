package myboot.app1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    public class Activity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        @Basic
        @Column
        private int year;
        @Basic
        @Column
        private String nature;
        @Basic
        @Column
        private String title;
        @Basic
        @Column
        private String description;
        @Basic
        @Column
        private String website;

        public Activity(int year, String nature, String title, String description, String website) {
            this.year = year;
            this.nature = nature;
            this.title = title;
            this.description = description;
            this.website = website;
        }

        public Activity() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
}
