package com.sfms.sfmsassist.repository;

import com.sfms.sfmsassist.entities.AssigningDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by Administrator on 10-07-2017.
 */
public interface AssigningDetailsRepository extends JpaRepository<AssigningDetail,Long> {

}
