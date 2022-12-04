package myboot.app1.web;

import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.dao.PersonRepository;
import myboot.app1.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller()
public class VueAppController {

    @Autowired
    CurriculumVitaeRepository curriculumVitaeRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/app")
    private ModelAndView hello() {
        return new ModelAndView("app");
    }

    @RequestMapping(value = "/login")
    private ModelAndView login() {
        return new ModelAndView("login");
    }



    @RequestMapping(value = "/profile")
    //@PreAuthorize("hasAuthority('USER')")
    private ModelAndView profile() {
        var res = new ModelAndView("profile");
        return res;
    }

    @RequestMapping(value = "/edit")
    //@PreAuthorize("hasAuthority('USER')")
    private ModelAndView editCV() {
        var res = new ModelAndView("edit-cv");
        return res;
    }

    @RequestMapping(value = "/activities")
    private ModelAndView activities() {
        var res = new ModelAndView("activities");
        return res;
    }

    @RequestMapping(value = "/create-cv")
    private ModelAndView createCV() {
        var res = new ModelAndView("create-cv");
        return res;
    }

    @RequestMapping(value = "/cvList")
    private ModelAndView cvList() {
        var res = new ModelAndView("curriculumVitaeList");
        return res;
    }

    @RequestMapping(value = "/result/find")
    private ModelAndView searchCV(@RequestParam("name")String name) {
        var cvs = curriculumVitaeRepository.getCurriculumVitaeByName(name);
        var firstName = personRepository.getPersonByFirstName("%"+name+"%");
        var lastName = personRepository.getPersonByLastName("%"+name+"%");
        ModelAndView modelAndView = null;
        if(!cvs.isEmpty()){
            modelAndView = new ModelAndView("resultSearch", "cvResult", cvs);

        }
        if(!firstName.isEmpty()){
            modelAndView = new ModelAndView("resultSearch", "personResult", firstName);
        }
        if(!lastName.isEmpty()){
            modelAndView = new ModelAndView("resultSearch", "personResult", lastName);
        }

        return modelAndView;

    }
    @RequestMapping(value = "/createPerson")
    private ModelAndView createPerson() {
        var res = new ModelAndView("createPerson");
        return res;
    }




}
