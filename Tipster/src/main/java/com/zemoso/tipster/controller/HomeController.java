package com.zemoso.tipster.controller;

import com.zemoso.tipster.entity.User;
import com.zemoso.tipster.entity.UserCredential;
import com.zemoso.tipster.service.UserCredentialService;
import com.zemoso.tipster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tipster")
public class HomeController {
    private UserService userService;
    private UserCredentialService userCredentialService;
    @Autowired
    public HomeController(UserService userService,UserCredentialService userCredentialService){
        this.userService=userService;
        this.userCredentialService=userCredentialService;
    }
    @GetMapping("/logout")
    public String showHome(){
        return "home";
    }
    @GetMapping("/home")
    public String showHomePage(){
        return "home";
    }
    @GetMapping("/signupForm")
    public String showSignUpForm(){
        return "signupForm";
    }
    @GetMapping("/signinForm")
    public String showSignInForm(){
        return "signinForm";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        System.out.println(user);
        userService.save(user);
        return "redirect:/tipster/signinForm";
    }
    @GetMapping("/admin")
    public String viewHomePage(Model model) {
        return showAdminPage(1, model);
    }
    @GetMapping("/page/{pageNo}")
    public String showAdminPage(@PathVariable (value = "pageNo") int pageNo, Model model)
    {
        int pageSize=2;
        Page<User> page=userService.findPaginated(pageNo,pageSize);
        List<User> users=page.getContent();
        model.addAttribute("users",users);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("currentPage",pageNo);
        return "adminPage";
    }
   @PostMapping("/validateCredentials")
    public String showUserPage(@ModelAttribute("usercredentials") UserCredential userCredential, Model model){
        String email=userCredential.getEmail();
        int id=0;
        UserCredential theUserCredential=null;
        List<UserCredential> userCredentials=userCredentialService.findAll();
       for (UserCredential credential : userCredentials) {
           if(userCredential.getEmail().equals(credential.getEmail())){
               System.out.println(userCredential+"   "+theUserCredential);
               userCredential.setUser(credential.getUser());
               id=credential.getId();
               userCredential.setId(id);
               theUserCredential=credential;
               break;
           }
       }
       System.out.println(userCredential+"   "+theUserCredential);
       if(theUserCredential==null)
           return "invalidPage";
       model.addAttribute("user",userService.findById(id));
       if(userCredential.getEmail().equals(theUserCredential.getEmail())&&userCredential.getPassword().equals(theUserCredential.getPassword()))
            return "userPage";
       else
            return "invalidPage";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int theId,
                                    Model theModel) {
        User user = userService.findById(theId);
        theModel.addAttribute("user", user);
        return "userForm";
    }
    @PostMapping("/save")
    public String saveUserWithUpdate(@ModelAttribute("user") User user) {
        userService.save(user);
        return "userPage";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("userId") int theId) {
        userService.deleteById(theId);
        return "redirect:/tipster/home";
    }
}