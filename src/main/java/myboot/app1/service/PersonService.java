package myboot.app1.service;

import myboot.app1.dao.ActivityRepository;
import myboot.app1.dao.PersonRepository;
import myboot.app1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Person savePerson(Person person){
        return personRepository.save(person);
    }

    public Person getPerson(Long id){
        return personRepository.findById((long) id).get();
    }

    public String deletePersonById(Long id){
        personRepository.deleteById( id);
        return "person deleted "+id;
    }

    public void deletePerson(Person person){
        personRepository.delete(person);
    }
    public Person updatePerson(Person person){
        if(personRepository.findById(person.getId()).isEmpty()){
            personRepository.save(person);
        }
        Person p = personRepository.findById(person.getId()).get();
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setBirthDate(person.getBirthDate());
        p.setAdress(person.getAdress());
        p.setWebsite(person.getWebsite());
        p.setPassword(person.getPassword());
        personRepository.save(p);
        return p;


    }




}
