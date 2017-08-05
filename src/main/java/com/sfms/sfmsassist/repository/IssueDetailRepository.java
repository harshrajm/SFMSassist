package com.sfms.sfmsassist.repository;

import com.sfms.sfmsassist.entities.IssueDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 10-07-2017.
 */
public interface IssueDetailRepository extends JpaRepository<IssueDetail,Long> {

    List<IssueDetail> findByTicketId(String ticketId);

    List<IssueDetail> findByIssueStatusAndIssueCurOwnerOrderByIssueIdDesc(int issueSatus,int issueCurOwner);


    List<IssueDetail> findByIssueTypeAndIssueStatus(int issueType,int issueStatus);


    List<IssueDetail> findByIssueLoggedOnGreaterThanEqual(Date date);

    List<IssueDetail> findAllByOrderByIssueIdDesc(org.springframework.data.domain.Pageable pageable);

    List<IssueDetail> findByIssueStatus(int uatOrProd);

    List<IssueDetail> findByIssueType(int openOrClosed);

    List<IssueDetail> findByIssueDescContainingIgnoreCaseOrIssueSolutionContainingIgnoreCaseOrIssueTitleContainingIgnoreCase(String str1,String str2,String str3);



}
