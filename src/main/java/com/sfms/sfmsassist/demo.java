package com.sfms.sfmsassist;

import com.sfms.sfmsassist.entities.*;
import com.sfms.sfmsassist.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by Administrator on 10-07-2017.
 */

@Controller
public class demo {

    @Autowired
    IssueCategoryRepository issueCategoryRepository;
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    IssueSubCategoryRepository issueSubCategoryRepository;

    @Autowired
    IssueDetailRepository issueDetailRepository;

    @Autowired
    AssigningDetailsRepository assigningDetailsRepository;

    /*@RequestMapping("/home")
    public String testMethod() {
        return "hello !";
    }*/


    @RequestMapping("/insert")
    public String insertIssueCategory() {

        // java.util.Date today = new java.util.Date();

        System.out.println("start");
        IssueCategory issueCategory = new IssueCategory();
        issueCategory.setIssueCategoryDesc("Archiving");
        issueCategory.setCreatedBy(1040218);
        //issueCategory.setCreatedOn(today);
        //issueCategory.setLastUpdate(today);


        issueCategoryRepository.save(issueCategory);
        System.out.println("end");
        return "inserted";
    }

    @RequestMapping("/update")
    public String update() {

        IssueCategory issueCategory = issueCategoryRepository.getOne(Long.valueOf(7));
        issueCategory.setIssueCategoryDesc("Archiving issues");
        issueCategory.setUpdatedBy(1040218);
        System.out.println(issueCategory.getCreatedOn() + " ------ " + issueCategory.getLastUpdate());
        issueCategoryRepository.save(issueCategory);
        return "updated";
    }

    @RequestMapping("/insertUser")
    public String inserUser(){
        UserDetail userDetail = new UserDetail();
        userDetail.setEmployeeId(1040051);
        userDetail.setUserType(1);
        userDetail.setUserLoginStat(2);
        userDetail.setUserEnabled(1);
        userDetail.setFirstName("mahideep");
        userDetail.setLastName("tumati");
        userDetail.setTcsMailid("mahideep.t@tcs.com" );
        userDetail.setAltMailid("mahideep@gmail.com");
        userDetail.setDob(new Date());
        userDetail.setDoj(new Date());
        userDetail.setPassword("123456");
        userDetail.setMobile(Long.parseLong("9959466466"));
        userDetail.setAltMobile(Long.parseLong("9959466466"));
        userDetail.setAddress("Hyderabad");
        userDetail.setGender(1);
        userDetail.setPictureLink("http://www.shximai.com/data/out/156/68582052-profile-wallpapers.jpg");
        userDetail.setUserCreatedBy(1040051);

        userDetailsRepository.save(userDetail);
        return "user inserted!";
    }


    @RequestMapping("/updateUser")
    public String updateUser(){

        UserDetail userDetail = userDetailsRepository.getOne(Long.valueOf(1040218));

        userDetail.setAltMobile(Long.parseLong("123456789"));
        userDetail.setUserUpdatedBy(1040218);

        userDetailsRepository.save(userDetail);
        return "user updated!";
    }


    @RequestMapping("/insertSubCateg")
    public String insertSubCateg(){





        IssueSubCategory issueSubCategory11 = new IssueSubCategory();
        issueSubCategory11.setIssueCategoryId(5);
        issueSubCategory11.setIssueSubCategoryDesc("Sender error 1");
        issueSubCategory11.setCreatedBy(1040218);

        issueSubCategoryRepository.save(issueSubCategory11);

        IssueSubCategory issueSubCategory111 = new IssueSubCategory();
        issueSubCategory111.setIssueCategoryId(5);
        issueSubCategory111.setIssueSubCategoryDesc("Sender error 3");
        issueSubCategory111.setCreatedBy(1040218);

        issueSubCategoryRepository.save(issueSubCategory111);

        return "inserted";
    }

    @RequestMapping("/updateSubCateg")
    public String updateSubCateg(){

        IssueSubCategory issueSubCategory = issueSubCategoryRepository.findOne(Long.valueOf(2));

        issueSubCategory.setIssueSubCategoryDesc("FBAPI105");

        issueSubCategory.setCreatedBy(1040218);

        issueSubCategoryRepository.save(issueSubCategory);

        return "updateSubCateg";
    }




  @RequestMapping("/testAssign")
    public String testAssign(){

        AssigningDetail assigningDetail = new AssigningDetail();

        assigningDetail.setIssueAssignedBy(1040051);
        assigningDetail.setIssueAssignedTo(1040218);
        assigningDetail.setIssueId(1);
        assigningDetail.setCreatedBy(1040218);
      assigningDetailsRepository.save(assigningDetail);
        return "Assing done";
    }

    @RequestMapping("/insertIssue")
    public String insertIssue(){

        IssueDetail issueDetail = new IssueDetail();
        issueDetail.setBankId(7);
        issueDetail.setIssueCategory(4);
        issueDetail.setIssueCurOwner(1040218);
        issueDetail.setIssueDesc("FBAPI");
        issueDetail.setIssueLoggedBy(1040051);
        issueDetail.setIssueStatus(1);
        issueDetail.setIssueSubCategory(2);
        issueDetail.setIssueTitle("FBAPI");
        issueDetail.setIssueType(2);
        issueDetail.setSfmsVer(3);
        issueDetail.setTicketId("SD201755555");


        issueDetailRepository.save(issueDetail);


        return "issue inserted";
    }

    /*@RequestMapping("/home")
    public void home(){

    }*/

    @RequestMapping("/login")
    public void login(){

    }

    @RequestMapping("/hello")
    public void hello(){

    }




}
