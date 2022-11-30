package myboot.app1.dao;

import myboot.app1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends CrudRepository<Person,Long> {

    @Query("select p from Person p where p.firstName LIKE ?1")
    List<Person> getPersonByFirstName(String firstName);
    @Query("select p from Person p where p.lastName LIKE ?1")
    List<Person> getPersonByLastName(String lastName);
    @Query("select p from Person p where p.email LIKE ?1")
    List<Person> getPersonByEmail(String email);

    public Person findPersonByEmail(String email);

}

