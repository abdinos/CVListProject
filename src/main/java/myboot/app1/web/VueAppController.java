package myboot.app1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class VueAppController {

    @RequestMapping(value = "/app")
    private ModelAndView hello() {
        return new ModelAndView("app");
    }

    @RequestMapping(value = "/login")
    private ModelAndView login() {
        var res = new ModelAndView("login");
        return res;
    }

    @RequestMapping(value = "/activities")
    private ModelAndView activities() {
        var res = new ModelAndView("activities");
        return res;
    }

    @RequestMapping(value = "/cvList")
    private ModelAndView cvList() {
        var res = new ModelAndView("curriculumVitaeList");
        return res;
    }




}
