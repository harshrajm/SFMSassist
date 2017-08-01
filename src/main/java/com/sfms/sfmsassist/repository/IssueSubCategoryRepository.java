package com.sfms.sfmsassist.repository;

import com.sfms.sfmsassist.entities.IssueSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 10-07-2017.
 */
public interface IssueSubCategoryRepository extends JpaRepository<IssueSubCategory,Long> {

    List<IssueSubCategory> findByIssueCategoryId(int issueCategoryId);


}
