package com.sfms.sfmsassist.service;
import com.sfms.sfmsassist.entities.BankDetail;
import com.sfms.sfmsassist.entities.IssueCategory;
import com.sfms.sfmsassist.entities.IssueDetail;
import com.sfms.sfmsassist.entities.IssueSubCategory;
import com.sfms.sfmsassist.repository.BankDetailRepository;
import com.sfms.sfmsassist.repository.IssueCategoryRepository;
import com.sfms.sfmsassist.repository.IssueDetailRepository;
import com.sfms.sfmsassist.repository.IssueSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 17-07-2017.
 */
@Service
public class IssueDetailsServiceImpl implements IssueDetailService {
    @Autowired
    BankDetailRepository bankDetailRepository;
    @Autowired
    IssueCategoryRepository issueCategoryRepository;
    @Autowired
    IssueSubCategoryRepository issueSubCategoryRepository;
    @Autowired
    IssueDetailRepository issueDetailRepository;


    @Override
    public List<BankDetail> getAllBankDetails() {

        return bankDetailRepository.findAll();

    }

    @Override
    public List<IssueCategory> getAllIssueCategory() {
        return issueCategoryRepository.findAll();
    }

    @Override
    public List<IssueSubCategory> getAllSubIssueCategory() {
        return issueSubCategoryRepository.findAll();
    }

    @Override
    public List<IssueSubCategory> getIssueSubCatByCatId(int CatId) {
        return issueSubCategoryRepository.findByIssueCategoryId(CatId);
    }

    @Override
    public void insertIssueDetails(String ticketId, int bankId, int issueType, int sfmsVer, int selectedCat, int selectedSubCat, String title, String desc) {
        IssueDetail issueDetail = new IssueDetail();
        //issueDetail.setIssueId(0L);
        issueDetail.setBankId(bankId);
        issueDetail.setIssueCategory(selectedCat);
        issueDetail.setIssueCurOwner(1040218);
        issueDetail.setIssueDesc(desc);
        //issueDetail.setIssueLastUpdate(new Date());
        issueDetail.setIssueLoggedBy(1040218);
        //issueDetail.setIssueLoggedOn(new Date());
       //issueDetail.setIssueResolvedBy(0);
       // issueDetail.setIssueResolvedOn(new Date());
        //issueDetail.setIssueSolution("");
        issueDetail.setIssueStatus(1);
        issueDetail.setIssueSubCategory(selectedSubCat);
        issueDetail.setIssueTitle(title);
        issueDetail.setIssueType(issueType);
        //issueDetail.setIssueUpdatedBy(0);
        //issueDetail.setIssueVersion(0);
        issueDetail.setSfmsVer(sfmsVer);
        issueDetail.setTicketId(ticketId.toUpperCase());

        issueDetailRepository.save(issueDetail);


    }

    @Override
    public void insertAndCloseIssueDetails(String ticketId, int bankId, int issueType, int sfmsVer, int selectedCat,
                                           int selectedSubCat, String title, String desc, String solution) {
        IssueDetail issueDetail = new IssueDetail();
        //issueDetail.setIssueId(0L);
        issueDetail.setBankId(bankId);
        issueDetail.setIssueCategory(selectedCat);
        issueDetail.setIssueCurOwner(1040218);
        issueDetail.setIssueDesc(desc);
        //issueDetail.setIssueLastUpdate(new Date());
        issueDetail.setIssueLoggedBy(1040218);
        //issueDetail.setIssueLoggedOn(new Date());
        issueDetail.setIssueResolvedBy(1040218);
        issueDetail.setIssueResolvedOn(new Date());
        issueDetail.setIssueSolution(solution);
        issueDetail.setIssueStatus(2);
        issueDetail.setIssueSubCategory(selectedSubCat);
        issueDetail.setIssueTitle(title);
        issueDetail.setIssueType(issueType);
        //issueDetail.setIssueUpdatedBy(0);
        //issueDetail.setIssueVersion(0);
        issueDetail.setSfmsVer(sfmsVer);
        issueDetail.setTicketId(ticketId.toUpperCase());

        issueDetailRepository.save(issueDetail);
    }

    @Override
    public boolean checkIfTicketAlreadyPresent(String ticketId) {

        List<IssueDetail> issueDetails = issueDetailRepository.findByTicketId(ticketId);

        if(issueDetails.isEmpty()){
           return false;
        }return true;



    }

    @Override
    public String getBankCode(Integer bankId) {
        return bankDetailRepository.getOne(Long.valueOf(bankId)).getBankCode();
    }

    @Override
    public String getIssueCategory(Integer catId) {
        return issueCategoryRepository.getOne(Long.valueOf(catId)).getIssueCategoryDesc();
    }

    @Override
    public String getIssueSubCategory(Integer subCatId) {
        return issueSubCategoryRepository.getOne(Long.valueOf(subCatId)).getIssueSubCategoryDesc();
    }
}
