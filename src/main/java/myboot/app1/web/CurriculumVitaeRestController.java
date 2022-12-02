package myboot.app1.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import myboot.app1.dao.ActivityRepository;
import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.dao.PersonRepository;
import myboot.app1.dao.XUserRepository;
import myboot.app1.model.*;
import myboot.app1.service.CurriculumVitaeService;
import myboot.app1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.*;

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

   @Autowired
    CurriculumVitaeService curriculumVitaeService;

    @Autowired
    LocalValidatorFactoryBean validationFactory;

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
        XUser xUser1 = new XUser(person1.getEmail(), passwordEncoder.encode(person1.getPassword()), roles1);
        XUser xUser2 = new XUser(person2.getEmail(), passwordEncoder.encode(person2.getPassword()), roles2);
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
        person1.setCurriculumVitae(cv1);
        person2.setCurriculumVitae(cv2);
        personRepository.saveAll(Arrays.asList(person1,person2));


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
    @GetMapping("/profileCv")
    public  CurriculumVitae getUserCv(){
        CurriculumVitae curriculumVitae = curriculumVitaeService.getCurrentUserCv();
        return curriculumVitae;

    }

    public Set<ConstraintViolation<Activity>> validate(Activity cv) {

        Set<ConstraintViolation<Activity>> violations = validationFactory.getValidator().validate(cv);
        return violations;
    }

    @PostMapping("/profileActivities")
    public Map<String, String> postActivity(@RequestBody Activity m) {
        CurriculumVitae curriculumVitae = curriculumVitaeService.getCurrentUserCv();
        curriculumVitae.getActivities().add(m);
        curriculumVitaeRepository.save(curriculumVitae);
        Set<ConstraintViolation<Activity>> violations = validate(m);
        if (violations.isEmpty()) {
            activityRepository.save(m);
        }

        Map<String, String> errors = new HashMap<>();
        violations.forEach((violation) -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
    @PutMapping ("/profileCv/{id}")
    public  Map<String, String> updateUserCv(@RequestBody Activity activity, @PathVariable int id){

// validating user movie
        Set<ConstraintViolation<Activity>> violations = validate(activity);
        if (violations.isEmpty()) {
            Optional<Activity> a = activityRepository.findById(id);
            if (a.isPresent()) {
                activityRepository.save(activity);
            } else {
                throw new NoSuchElementException("no cv");
            }
        }

        Map<String, String> errors = new HashMap<>();
        violations.forEach((violation) -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
    @GetMapping("/profileActivities")
    public List<Activity> getUserActivities(){
        CurriculumVitae curriculumVitae = curriculumVitaeService.getCurrentUserCv();
        return curriculumVitae.getActivities();
    }


}