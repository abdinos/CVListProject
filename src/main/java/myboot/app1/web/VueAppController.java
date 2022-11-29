package myboot.app1.web;

import myboot.app1.dao.CurriculumVitaeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class VueAppController {

    @Autowired
    CurriculumVitaeRepository curriculumVitaeRepository;

    @RequestMapping(value = "/app")
    private ModelAndView hello() {
        return new ModelAndView("app");
    }

    @RequestMapping(value = "/login")
    private ModelAndView login() {
        var res = new ModelAndView("login");
        return res;
    }


    @RequestMapping(value = "/profile")
    private ModelAndView profile() {
        var res = new ModelAndView("profile");
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
    private ModelAndView search(@RequestParam("name")String name) {
        var result = curriculumVitaeRepository.getCurriculumVitaeByName(name);
        var res = new ModelAndView("resultCvSearch", "cvResult", result);
        return res;

    }




}
