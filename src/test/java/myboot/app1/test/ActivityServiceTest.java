package myboot.app1.test;

import myboot.app1.model.Activity;
import myboot.app1.model.ActivityNature;
import myboot.app1.model.Person;
import myboot.app1.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class ActivityServiceTest {
    @Autowired
    ActivityService activityService;
    Activity activity;


    @BeforeEach
    void populate(){
        Person owner = new Person();
        activity = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");

    }

    @Test
    public void testCreateActivity() {

        Activity expected = activityService.saveActivity(activity);
        assertEquals(ActivityNature.FORMATION, expected.getNature());
    }

    @Test
    public void testDeleteActivity() {
        Activity expected = activityService.saveActivity(activity);
        assertEquals(ActivityNature.FORMATION, expected.getNature());
        String expectedMessage = activityService.deleteActivity(expected.getId());
        assertEquals("activity deleted " + expected.getId(), expectedMessage);
        assertThrows(NoSuchElementException.class,() -> activityService.getActivity(activity.getId()));
    }

    @Test
    public void testUpdateActivity() {
        activityService.saveActivity(activity);
        activity.setYear(2021);
        Activity expected = activityService.updateActivity(activity);
        assertEquals(2021, expected.getYear());

    }

    @Test
    public void testGetActivity() {
        activityService.saveActivity(activity);
        Activity expected = activityService.getActivity(activity.getId());
        assertEquals(activity.getYear(), expected.getYear());
    }
}