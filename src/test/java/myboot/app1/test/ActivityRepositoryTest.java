package myboot.app1.test;

import myboot.app1.dao.ActivityRepository;
import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.model.Activity;
import myboot.app1.model.CurriculumVitae;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ActivityRepositoryTest {
    @Autowired
    ActivityRepository activityRepo ;
    @Autowired
    CurriculumVitaeRepository cvRepo ;

    @Test
    public void testCreate() {
        var m = activityRepo.save(new Activity(2022,"form","M2-info", "formation : M2 - IDL","cv.com"));
        var m2 = activityRepo.findById(m.getId());
        assertEquals(m2.get().getTitle(), "M2-info");
    }

    @Test
    public void testCreateCV() {
        var s2 = activityRepo.save(new Activity(2022,"stage","Stage2", "formation : M2 - IDL","cv.com"));
        var m2 = activityRepo.save(new Activity(2022,"form","M2-info", "formation : M2 - IDL","cv.com"));
        Collection<Activity> acfol = new ArrayList<>();
        acfol.add(s2);
        acfol.add(m2);
        CurriculumVitae c = new CurriculumVitae();
        c.getActivities().addAll(acfol);
        var cv = cvRepo.save(c);
        assertEquals(cv.getActivities().get(1).getTitle(), "M2-info");
        assertEquals(cv.getActivities().get(0).getTitle(), "Stage2");
    }



}