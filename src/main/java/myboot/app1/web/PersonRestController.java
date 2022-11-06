package myboot.app1.web;
import myboot.app1.dao.PersonRepository;
import myboot.app1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.webjars.NotFoundException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonRestController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    LocalValidatorFactoryBean validatorFactoryBean;

    @Autowired
    public PersonRestController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public Iterable<Person> getPersons(@RequestParam (value = "name", required = false) String name){
        if(name != null){
            List<Person> personByFirstName = personRepository.getPersonByFirstName(name);
            List<Person> personByLastName = personRepository.getPersonByLastName(name);
            if (!personByFirstName.isEmpty()){
                return personByFirstName;
            }else if(!personByLastName.isEmpty()){
                return personByLastName;
            }
        }
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable Long id){
        return personRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable Long id){
        personRepository.deleteById(id);
    }

    @PostMapping("/persons")
    public void postPerson(Person person){
        personRepository.save(person);
    }

    @PutMapping("/persons/{id}")
    public void putPerson(Person person, @PathVariable Long id) throws Exception {
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()){
            personRepository.save(person);
        }else {
            throw new Exception("Person Not Found");
        }


    }

}
