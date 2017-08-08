package com.sfms.sfmsassist.controller;

import com.sfms.sfmsassist.dto.UserDto;
import com.sfms.sfmsassist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Base64;

/**
 * Created by Administrator on 14-07-2017.
 */


@Controller
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;




    @RequestMapping(value="/registerUser", method= RequestMethod.GET)
    public String registerUser(Model model) throws Exception
    {

        UserDto userDto = new UserDto();

        model.addAttribute("userDto",userDto);

        return "register";

    }



    @RequestMapping(value="/registerUser", method= RequestMethod.POST)
    public String processUser(@ModelAttribute("userDto") UserDto userDto,Model model) throws Exception {


        System.out.println(userDto.toString());

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       //String hashedPassword = passwordEncoder.encode(userDto.getPassword());

        String hashedPassword = Base64.getEncoder().encodeToString(userDto.getPassword().getBytes());
        userService.insertUser(userDto,hashedPassword);
        System.out.println("inserted!!!!!!!!");

        model.addAttribute("userInserted",true);

        return "login";
    }





}

