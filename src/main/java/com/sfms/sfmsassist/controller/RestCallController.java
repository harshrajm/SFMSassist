package com.sfms.sfmsassist.controller;

import com.google.gson.Gson;
import com.sfms.sfmsassist.entities.IssueCategory;
import com.sfms.sfmsassist.entities.IssueDetail;
import com.sfms.sfmsassist.entities.IssueSubCategory;
import com.sfms.sfmsassist.entities.UserDetail;
import com.sfms.sfmsassist.service.HomeService;
import com.sfms.sfmsassist.service.IssueDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 19-07-2017.
 */
@RestController
public class RestCallController {



    @Autowired
    HomeController homeController;
    @Autowired
    HomeService homeService;

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


    @RequestMapping("/allIssuesRestCall/{page}")
    public List<IssueDetail> getAllIssuesRestCall(@PathVariable int page){

        UserDetail principal = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "issueId"));
        Pageable pageable = new PageRequest(page, 10, sort);
        List<IssueDetail> issueDetails = homeService.getAllIssues(pageable);

        issueDetails = homeController.addStringNames(issueDetails,"allIssues",principal);

        String json = new Gson().toJson(issueDetails);
        System.out.println("rest controller");
        System.out.println(json);
        System.out.println("--rest controller--");
        return issueDetails;
    }

    //uatOrProd,pendOrClosed,yrTkt
    @RequestMapping("/getIssuesAfterFilter/filter")
    public List<IssueDetail> getAllIssuesRestCall(@RequestParam (required = false)int uatOrProd,
                                                  @RequestParam (required = false)int pendOrClosed,
                                                  @RequestParam (required = false)boolean yrTkt){

        UserDetail principal = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<IssueDetail> issueDetails = null;
        System.out.println("-----------------in /getIssuesAfterFilter-----------------");
        System.out.println("uatOrProd "+uatOrProd);
        System.out.println("pendOrClosed "+pendOrClosed);
        System.out.println("yrTkt "+yrTkt );

        if(uatOrProd != 0 && pendOrClosed != 0 ){

            issueDetails =  issueDetailService.findByIssueTypeAndIssueStatus(uatOrProd,pendOrClosed);

        }
        else if(uatOrProd == 0 && pendOrClosed != 0 ){
            issueDetails =  issueDetailService.findByIssueStatus(pendOrClosed);
        }
        else if(uatOrProd != 0 && pendOrClosed == 0 ){
            issueDetails =  issueDetailService.findByIssueType(uatOrProd);
        }else{
            issueDetails = issueDetailService.getAllIssues();

        }

        System.out.println("issueDetails.size() "+issueDetails.size());
        issueDetails = homeController.addStringNames(issueDetails,"allIssues",principal);

        if(yrTkt == true){

            for (Iterator<IssueDetail> iterator = issueDetails.iterator(); iterator.hasNext(); ) {
                IssueDetail issueDetail = iterator.next();
                if(issueDetail.isYourTicket() == false){
                    iterator.remove();
                }
            }
        }

        return issueDetails;
    }


    @RequestMapping("/searchIssuesRestCall/{searchQuery}")
    public List<IssueDetail> searchIssuesRestCall(@PathVariable String searchQuery){

        UserDetail principal = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(searchQuery);

        List<IssueDetail> issueDetails = null;


        issueDetails = issueDetailService.searchAllIssuesByKeyword(searchQuery);

        /*for (Iterator<IssueDetail> iterator = issueDetails.iterator(); iterator.hasNext(); ) {
            IssueDetail issueDetail = iterator.next();
            if(issueDetail.isClosed() == true){
                iterator.remove();
            }
        }*/

        String json = new Gson().toJson(issueDetails);
        System.out.println(json);

        issueDetails = homeController.addStringNames(issueDetails,"allIssues",principal);

        return issueDetails;
    }


    @RequestMapping("/getIssuesBetwnDate")
    List<IssueDetail> getIssuesBetwnDate(@RequestParam String fDate,
                                         @RequestParam String tDate) throws Exception{
        System.out.println("rest controller   :");


        System.out.println(fDate);
        System.out.println(tDate);

        DateFormat df2 = new SimpleDateFormat("dd-M-yyyy");

        Date fDateD = df2.parse(fDate);
        Date tDateD = df2.parse(tDate);

        List<IssueDetail> data= issueDetailService.getIssueBetween(fDateD,tDateD);


        String json = new Gson().toJson(data);

        System.out.println(json);

        return data;
    }

    // data= issueDetailRepository.findByIssueLoggedOnBetween(fDate,tDate);

}
