package myboot.app1.dao;

import myboot.app1.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer>{

}
