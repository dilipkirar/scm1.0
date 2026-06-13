package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page handler");

        //Sending data to view
        model.addAttribute("name","Substring Technology");
        model.addAttribute("YoutubeChannel","Junior Daivik kirar");
        model.addAttribute("githubRepo","https://github.com/learning-zone/java-basics");

        return "home";
    }
    //about route

    @RequestMapping("/about")
    public String aboutPage(Model model){
        System.out.println("About page Loading");
        model.addAttribute("isLogin",true);
        return "about";
    }
    //services

    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services page Loading");
        return "services";
    }
}
