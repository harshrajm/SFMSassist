package com.sfms.sfmsassist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@RequestMapping("/home")
	public String loadHomePage(Model model, RedirectAttributes redirectAttributes) {


		List<IssueDetail> issueDetails = homeService.getTicketsOfUser(Constants.ISSUE_STATUS_OPEN, 1040218);

		List<IssueDetail> issueDetailsOpenAndProd = homeService.getOpenProductionIssues();

		HashMap<String, Integer> trendingIssues = new HashMap<>();
		trendingIssues = homeService.getTrendingIssuesDetails();

		for (Map.Entry<String, Integer> entry : trendingIssues.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		issueDetails = addStringNames(issueDetails);

		String json = new Gson().toJson(issueDetails);

		model.addAttribute("myOpenIssues", json);
		model.addAttribute("openProductionIssues", issueDetailsOpenAndProd);
		model.addAttribute("trendingIssues",trendingIssues);
		return "home";
	}

	/*
	 * private ArrayList<HashMap<IssueDetail, Integer>> insert(IssueDetail
	 * items) {
	 * 
	 * HashMap<IssueDetail, Integer> counters = new HashMap<IssueDetail,
	 * Integer>(); if (counters.containsValue(items)) counters.put(items,
	 * counters.get(items)+1); else counters.put(items, 1);
	 * 
	 * items.add(items); }
	 */

	List<IssueDetail> addStringNames(List<IssueDetail> issueDetails) {

		for (IssueDetail issueDetail : issueDetails) {

			if (issueDetail.getIssueType() == Constants.ISSUE_TYPE_PRODUCTION) {
				issueDetail.setIssueTypeStr("Production");
			} else {
				issueDetail.setIssueTypeStr("UAT");
			}

			issueDetail.setBankCodeStr(issueDetailService.getBankCode(issueDetail.getBankId()));
			issueDetail.setIssueCatStr(issueDetailService.getIssueCategory(issueDetail.getIssueCategory()));
			issueDetail.setIssueSubCatStr(issueDetailService.getIssueSubCategory(issueDetail.getIssueSubCategory()));

		}

		return issueDetails;
	}

}
