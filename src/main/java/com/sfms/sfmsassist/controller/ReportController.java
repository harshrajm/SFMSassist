package com.sfms.sfmsassist.controller;

import com.sfms.sfmsassist.service.IssueDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
public class ReportController {


	@Autowired
	IssueDetailService issueDetailService;

	@RequestMapping("/generateReport")
	public String reports(Model model)
	{

		System.out.println("inside reports ");

		return "reports";
	}


	@RequestMapping(value="/generateReports", method= RequestMethod.POST)
	public ModelAndView generateReports(@RequestParam("fDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fDate,@RequestParam("tDate") @DateTimeFormat(pattern="yyyy-MM-dd"  ) Date tDate,@RequestParam("formatType") String reportFormat)
	{
		System.out.println(fDate);
		System.out.println(tDate);
		System.out.println(reportFormat);

		Map<String, Object> model = new HashMap<>();

		//String reportFormat="html";

		List<Map<String, String>> reportDetails;
		reportDetails = issueDetailService.getIssueDetails(fDate,tDate);

		model.put("tDate", tDate);
		model.put("fDate", fDate);

		model.put("report", reportDetails);
		model.put("reportformat", reportFormat);

		for (Map<String,String> map : reportDetails) {
			System.out.println(map);
		}

		if ("excel".equals(reportFormat)) {
			model.put("format", "xlsx");
		} else if ("html".equals(reportFormat)) {
			model.put("format", "html");





		}
		return new ModelAndView("weeklyReport", model);
	}
}


