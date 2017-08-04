package com.sfms.sfmsassist.controller;

import com.google.gson.Gson;
import com.sfms.sfmsassist.entities.IssueCategory;
import com.sfms.sfmsassist.entities.IssueDetail;
import com.sfms.sfmsassist.entities.IssueSubCategory;
import com.sfms.sfmsassist.service.HomeService;
import com.sfms.sfmsassist.service.IssueDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "issueId"));
        Pageable pageable = new PageRequest(page, 10, sort);
        List<IssueDetail> issueDetails = homeService.getAllIssues(pageable);

        issueDetails = homeController.addStringNames(issueDetails,"allIssues");

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
        issueDetails = homeController.addStringNames(issueDetails,"allIssues");

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

}
