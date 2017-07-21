package com.sfms.sfmsassist.controller;

import com.sfms.sfmsassist.entities.IssueCategory;
import com.sfms.sfmsassist.entities.IssueSubCategory;
import com.sfms.sfmsassist.service.IssueDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 19-07-2017.
 */
@RestController
public class RestCallController {

    @Autowired
    IssueDetailService issueDetailService;

    @RequestMapping("/getAllIssueCategory")
    public List<IssueCategory> getAllCategories(){
       return issueDetailService.getAllIssueCategory();
    }

    @RequestMapping("/getSubCat/{catId}")
    public List<IssueSubCategory> getSubCatByCatId(@PathVariable int catId){

        System.out.println("in /getSubCat/{catId}   "+catId);

        return issueDetailService.getIssueSubCatByCatId(catId);


    }


}
