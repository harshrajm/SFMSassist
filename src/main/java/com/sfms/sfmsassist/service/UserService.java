package com.sfms.sfmsassist.service;

import com.sfms.sfmsassist.dto.UserDto;
import com.sfms.sfmsassist.entities.UserDetail;

/**
 * Created by Administrator on 17-07-2017.
 */


public interface UserService {

    public void insertUser(UserDto userDto,String hashedPassword);

    public UserDetail getUserByEmpId(long x);

}
