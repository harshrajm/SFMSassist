package com.sfms.sfmsassist.controller;

import com.google.gson.Gson;
import com.sfms.sfmsassist.constants.Constants;
import com.sfms.sfmsassist.entities.IssueDetail;
import com.sfms.sfmsassist.entities.UserDetail;
import com.sfms.sfmsassist.service.HomeService;
import com.sfms.sfmsassist.service.IssueDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 21-07-2017.
 */

@Controller
public class HomeController {



    @Autowired
    IssueDetailService issueDetailService;


    @Autowired
    HomeService homeService;

    @RequestMapping("/")
    public String loadHomePage(Model model, RedirectAttributes redirectAttributes){

        UserDetail principal = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String json = null;

        List<IssueDetail> issueDetails = null;
        try {
            issueDetails = homeService.getTicketsOfUser(Constants.ISSUE_STATUS_OPEN,(int) principal.getEmployeeId());
            issueDetails = addStringNames(issueDetails,"home",null);
             json = new Gson().toJson(issueDetails);
        } catch (NullPointerException e) {
            model.addAttribute("noIssueToDisplay",true);
        }

        List<IssueDetail> issueDetailsOpenAndProd = homeService.getOpenProductionIssues();




        HashMap<String, Integer> trendingIssues = new HashMap<>();
        trendingIssues = homeService.getTrendingIssuesDetails();



        System.out.println(json);

        //for current logged in user
       // principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        /*if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            System.out.println("-1-1-1-1-1-1-1-1-1--1"+username);
        } else {
            String username = principal.toString();
        }*/

        System.out.println("-1-1-1-1-1-1-1-1-1-1"+(principal).getLastName());

        model.addAttribute("userName",(principal).getFirstName()+" "+(principal).getLastName());
        model.addAttribute("empId",(principal).getEmployeeId());
        model.addAttribute("tcsMailId",(principal).getTcsMailid());

        model.addAttribute("myOpenIssues",json);
        model.addAttribute("openProductionIssues",issueDetailsOpenAndProd);

        model.addAttribute("trendingIssues",trendingIssues);

        return "home";
    }

      List<IssueDetail> addStringNames(List<IssueDetail> issueDetails, String homeOrAllIssues,UserDetail principal) {

        for(IssueDetail issueDetail :issueDetails){

            if(issueDetail.getIssueType() == Constants.ISSUE_TYPE_PRODUCTION){
                issueDetail.setIssueTypeStr("Production");
            }else{
                issueDetail.setIssueTypeStr("UAT");
            }

            issueDetail.setBankCodeStr(issueDetailService.getBankCode(issueDetail.getBankId()));
            issueDetail.setIssueCatStr(issueDetailService.getIssueCategory(issueDetail.getIssueCategory()));
            issueDetail.setIssueSubCatStr(issueDetailService.getIssueSubCategory(issueDetail.getIssueSubCategory()));

            if(homeOrAllIssues.equals("home")) {
                if (issueDetail.getIssueCurOwner().equals(issueDetail.getIssueLoggedBy())) {
                    issueDetail.setAssigned(false);
                } else {
                    issueDetail.setAssigned(true);
                }
            }

            if(homeOrAllIssues.equals("allIssues")){

                System.out.println(issueDetail.getIssueCurOwner()+"----"+principal.getEmployeeId()+"-----"+(issueDetail.getIssueCurOwner() == principal.getEmployeeId()));

                if(issueDetail.getIssueCurOwner() == principal.getEmployeeId()){
                    issueDetail.setYourTicket(true);
                }
                if(issueDetail.getIssueStatus().equals(Constants.ISSUE_STATUS_CLOSED)){
                    issueDetail.setClosed(true);
                }

            }


        }

        return issueDetails;
    }


    @RequestMapping("/allIssues")
    public String allIssues(Model model){

        UserDetail principal = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "issueId"));
        Pageable pageable = new PageRequest(0, 10, sort);
        List<IssueDetail> issueDetails = homeService.getAllIssues(pageable);

        issueDetails = addStringNames(issueDetails,"allIssues",principal);

        String json = new Gson().toJson(issueDetails);

        model.addAttribute("allIssues",json);

        model.addAttribute("totalNoOfIssues",homeService.getAllIssuesCount());

        return "allIssues";
    }


    @RequestMapping(value="/loginform",method= RequestMethod.GET)
    public String loginMethod(){

        System.out.println("hihihihihihihih");
        return "login";
    }


}
