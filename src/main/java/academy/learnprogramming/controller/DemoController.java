package academy.learnprogramming.controller;

import academy.learnprogramming.service.DemoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class DemoController {

    DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    // http://localhost:8080/todo-list/hello
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // http://localhost:8080/todo-list/welcome
    // http://localhost:8080/todo-list/welcome?user=John
    // http://localhost:8080/todo-list/welcome?user=John&age=45
    @GetMapping("welcome")
    public String welcome(@RequestParam String user, @RequestParam int age,Model model){
        model.addAttribute("message", demoService.getHelloMessage(user));
        model.addAttribute("age", age);
        return "welcome";
    }

    @ModelAttribute("welcomeMessage")
    public String welcomeMessage(){
        return demoService.getWelcomeMessage();
    }


}
