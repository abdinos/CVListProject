package myboot.app1.service;

import com.github.javafaker.Faker;
import myboot.app1.dao.ActivityRepository;
import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.dao.PersonRepository;
import myboot.app1.dao.XUserRepository;
import myboot.app1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PopulationService {

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

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println("Start " + this);
                Faker faker = new Faker();


                for (int i = 0; i < 100000; i++) {
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
                    personRepository.save(person);
                    System.out.println("firstname : " + person.getFirstName() + " --------- Lastname : " + person.getLastName());
                }

                Person person1 = new Person("Yanis", "Amerouche", "01/01/1931", "Opale", "yanis@gmail", "yanis.com", "yani");
                Person person2 = new Person("Amerouche", "Ould-Chibani", "07/08/2000", "Canada", "abdessettar@gmail.com", "abdessettar.com", "abdessettar");
                XUser xUser1 = new XUser(person1.getEmail(), passwordEncoder.encode(person1.getPassword()));
                XUser xUser2 = new XUser(person2.getEmail(), passwordEncoder.encode(person2.getPassword()));
                personRepository.saveAll(Arrays.asList(person1, person2));
                userRepository.saveAll(Arrays.asList(xUser1, xUser2));


                Activity activity1 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IDL", "cv.com");
                Activity activity2 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - SID", "cv.com");
                Activity activity3 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IAAA", "cv.com");
                Activity activity4 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - IMD", "cv.com");
                Activity activity5 = new Activity(2022, ActivityNature.FORMATION, "M2-info", "formation : M2 - GIG", "cv.com");
                activityRepository.saveAll(Arrays.asList(activity1, activity2, activity3, activity4, activity5));
                CurriculumVitae cv1 = new CurriculumVitae("cvA", person1);
                cv1.getActivities().add(activity1);
                cv1.getActivities().add(activity2);
                curriculumVitaeRepository.save(cv1);
                CurriculumVitae cv2 = new CurriculumVitae("cvB", person2);
                cv2.getActivities().add(activity5);
                cv2.getActivities().add(activity3);
                cv2.getActivities().add(activity4);
                curriculumVitaeRepository.save(cv2);
                person1.setCurriculumVitae(cv1);
                person2.setCurriculumVitae(cv2);
                personRepository.saveAll(Arrays.asList(person1,person2));


            }

            });
        executorService.shutdown();
    }



}