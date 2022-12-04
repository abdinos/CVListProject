package myboot.app1.web;

import myboot.app1.dao.ActivityRepository;
import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.dao.PersonRepository;
import myboot.app1.dao.XUserRepository;
import myboot.app1.model.Activity;
import myboot.app1.model.CurriculumVitae;
import myboot.app1.model.Person;
import myboot.app1.service.CurriculumVitaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping("/cvList")
    public Iterable<CurriculumVitae> getCvList() {
        return curriculumVitaeRepository.findAll();
    }

    @GetMapping("/cv/{id}")
    public CurriculumVitae getCurriculumVitae(@PathVariable int id) {
        return curriculumVitaeRepository.findById(id).orElseThrow();
    }


    @GetMapping("/cvActivities/{id}")
    public Iterable<Activity> getCurriculumVitaeActivities(@PathVariable int id) {
        CurriculumVitae cv = curriculumVitaeRepository.findById(id).orElseThrow();
        return cv.getActivities();
    }

    @PostMapping("/cv")
    public String postCV(@RequestBody @Valid CurriculumVitae cv) {
        curriculumVitaeRepository.save(cv);
        return "redirect:/cv/" + cv.getId();
    }
    @GetMapping("/profileCv")
    public  CurriculumVitae getUserCv(){
        return curriculumVitaeService.getCurrentUserCv();

    }

    public Set<ConstraintViolation<Activity>> validate(Activity cv) {

        return validationFactory.getValidator().validate(cv);
    }

    @PostMapping("/profileActivities")
    public Map<String, String> postActivity(@RequestBody @Valid Activity m) {
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

    @GetMapping("/userInfo")
    public List<String> getUserInfo(){
        CurriculumVitae curriculumVitae = curriculumVitaeService.getCurrentUserCv();
        Person curentPerson = curriculumVitae.getPerson();
        return Arrays.asList(curentPerson.getFirstName(), curentPerson.getLastName(), curentPerson.getEmail(), curentPerson.getAdress(),
                curentPerson.getBirthDate(), curentPerson.getWebsite());
    }



}