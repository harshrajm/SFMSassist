package com.sfms.sfmsassist.repository;

import com.sfms.sfmsassist.entities.UserLoginStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 10-07-2017.
 */
public interface UserLoginStatusRepository extends JpaRepository<UserLoginStatus,Long> {
}
