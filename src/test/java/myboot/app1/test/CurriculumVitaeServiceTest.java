package myboot.app1.test;

import myboot.app1.model.Activity;
import myboot.app1.model.ActivityNature;
import myboot.app1.model.CurriculumVitae;
import myboot.app1.model.Person;
import myboot.app1.service.ActivityService;
import myboot.app1.service.CurriculumVitaeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class CurriculumVitaeServiceTest {

    @Autowired
    CurriculumVitaeService curriculumVitaeService;
    @Autowired
    ActivityService activityService;
    Activity activity1;
    Activity activity2;
    Activity activity3;

    @BeforeEach
    void populate(){

        activity1 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");
       activity2= new Activity(2021, ActivityNature.EXPERIENCE, "Stage M1", "Stage M1 IDL", "cv.com");
       activity3 =  new Activity(2023, ActivityNature.AUTRE, "M2-info", "formation : M2 - IDL", "cv.com");
        activityService.saveActivity(activity1);
        activityService.saveActivity(activity2);
        activityService.saveActivity(activity3);


    }


    @Test
    void testSaveActivity() {
        CurriculumVitae cv = new CurriculumVitae();
        cv.getActivities().add(activity1);
        cv.getActivities().add(activity2);
        CurriculumVitae expected = curriculumVitaeService.saveCV(cv);
        assertEquals(expected.getActivities(),cv.getActivities());
    }

        @Test
        void testGetCV () {
            CurriculumVitae cv = new CurriculumVitae();
            cv.getActivities().add(activity1);
            cv.getActivities().add(activity2);
            curriculumVitaeService.saveCV(cv);
            CurriculumVitae expected = curriculumVitaeService.getCV(cv.getId());
            assertEquals(expected.getActivities().get(0),cv.getActivities().get(0));
        }


        @Test
        void testDeleteCV () {
            CurriculumVitae cv = new CurriculumVitae();
            cv.getActivities().add(activity1);
            cv.getActivities().add(activity2);
            curriculumVitaeService.saveCV(cv);
            String expected = curriculumVitaeService.deleteCV(cv.getId());
            assertEquals("CV deleted " + cv.getId(),expected);
            assertThrows(NoSuchElementException.class,() -> curriculumVitaeService.getCV(cv.getId()));
        }

        @Test
        void testUpdateCV() {
            CurriculumVitae cv = new CurriculumVitae();
            cv.getActivities().add(activity1);
            cv.getActivities().add(activity2);
            cv.getActivities().add(activity3);
            curriculumVitaeService.saveCV(cv);
            ArrayList<Activity> updatedActivities = new ArrayList<>();
            updatedActivities.add(activity1);
            updatedActivities.add(activity2);
            updatedActivities.add(activity3);
            cv.setActivities(updatedActivities);
            curriculumVitaeService.updateCV(cv);
            assertEquals(activity3,cv.getActivities().get(2));
        }
}