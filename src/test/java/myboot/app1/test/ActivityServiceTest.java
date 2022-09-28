package myboot.app1.test;

import myboot.app1.dao.ActivityRepository;
import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.model.Activity;
import myboot.app1.model.ActivityNature;
import myboot.app1.model.CurriculumVitae;
import myboot.app1.service.ActivityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ActivityServiceTest {
    @Autowired
    ActivityService activityService;

    @Test
    public void testCreateActivity() {
        Activity activity = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");
        Activity expected = activityService.saveActivity(activity);
        assertEquals(ActivityNature.FORMATION, expected.getNature());
    }

    @Test
    public void testDeleteActivity() {
        Activity activity = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");
        Activity expected = activityService.saveActivity(activity);
        assertEquals(ActivityNature.FORMATION, expected.getNature());
        String expectedMessage = activityService.deleteActivity(expected.getId());
        assertEquals("activity deleted " + expected.getId(), expectedMessage);
        assertThrows(NoSuchElementException.class,() ->{
            activityService.getActivity(activity.getId());

        });
    }

    @Test
    public void testUpdateActivity() {
        Activity activity = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");
        activityService.saveActivity(activity);
        activity.setYear(2021);
        Activity expected = activityService.updateActivity(activity);
        assertEquals(2021, expected.getYear());

    }

    @Test
    public void testGetActivity() {
        Activity activity = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");
        activityService.saveActivity(activity);
        Activity expected = activityService.getActivity(activity.getId());
        assertEquals(activity.getYear(), expected.getYear());
    }
}