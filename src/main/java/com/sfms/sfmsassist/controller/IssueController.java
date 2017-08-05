package com.sfms.sfmsassist.controller;

import com.sfms.sfmsassist.constants.Constants;
import com.sfms.sfmsassist.entities.AssigningDetail;
import com.sfms.sfmsassist.entities.BankDetail;
import com.sfms.sfmsassist.entities.IssueDetail;
import com.sfms.sfmsassist.entities.UserDetail;
import com.sfms.sfmsassist.service.HomeService;
import com.sfms.sfmsassist.service.IssueDetailService;
import com.sfms.sfmsassist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 17-07-2017.
 */
@Controller
public class IssueController {

    @Autowired
    IssueDetailService issueDetailService;
    @Autowired
    UserService userService;
    @Autowired
    HomeService homeService;
    @Autowired
    HomeController homeController;

    @RequestMapping("/addIssueDetails")
    public String newIssue(Model model,HttpSession httpSession){



        List<BankDetail>  bankDetails = issueDetailService.getAllBankDetails();

        Collections.sort(bankDetails, new Comparator<BankDetail>() {
            @Override
            public int compare(BankDetail bD1, BankDetail bD2) {

                return bD1.getBankCode().compareToIgnoreCase(bD2.getBankCode());
            }
        });



        model.addAttribute("bankList",bankDetails);


        return "issueDetails";


    }

    @RequestMapping(value="/submitIssue", method= RequestMethod.POST)
    public String insertIssueDetails(Model model, RedirectAttributes redirectAttributes, @RequestParam("ticketId") String ticketId,
                                     @RequestParam("bankId") int bankId, @RequestParam("issueType") int issueType, @RequestParam("sfmsVer") int sfmsVer,
                                     @RequestParam("selectedCat") int selectedCat, @RequestParam("selectedSubCat") int selectedSubCat, @RequestParam("title") String title,
                                     @RequestParam("desc") String desc, @RequestParam(required=false, value="sol") String solution){


        if(issueDetailService.checkIfTicketAlreadyPresent(ticketId)){
            System.out.println("Ticket ID Already Present!! " +ticketId);


            redirectAttributes.addFlashAttribute("ticketIdAlreadyPresent",true);

            return "redirect:/home";

        }else{
            System.out.println("ticket ID not present!!");


        System.out.println("Solution : "+solution);
        if (solution != null && !solution.isEmpty()) {
            System.out.println("solution is not null/empty");
            issueDetailService.insertAndCloseIssueDetails(ticketId,bankId,issueType,sfmsVer,
                    selectedCat,selectedSubCat, title,desc,solution);
            System.out.println("Issue Inserted & closed!!!!");

            redirectAttributes.addFlashAttribute("issueInsertedAndClosed",true);

        }else{
            System.out.println("solution is null/empty");
            issueDetailService.insertIssueDetails(ticketId,bankId,issueType,sfmsVer,
                    selectedCat,selectedSubCat, title,desc);
            System.out.println("Issue Inserted!!!!");
            redirectAttributes.addFlashAttribute("issueInserted",true);

        }


        return "redirect:/home";
        }
    }



