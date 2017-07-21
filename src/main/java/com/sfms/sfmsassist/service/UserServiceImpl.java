package com.sfms.sfmsassist.service;

import com.sfms.sfmsassist.dto.UserDto;
import com.sfms.sfmsassist.entities.UserDetail;
import com.sfms.sfmsassist.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 17-07-2017.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public void insertUser(UserDto userDto, String hashedPassword)  {



        UserDetail userDetail = new UserDetail();

        userDetail.setEmployeeId(userDto.getEmpId());
        userDetail.setAddress(userDto.getAddress());
        userDetail.setAltMailid(userDto.getAltMail());
        userDetail.setAltMobile(userDto.getAltMobile());
        //userDetail.setDmLstupddt(new Date());
        userDetail.setDob(stringToDate(userDto.getDob()));
        userDetail.setDoj(stringToDate(userDto.getDoj()));
        userDetail.setFirstName(userDto.getfName());
        userDetail.setGender(userDto.getGender());
        userDetail.setLastName(userDto.getlName());
        userDetail.setMobile(userDto.getMobile());
        userDetail.setPassword(hashedPassword);
        userDetail.setPictureLink("https://image.freepik.com/free-icon/profile-user-silhouette_318-40557.jpg");
        userDetail.setTcsMailid(userDto.getTcsMail());
        userDetail.setUserCreatedBy((int)(long)userDto.getEmpId());
        //userDetail.setUserCreatedOn(new Date());
        //userDetail.setUserDetailsVersion(0);
        userDetail.setUserEnabled(1);
        userDetail.setUserLoginStat(1);
        userDetail.setUserType(1);
        //userDetail.setUserUpdatedBy(0);

        userDetailsRepository.save(userDetail);




    }

    @Override
    public UserDetail getUserByEmpId(long x) {
        return userDetailsRepository.getOne(x);
    }

    public static Date stringToDate(String dateInString){

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }
}
