package com.sfms.sfmsassist.controller;

import java.util.Map.Entry;

import static org.assertj.core.api.Assertions.entry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.sfms.sfmsassist.constants.Constants;
import com.sfms.sfmsassist.entities.IssueDetail;
import com.sfms.sfmsassist.service.HomeService;
import com.sfms.sfmsassist.service.IssueDetailService;

/**
 * Created by Administrator on 21-07-2017.
 */

@Controller
public class HomeController {

    @Autowired
    IssueDetailService issueDetailService;


    @Autowired
    HomeService homeService;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/home")
    public String loadHomePage(Model model, RedirectAttributes redirectAttributes){
    	
    	LinkedHashMap<String, Integer> trendingIssuesHashMap = new LinkedHashMap<>();

       List<IssueDetail> issueDetails = homeService.getTicketsOfUser(Constants.ISSUE_STATUS_OPEN,  1040218);

        List<IssueDetail> issueDetailsOpenAndProd = homeService.getOpenProductionIssues();


        issueDetails = addStringNames(issueDetails);

        List<IssueDetail> trendingIssuesSubList=homeService.getLastFiftyIssue();
       /* List<IssueDetail> trendingIssuesSubList=trendingIssues.subList(trendingIssues.size()-8, trendingIssues.size());
        System.out.println(trendingIssuesSubList.size()+"jhasvcghvasdghscvhsag ");*/
        for(IssueDetail abc:trendingIssuesSubList){
     
System.out.println(abc.getIssueSubCatStr());        	
        	
        	
        }
        



Map<Integer, Integer> seussCount = new HashMap<Integer,Integer>();
for(IssueDetail t: trendingIssuesSubList) {
	if (seussCount.containsKey(t.getIssueSubCategory()))
	{
		seussCount.put(t.getIssueSubCategory(),seussCount.get(t.getIssueSubCategory())+1);
	}
	   else
	
	   {
		   seussCount.put(t.getIssueSubCategory(), 1);
	   }

}

for (Map.Entry<Integer, Integer> entry : seussCount.entrySet()) {
    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
}


List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(seussCount.entrySet());

Collections.sort( list, new Comparator<Map.Entry<Integer, Integer>>(){

	@Override
	public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
		return o2.getValue().compareTo(o1.getValue());
	}
		
		
});

/* changes done for trending isues   mahideep*/


for (Entry<Integer, Integer> entry :list) {
	//System.out.println(entry);
	trendingIssuesHashMap.put(entry.getKey().toString(), entry.getValue());
	//trendingIssuesHashMap.put(issueDetailService.getIssueSubCategory(entry.getKey()), entry.getValue()) ;
	
} 

System.out.println("-----------------------------");
for (Map.Entry<String, Integer> entry : trendingIssuesHashMap.entrySet()) {
    System.out.println("Key1 = " + entry.getKey() + ", Value1 = " + entry.getValue());
}


String json = new Gson().toJson(issueDetails);


        model.addAttribute("myOpenIssues",json);
        model.addAttribute("openProductionIssues",issueDetailsOpenAndProd);
        return "home";
    }

    
    
    
    
    /*  private ArrayList<HashMap<IssueDetail, Integer>> insert(IssueDetail items) {
    	  
    	  HashMap<IssueDetail, Integer> counters = new HashMap<IssueDetail, Integer>();
    	   if (counters.containsValue(items))
    		     counters.put(items, counters.get(items)+1);
    		   else
    		     counters.put(items, 1);

    		   items.add(items);
    		 }
*/








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
