package com.sfms.sfmsassist.repository;

import com.sfms.sfmsassist.entities.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 10-07-2017.
 */
public interface IssueStatusRepository extends JpaRepository<IssueStatus,Long> {
}
