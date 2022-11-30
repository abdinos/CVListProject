package myboot.app1.web;

import myboot.app1.dao.ActivityRepository;
import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.dao.PersonRepository;
import myboot.app1.dao.XUserRepository;
import myboot.app1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CurriculumVitaeRestController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CurriculumVitaeRepository curriculumVitaeRepository;
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    XUserRepository userRepository;


    @PostConstruct
    public void init() {
        System.out.println("Start " + this);


        Person person1 = new Person("yanis", "amer", "", "", "aaa@aaa", "", "aaa");
        Person person2 = new Person("abdessettar", "ould", "", "", "bbb@bbb", "", "bbb");
        Person person3 = new Person("XXX", "YYY", "", "", "", "", "");
        Set<String> roles1 = new HashSet<String>();
        Set<String> roles2 = new HashSet<String>();
        roles1.add("USER");
        roles2.add("USER");
        XUser xUser1 = new XUser(person1.getEmail(), passwordEncoder.encode(person1.getPassword()), roles1,person1);
        XUser xUser2 = new XUser(person2.getEmail(), passwordEncoder.encode(person2.getPassword()), roles2,person2);
        personRepository.saveAll(Arrays.asList(person1, person2, person3));
        userRepository.saveAll(Arrays.asList(xUser1, xUser2));


        Activity activity1 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");
        Activity activity2 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - SID", "cv.com");
        Activity activity3 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IAAA", "cv.com");
        Activity activity4 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IMD", "cv.com");
        Activity activity5 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - GIG", "cv.com");
        activityRepository.saveAll(Arrays.asList(activity1, activity2, activity3, activity4, activity5));
        CurriculumVitae cv1 = new CurriculumVitae("cv1", person1);
        cv1.getActivities().add(activity1);
        cv1.getActivities().add(activity2);
        curriculumVitaeRepository.save(cv1);
        CurriculumVitae cv2 = new CurriculumVitae("cv2", person2);
        cv2.getActivities().add(activity5);
        cv2.getActivities().add(activity3);
        cv2.getActivities().add(activity4);
        curriculumVitaeRepository.save(cv2);
        CurriculumVitae cv3 = new CurriculumVitae("cv3", person3);
        curriculumVitaeRepository.save(cv3);


    }


    @GetMapping("/cvList")
    public Iterable<CurriculumVitae> getCvList() {
        return curriculumVitaeRepository.findAll();
    }

    @GetMapping("/cv/{id}")
    public CurriculumVitae getCurriculumVitae(@PathVariable int id) {
        CurriculumVitae cv = curriculumVitaeRepository.findById(id).orElseThrow();
        return cv;
    }


    @GetMapping("/cvActivities/{id}")
    public Iterable<Activity> getCurriculumVitaeActivities(@PathVariable int id) {
        CurriculumVitae cv = curriculumVitaeRepository.findById(id).orElseThrow();
        return cv.getActivities();
    }

    @PostMapping("/cv")
    public String postMovie(@RequestBody @Valid CurriculumVitae cv) {
        curriculumVitaeRepository.save(cv);
        return "redirect:/cv/" + cv.getId();
    }
}