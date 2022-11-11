package myboot.app1.dao;

import myboot.app1.model.Activity;
import myboot.app1.model.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CurriculumVitaeRepository extends JpaRepository<CurriculumVitae, Integer> {
    @Query("select c from CurriculumVitae c where c.cvName LIKE ?1")
    List<CurriculumVitae> getCurriculumVitaeByName(String cvName);
}
