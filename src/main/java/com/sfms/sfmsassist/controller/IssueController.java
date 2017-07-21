package com.sfms.sfmsassist.controller;

import com.sfms.sfmsassist.entities.BankDetail;
import com.sfms.sfmsassist.service.HomeService;
import com.sfms.sfmsassist.service.IssueDetailService;
import com.sfms.sfmsassist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

            //model.addAttribute("ticketIdAlreadyPresent",true);

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



}
