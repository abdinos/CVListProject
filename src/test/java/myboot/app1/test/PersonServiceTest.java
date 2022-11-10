package myboot.app1.test;

import myboot.app1.model.CurriculumVitae;
import myboot.app1.model.Person;
import myboot.app1.service.CurriculumVitaeService;
import myboot.app1.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonService personService;

    Person person;

    @BeforeEach
    void populate() {
         person = new Person("yanis", "amrouche", "99/99/1999", "pale", "yanis@amrouche.fr", "yann.fr", "ssss");
    }



    @Test
    void testSavePerson() {
        Person expected = personService.savePerson(person);
        assertEquals(person.getLastName(),expected.getLastName());
    }

    @Test
    void testGetPerson() {
        personService.savePerson(person);
        Person expected = personService.getPerson(person.getId());
        assertEquals(person.getLastName(),expected.getLastName());
    }

    @Test
    void testDeletePersonById() {
        personService.savePerson(person);

        String expected = personService.deletePersonById(person.getId());
        assertEquals("person deleted "+ person.getId(),expected);
        assertThrows(NoSuchElementException.class,() -> personService.getPerson(person.getId()));
    }


    @Test
    void testUpdatePerson() {
        personService.savePerson(person);
        person.setLastName("ould-chibani");
        Person expected =personService.updatePerson(person);
        assertEquals(person.getLastName(),expected.getLastName());
    }
}