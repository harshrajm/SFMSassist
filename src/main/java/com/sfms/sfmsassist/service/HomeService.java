package com.sfms.sfmsassist.service;

import com.sfms.sfmsassist.entities.IssueDetail;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 21-07-2017.
 */
public interface HomeService {



    List<IssueDetail> getTicketsOfUser(int issueSatus, int  userId );

    List<IssueDetail> getOpenProductionIssues();

    HashMap<String, Integer> getTrendingIssuesDetails();


    List<IssueDetail> getAllIssues(org.springframework.data.domain.Pageable pageable);

    int getAllIssuesCount();
}
