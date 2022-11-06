package myboot.app1.dao;

import myboot.app1.model.Activity;
import myboot.app1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    @Query("select a from Activity a where a.title = ?1")
    List<Activity> getActivitiesByTitle(String title);
}