package com.sfms.sfmsassist.controller;

import com.google.gson.Gson;
import com.sfms.sfmsassist.constants.Constants;
import com.sfms.sfmsassist.entities.IssueDetail;
import com.sfms.sfmsassist.service.HomeService;
import com.sfms.sfmsassist.service.IssueDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping("/home")
    public String loadHomePage(Model model, RedirectAttributes redirectAttributes){

       List<IssueDetail> issueDetails = homeService.getTicketsOfUser(Constants.ISSUE_STATUS_OPEN,  1040218);

        List<IssueDetail> issueDetailsOpenAndProd = homeService.getOpenProductionIssues();


        issueDetails = addStringNames(issueDetails);

        String json = new Gson().toJson(issueDetails);

        System.out.println(json);

        model.addAttribute("myOpenIssues",json);
        model.addAttribute("openProductionIssues",issueDetailsOpenAndProd);
        return "home";
    }

      List<IssueDetail> addStringNames(List<IssueDetail> issueDetails) {

        for(IssueDetail issueDetail :issueDetails){

            if(issueDetail.getIssueType() == Constants.ISSUE_TYPE_PRODUCTION){
                issueDetail.setIssueTypeStr("Production");
            }else{
                issueDetail.setIssueTypeStr("UAT");
            }

            issueDetail.setBankCodeStr(issueDetailService.getBankCode(issueDetail.getBankId()));
            issueDetail.setIssueCatStr(issueDetailService.getIssueCategory(issueDetail.getIssueCategory()));
            issueDetail.setIssueSubCatStr(issueDetailService.getIssueSubCategory(issueDetail.getIssueSubCategory()));

        }

        return issueDetails;
    }


}