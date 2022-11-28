package myboot.app1.web;

import myboot.app1.dao.ActivityRepository;
import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.model.Activity;
import myboot.app1.model.ActivityNature;
import myboot.app1.model.CurriculumVitae;
import myboot.app1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class CurriculumVitaeRestController {
    @Autowired
    CurriculumVitaeRepository curriculumVitaeRepository;
    @Autowired
    ActivityRepository activityRepository;
    @PostConstruct
    public void init() {
        System.out.println("Start " + this);

            Activity activity1 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");
            Activity activity2 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - SID", "cv.com");
            Activity activity3 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IAAA", "cv.com");
            Activity activity4 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IMD", "cv.com");
            Activity activity5 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - GIG", "cv.com");
            activityRepository.saveAll(Arrays.asList(activity1,activity2,activity3,activity4,activity5));
            CurriculumVitae cv1 = new CurriculumVitae("cv1");
            cv1.getActivities().add(activity1);
            cv1.getActivities().add(activity2);
            curriculumVitaeRepository.save(cv1);
            CurriculumVitae cv2 = new CurriculumVitae("cv2");
            cv2.getActivities().add(activity5);
            cv2.getActivities().add(activity3);
            cv2.getActivities().add(activity4);
            curriculumVitaeRepository.save(cv2);
            CurriculumVitae cv3 = new CurriculumVitae("cv3");
            curriculumVitaeRepository.save(cv3);





    }

    @GetMapping("/cvList")
    public Iterable<CurriculumVitae> getCvList(){
        return curriculumVitaeRepository.findAll();
    }

    @GetMapping("/cv/{id}")
    public CurriculumVitae getCurriculumVitae(@PathVariable int id){
         CurriculumVitae cv = curriculumVitaeRepository.findById(id).orElseThrow();
         return cv;
    }


    @GetMapping("/cvActivities/{id}")
    public Iterable<Activity> getCurriculumVitaeActivities(@PathVariable int id){
        CurriculumVitae cv = curriculumVitaeRepository.findById(id).orElseThrow();
        return cv.getActivities();
    }

    @PostMapping("/cv")
    public String postMovie(@RequestBody @Valid CurriculumVitae cv) {
        curriculumVitaeRepository.save(cv);
        return "redirect:/cv/"+cv.getId();
    }

}
