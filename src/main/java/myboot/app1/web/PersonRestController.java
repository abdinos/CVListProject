package myboot.app1.web;


import myboot.app1.dao.PersonRepository;
import myboot.app1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.servlet.oauth2.client.OAuth2ClientSecurityMarker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RequestMapping("/api/")
@RestController
public class PersonRestController {


    @Autowired
    PersonRepository personRepository;

    @PostConstruct
    public void init() {
        System.out.println("Start " + this);
        if (personRepository.count() == 0) {
            personRepository.save(new Person("Abdessattar","Despair","2024","","","",""));
            personRepository.save(new Person("Yanis","Arsenic","2024","","","",""));
        }
    }


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
    void deleteMovie(@PathVariable long id) {
        personRepository.deleteById(id);
    }

    @PostMapping("/persons")
    public Person postMovie(@RequestBody @Valid Person m) {
        personRepository.save(m);
        return m;
    }







}
