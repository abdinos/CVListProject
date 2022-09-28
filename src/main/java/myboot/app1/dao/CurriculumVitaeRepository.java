package myboot.app1.dao;

import myboot.app1.model.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CurriculumVitaeRepository extends JpaRepository<CurriculumVitae, Integer> {

}
