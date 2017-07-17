package com.sfms.sfmsassist.repository;

import com.sfms.sfmsassist.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 10-07-2017.
 */
public interface UserDetailsRepository extends JpaRepository<UserDetail,Long> {
}
