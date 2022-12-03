package myboot.app1.web;


import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.dao.PersonRepository;
import myboot.app1.dao.XUserRepository;
import myboot.app1.model.Activity;
import myboot.app1.model.CurriculumVitae;
import myboot.app1.model.Person;
import myboot.app1.model.XUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.servlet.oauth2.client.OAuth2ClientSecurityMarker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.*;

@RequestMapping("/api/")
@RestController
public class PersonRestController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    CurriculumVitaeRepository curriculumVitaeRepository;

    @Autowired
    LocalValidatorFactoryBean validationFactory;

    @Autowired
    XUserRepository xUserRepository;


    @GetMapping(value = "/persons")
    public Iterable<Person>  getPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    @DeleteMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePerson(@PathVariable long id) {
        personRepository.deleteById(id);
    }

    public Set<ConstraintViolation<Person>> validate(Person p) {

        Set<ConstraintViolation<Person>> violations = validationFactory.getValidator().validate(p);
        return violations;
    }

    @PostMapping("/persons")
    public Map<String, String> postPerson(@RequestBody Person p) {
        Set<ConstraintViolation<Person>> violations = validate(p);
        if (violations.isEmpty()) {
            personRepository.save(p);
        }

        Map<String, String> errors = new HashMap<>();
        violations.forEach((violation) -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        CurriculumVitae curriculumVitae =  new CurriculumVitae("cv000",p);
        p.setCurriculumVitae(curriculumVitae);
        curriculumVitaeRepository.save(curriculumVitae);
        XUser xuser = new XUser(p.getEmail(), passwordEncoder.encode(p.getPassword()));
        xUserRepository.save(xuser);



        return errors;

    }








}
