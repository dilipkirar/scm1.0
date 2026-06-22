package com.scm.controller;

import com.scm.forms.UserFrom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");

        //Sending data to view
        model.addAttribute("name", "Substring Technology");
        model.addAttribute("YoutubeChannel", "Junior Daivik kirar");
        model.addAttribute("githubRepo", "https://github.com/learning-zone/java-basics");

        return "home";
    }
    //about route

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        System.out.println("About page Loading");
        model.addAttribute("isLogin", true);
        return "about";
    }
    //services

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services page Loading");
        return "services";
    }

    @RequestMapping("/contact")
    public String contact() {
        System.out.println("contact page Loading");
        return "contact";
    }

    @RequestMapping("/login")
    public String login() {
        System.out.println("login page Loading");
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        System.out.println("register page Loading");
        UserFrom userFrom = new UserFrom();
        //default data bhi daal sakte hai
       // userFrom.setName("Dilip");
        model.addAttribute("userFrom", userFrom);
        return "register";
    }

    //processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserFrom userFrom) {
        System.out.println("processing register");
        //fetch from data
        //UserForm
        System.out.println(userFrom.getName());
        System.out.println(userFrom.getEmail());
        System.out.println(userFrom.getPhoneNumber());
        //validate from data
        //save to database
        //message = "Registration successful"
        //redirect to login page
        return "redirect:/register";
    }
}
