package myboot.app1.web;

import myboot.app1.dao.ActivityRepository;
import myboot.app1.model.Activity;
import myboot.app1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
