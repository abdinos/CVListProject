package myboot.app1.web;

import myboot.app1.dao.ActivityRepository;
import myboot.app1.model.Activity;
import myboot.app1.model.ActivityNature;
import myboot.app1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ActivityRestController {
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    public ActivityRestController(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("Start " + this);
        if (activityRepository.count() == 0) {
            Activity activity = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");
            Activity activity2 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - SID", "cv.com");
            Activity activity3 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IAAA", "cv.com");
            Activity activity4 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IMD", "cv.com");
            Activity activity5 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - GIG", "cv.com");

            activityRepository.saveAll(Arrays.asList(activity,activity2,activity3,activity4,activity5));
        }
    }
    @GetMapping("/activities")
    public Iterable<Activity> getActivities(@RequestParam(value = "title", required = false) String title){
        if(title != null){
            return activityRepository.getActivitiesByTitle(title);
        }
        return activityRepository.findAll();
    }

    @GetMapping("/activities/{id}")
    public Activity getActivity(@PathVariable int id){
        return activityRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/activities/{id}")
    public void deleteaActivity(@PathVariable int id){
        activityRepository.deleteById(id);
    }

    @PostMapping("/activities")
    public void postActivity(Activity activity){
        activityRepository.save(activity);
    }

    @PutMapping("/activities/{id}")
    public void putActivity(Activity activity, @PathVariable int id) throws Exception {
        Optional<Activity> a = activityRepository.findById(id);
        if (a.isPresent()){
            activityRepository.save(activity);
        }else {
            throw new Exception("Activity Not Found");
        }


    }
}
