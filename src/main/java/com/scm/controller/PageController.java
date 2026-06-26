package com.scm.controller;

import com.scm.entities.User;
import com.scm.forms.UserFrom;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

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
        return new String("contact");
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
    public String processRegister(@Valid @ModelAttribute UserFrom userFrom, BindingResult rBindingResult, HttpSession session) {
        System.out.println("processing register");
        //fetch from data
        //UserForm

        //validate from data
        if (rBindingResult.hasErrors()) {
            return "register";
        }
        //save to database
        //userForm --> User
      /*  User user = User.builder()
                .name(userFrom.getName())
                .email(userFrom.getEmail())
                .password(userFrom.getPassword())
                .phoneNumber(userFrom.getPhoneNumber())
                .about(userFrom.getAbout())
                .profilePic("Passing Pic URL")
                .build();*/
        User user = new User();
        user.setName(userFrom.getName());
        user.setEmail(userFrom.getEmail());
        user.setPassword(userFrom.getPassword());
        user.setPhoneNumber(userFrom.getPhoneNumber());
        user.setAbout(userFrom.getAbout());
        user.setProfilePic("Passing Pic URL");

        userService.saveUser(user);

        System.out.println("user Saved :");
        //message = "Registration successful"

        //add the message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        //redirect to login page
        return "redirect:/register";
    }
}