    @RequestMapping("/showIssueDetails/{issueId}")
    public String viewIssueDetails (@PathVariable long issueId, Model model){

        List<UserDetail> userDetailsList = issueDetailService.getAllUsers();

        System.out.println("issue Id is :"+issueId);

        IssueDetail issueDetail = issueDetailService.getIssueById(issueId);

        if(issueDetail.getIssueType() == Constants.ISSUE_TYPE_PRODUCTION){
            issueDetail.setIssueTypeStr("Production");
        }else{
            issueDetail.setIssueTypeStr("UAT");
        }

        issueDetail.setBankNameStr(issueDetailService.getBankName(issueDetail.getBankId())+" ("+issueDetailService.getBankCode(issueDetail.getBankId())+")");
        //issueDetail.setBankCodeStr(issueDetailService.getBankCode(issueDetail.getBankId()));
        issueDetail.setIssueCatStr(issueDetailService.getIssueCategory(issueDetail.getIssueCategory()));
        issueDetail.setIssueSubCatStr(issueDetailService.getIssueSubCategory(issueDetail.getIssueSubCategory()));

        /*System.out.println(issueDetail);*/
        /*System.out.println("##################");
        System.out.println("##################");
        System.out.println(issueDetailService.getAssigningDetailsByIssueId((int)issueDetail.getIssueId()));
        System.out.println("##################");*/

        List<AssigningDetail> assigningDetailList = issueDetailService.getAssigningDetailsByIssueId((int)issueDetail.getIssueId());

        for(AssigningDetail assigningDetail: assigningDetailList){
            assigningDetail.setFromName(userService.getUserByEmpId(assigningDetail.getIssueAssignedBy()).getFirstName()
                    + " "+userService.getUserByEmpId(assigningDetail.getIssueAssignedBy()).getLastName());
            assigningDetail.setToName(userService.getUserByEmpId(assigningDetail.getIssueAssignedTo()).getFirstName()
                    + " "+userService.getUserByEmpId(assigningDetail.getIssueAssignedTo()).getLastName());
        }

        model.addAttribute("assigningDetails",assigningDetailList);

        model.addAttribute("issueResolved",issueDetail.getIssueStatus().equals(Constants.ISSUE_STATUS_CLOSED));

        if(issueDetail.getIssueStatus().equals(Constants.ISSUE_STATUS_CLOSED)){
            model.addAttribute("resolvedBy",userService.getUserByEmpId(issueDetail.getIssueResolvedBy()).getFirstName()
                    +" "+userService.getUserByEmpId(issueDetail.getIssueResolvedBy()).getLastName());
            model.addAttribute("solution",issueDetail.getIssueSolution());
        }
        model.addAttribute("issueDetail",issueDetail);
        System.out.println("enable assigning :"+((issueDetail.getIssueCurOwner() == 1040218)
                && issueDetail.getIssueStatus()== Constants.ISSUE_STATUS_OPEN));
        model.addAttribute("enableAssigning",((issueDetail.getIssueCurOwner() == 1040218)
                && issueDetail.getIssueStatus()== Constants.ISSUE_STATUS_OPEN));

        model.addAttribute("userDetails",userDetailsList);
        model.addAttribute("IssueCurOwnerName",userService.getUserByEmpId(issueDetail.getIssueCurOwner()).getFirstName()
                +" "+userService.getUserByEmpId(issueDetail.getIssueCurOwner()).getLastName());
        model.addAttribute("IssueOwnerName",userService.getUserByEmpId(issueDetail.getIssueLoggedBy()).getFirstName()
                +" "+userService.getUserByEmpId(issueDetail.getIssueLoggedBy()).getLastName());

        model.addAttribute("userEmpId",1040218);

        return "viewIssueDetails";
    }

    @RequestMapping(value ="/assignIssue" ,method = RequestMethod.POST)
    public String assignIssue(RedirectAttributes redirectAttributes,@RequestParam("assignTo") long assignToId,@RequestParam long issueId ){
        System.out.println("in assignIssue");
        System.out.println(assignToId);
        System.out.println(issueId);

        long fromId = 1040218;

        issueDetailService.assignIssue(issueId,fromId,assignToId);

        redirectAttributes.addFlashAttribute("issueAssigned",true);

        return "redirect:/home";
    }

    @RequestMapping(value = "/closeIssue/{issueId}",method = RequestMethod.POST)
    public String closeIssue(@PathVariable long issueId,RedirectAttributes redirectAttributes,@RequestParam String solution){

        System.out.println("solution :"+solution);
        System.out.println("issueId :"+issueId);

        long employeeId =1040218;

        issueDetailService.closeIssue(issueId,solution,employeeId);

        redirectAttributes.addFlashAttribute("issueClosed",true);

        return "redirect:/home";
    }

    @RequestMapping(value = "/findIssueByTktId",method = RequestMethod.POST)
    public String findIssueByTktId(Model model,@RequestParam String ticketId){
        System.out.println("ticketId : "+ ticketId);

        List<IssueDetail> issueDetailList = issueDetailService.getIdOfIssue(ticketId.toUpperCase());

        return "redirect:/showIssueDetails/"+issueDetailList.get(0).getIssueId();
    }

    @RequestMapping("/searchIssues")
    public String searchIssues(){

        return "searchIssues";
    }
}
