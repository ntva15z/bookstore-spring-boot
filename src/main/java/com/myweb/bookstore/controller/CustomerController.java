package com.myweb.bookstore.controller;

import com.myweb.bookstore.entity.Customer;
import com.myweb.bookstore.entity.Role;
import com.myweb.bookstore.repository.RoleReponsitory;
import com.myweb.bookstore.service.CustomerService;
import com.myweb.bookstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/registration")
    public String registration(ModelMap model) {
        model.addAttribute("customer", new Customer());

        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@RequestParam("gender")String gender,@Validated @ModelAttribute Customer entity, Errors errors) {
        entity.setGender(gender);
        entity.setRegisterdate(Calendar.getInstance().getTime());
        Set<Role> roles = new HashSet<>();
        Optional<Role> role = roleService.findById(Long.parseLong(String.valueOf(2)));
        if (role.isPresent()){
            roles.add(role.get());
            entity.setRoleList(roles);
        }
        if(errors.hasErrors()){
            return "registration";
        }
        customerService.save(entity);
        return "redirect:login";
    }
}
