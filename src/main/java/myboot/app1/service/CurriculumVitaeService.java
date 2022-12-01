package myboot.app1.service;

import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.model.Activity;
import myboot.app1.model.CurriculumVitae;
import myboot.app1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CurriculumVitaeService {
    @Autowired
    CurriculumVitaeRepository curriculumVitaeRepository;
    @Autowired
    PersonService personService;

    public CurriculumVitae saveCV(CurriculumVitae cv) {
        return curriculumVitaeRepository.save(cv);
    }

    public CurriculumVitae getCV(int id) {
        return curriculumVitaeRepository.findById(id).get();
    }

    public String deleteCV(int id) {
        curriculumVitaeRepository.deleteById(id);
        return "CV deleted " + id;
    }
    public CurriculumVitae updateCV(CurriculumVitae cv){
        if (curriculumVitaeRepository.findById(cv.getId()).isEmpty()){
            curriculumVitaeRepository.save(cv); }
        CurriculumVitae cv2 = curriculumVitaeRepository.findById(cv.getId()).get();
        cv2.setActivities(cv.getActivities());
        curriculumVitaeRepository.save(cv2);
        return cv;

    }
    public CurriculumVitae getCurrentUserCv(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }
        System.out.println(auth.getName());
        System.out.println(email +" EEEEEEEEEE");
        Person person = personService.getPersonByEmail(auth.getName());
        System.out.println(person.getFirstName());
        System.out.println(person.getCurriculumVitae().getCvName());
        CurriculumVitae curriculumVitae = curriculumVitaeRepository.getCurriculumVitaeByPerson(person);
        return curriculumVitae;
    }
}
