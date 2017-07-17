package com.sfms.sfmsassist.controller;

import com.sfms.sfmsassist.dto.UserDto;
import com.sfms.sfmsassist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        /*userDto.setEmpId();
        userDto.setfName("fName");
        userDto.setlName("lName");
        userDto.setTcsMail("tcsMail");
        userDto.setAltMail("altMail");
        userDto.setDob("dob");
        userDto.setDoj("doj");
        userDto.setPassword("password");
        userDto.setMobile("mobile");
        userDto.setAltMobile(0L);
        userDto.setAddress("");
        userDto.setGender(0);
        userDto.setpLink("");*/
        //userDto.setpLink("");



        model.addAttribute("userDto",userDto);

        return "register";

    }


    @RequestMapping(value="/registerUser", method= RequestMethod.POST)
    public String processUser(@ModelAttribute("userDto") UserDto userDto,Model model) throws Exception {


        System.out.println(userDto.toString());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       String hashedPassword = passwordEncoder.encode(userDto.getPassword());
       // System.out.println("hashed pwd :"+hashedPassword);

        //https://image.freepik.com/free-icon/profile-user-silhouette_318-40557.jpg
        userService.insertUser(userDto,hashedPassword);
        System.out.println("inserted!!!!!!!!");

        model.addAttribute("userInserted",true);

        return "login";
    }


}

