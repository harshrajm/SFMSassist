package com.sfms.sfmsassist.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfms.sfmsassist.constants.Constants;
import com.sfms.sfmsassist.entities.IssueDetail;
import com.sfms.sfmsassist.repository.IssueDetailRepository;

/**
 * Created by Administrator on 21-07-2017.
 */

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    IssueDetailRepository issueDetailRepository;

    @Override
    public List<IssueDetail> getTicketsOfUser(int issueSatus, int userId) {

        if(issueSatus == Constants.ISSUE_STATUS_OPEN ){

           List<IssueDetail> issueDetailsList = issueDetailRepository.findByIssueStatusAndIssueCurOwnerOrderByIssueIdDesc(issueSatus,userId);
            //ByOrderByIssueIdDesc
           return issueDetailsList;
        }


        return null;
    }

    @Override
    public List<IssueDetail> getOpenProductionIssues() {
        return issueDetailRepository.findByIssueTypeAndIssueStatus(Constants.ISSUE_TYPE_PRODUCTION,Constants.ISSUE_STATUS_OPEN);
    }

	public List<IssueDetail> getLastFiftyIssue() {

		
		Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				System.out.println("Date = "+ cal.getTime());
		System.out.println("inside service impl");
/*		return issueDetailRepository.findFirst8ByOrderByIssueLoggedOn();
*/		return issueDetailRepository.findByIssueLoggedOnGreaterThanEqual(cal.getTime());

	}
    
    
}
