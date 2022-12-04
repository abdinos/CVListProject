package myboot.app1.service;

import com.github.javafaker.Faker;
import jdk.dynalink.linker.LinkerServices;
import myboot.app1.dao.ActivityRepository;
import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.dao.PersonRepository;
import myboot.app1.dao.XUserRepository;
import myboot.app1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PopulationService extends Thread {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    CurriculumVitaeRepository curriculumVitaeRepository;

    @Autowired
    XUserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;




    @PostConstruct
    public void init() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {

            @Override
            public void run() {

                System.out.println("Start " + this);
                Faker faker = new Faker();
                for (int i = 0; i < 100; i++) {
                    Person person = new Person();
                    person.setFirstName(faker.name().firstName());
                    person.setLastName(faker.name().lastName());
                    person.setBirthDate(faker.date().birthday().toString());
                    person.setAdress(faker.address().fullAddress());
                    person.setEmail(person.getFirstName() + person.getLastName() + "@gmail.com");
                    person.setWebsite(person.getFirstName() + "." + person.getLastName() + ".com");
                    person.setPassword(person.getFirstName() + "123");
                    personRepository.save(person);
                    CurriculumVitae curriculumVitae = new CurriculumVitae("cv" + i, person);
                    Activity activity1 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");
                    Activity activity2 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - SID", "cv.com");
                    Activity activity3 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IAAA", "cv.com");
                    Activity activity4 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IMD", "cv.com");
                    Activity activity5 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - GIG", "cv.com");
                    activityRepository.saveAll(Arrays.asList(activity1, activity2, activity3, activity4, activity5));
                    curriculumVitae.getActivities().add(activity1);
                    curriculumVitae.getActivities().add(activity2);
                    curriculumVitae.getActivities().add(activity3);
                    curriculumVitae.getActivities().add(activity4);
                    curriculumVitae.getActivities().add(activity5);
                    curriculumVitaeRepository.save(curriculumVitae);
                    person.setCurriculumVitae(curriculumVitae);
                    XUser xUser = new XUser(person.getEmail(), passwordEncoder.encode(person.getPassword()));
                    userRepository.save(xUser);
                    personRepository.save(person);
                    System.out.println("firstname : "+ person.getFirstName()+" --------- Lastname : " +person.getLastName());
                }


            }});
        executorService.shutdown();



    }
}
