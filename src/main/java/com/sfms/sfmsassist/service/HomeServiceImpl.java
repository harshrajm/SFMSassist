package com.sfms.sfmsassist.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfms.sfmsassist.constants.Constants;
import com.sfms.sfmsassist.entities.IssueDetail;
import com.sfms.sfmsassist.repository.IssueDetailRepository;
import com.sfms.sfmsassist.repository.IssueSubCategoryRepository;

/**
 * Created by Administrator on 21-07-2017.
 */

@Service
public class HomeServiceImpl implements HomeService {

@Autowired
IssueDetailRepository issueDetailRepository;
	
	@Autowired
	IssueSubCategoryRepository issueSubCategoryRepository;

	@Override
	public List<IssueDetail> getTicketsOfUser(int issueSatus, int userId) {

		if (issueSatus == Constants.ISSUE_STATUS_OPEN) {

			List<IssueDetail> issueDetailsList = issueDetailRepository
					.findByIssueStatusAndIssueCurOwnerOrderByIssueIdDesc(issueSatus, userId);
			// ByOrderByIssueIdDesc
			return issueDetailsList;
		}

		return null;
	}

	@Override
	public List<IssueDetail> getOpenProductionIssues() {
		return issueDetailRepository.findByIssueTypeAndIssueStatus(Constants.ISSUE_TYPE_PRODUCTION,
				Constants.ISSUE_STATUS_OPEN);
	}

	public List<IssueDetail> getLastFiftyIssue() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -5);
		 return issueDetailRepository.findByIssueLoggedOnGreaterThanEqual(cal.getTime());

	}

	@Override
	public HashMap<String, Integer> getTrendingIssuesDetails() {
		
    	LinkedHashMap<String, Integer> trendingIssuesHashMap = new LinkedHashMap<>();

		List<IssueDetail> trendingIssuesList = getLastFiftyIssue();

		
		Map<Integer, Integer> seussCount = new HashMap<Integer, Integer>();
		for (IssueDetail t : trendingIssuesList) {
			if (seussCount.containsKey(t.getIssueSubCategory())) {
				seussCount.put(t.getIssueSubCategory(), seussCount.get(t.getIssueSubCategory()) + 1);
			} else

			{
				seussCount.put(t.getIssueSubCategory(), 1);
			}

		}

		List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(seussCount.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}

		});

		/* changes done for trending isues mahideep */

		for (Entry<Integer, Integer> entry : list) {
			
			trendingIssuesHashMap.put(getIssueSubCategory(entry.getKey()), entry.getValue());

		}

		

		return trendingIssuesHashMap;
	}

	 public String getIssueSubCategory(Integer subCatId) {
	        return issueSubCategoryRepository.getOne(Long.valueOf(subCatId)).getIssueSubCategoryDesc();
	    }
	
}
