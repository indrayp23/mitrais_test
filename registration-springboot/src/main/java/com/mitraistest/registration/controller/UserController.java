package com.mitraistest.registration.controller;

import com.mitraistest.registration.domain.UserRegistrationDto;
import com.mitraistest.registration.service.UserService;
import com.mitraistest.registration.util.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class UserController {

    @Autowired
    UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto, BindingResult bindingResult) throws Exception{
        Map<String, Object> res = new HashMap<>();
        try {
            userService.saveUser(userRegistrationDto);
            return "redirect:/registration?success";
        } catch (ApplicationException e){
            bindingResult.rejectValue("email", null, e.getMessage());
            return "registration";
        } catch (Exception e){
            return "redirect:/registration?failed";
        }
    }

}
