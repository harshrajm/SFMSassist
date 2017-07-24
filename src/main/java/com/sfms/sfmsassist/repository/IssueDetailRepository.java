package com.sfms.sfmsassist.repository;

import com.sfms.sfmsassist.entities.IssueDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Date;

/**
 * Created by Administrator on 10-07-2017.
 */
public interface IssueDetailRepository extends JpaRepository<IssueDetail,Long> {

    List<IssueDetail> findByTicketId(String ticketId);

    List<IssueDetail> findByIssueStatusAndIssueCurOwnerOrderByIssueIdDesc(int issueSatus,int issueCurOwner);
    //ByOrderByIssueIdDesc

    List<IssueDetail> findByIssueTypeAndIssueStatus(int issueType,int issueStatus);

/*	List<IssueDetail> findFirst8ByOrderByIssueLoggedOn();
*/	
    
    List<IssueDetail> findByIssueLoggedOnGreaterThanEqual(Date date);
}
