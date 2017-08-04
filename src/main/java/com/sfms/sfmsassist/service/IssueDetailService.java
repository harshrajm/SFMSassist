package com.sfms.sfmsassist.service;

import com.sfms.sfmsassist.entities.*;

import java.util.List;

/**
 * Created by Administrator on 17-07-2017.
 */
public interface IssueDetailService {

    public List<BankDetail> getAllBankDetails();

    public List<IssueCategory> getAllIssueCategory();

    public List<IssueSubCategory> getAllSubIssueCategory();

    public List<IssueSubCategory> getIssueSubCatByCatId(int CatId);

    public void insertIssueDetails(String ticketId, int bankId,  int issueType,  int sfmsVer,
                                   int selectedCat, int selectedSubCat, String title,
                                   String desc);

    public void insertAndCloseIssueDetails(String ticketId, int bankId,  int issueType,  int sfmsVer,
                                           int selectedCat, int selectedSubCat, String title,
                                           String desc,String solution);

    public boolean checkIfTicketAlreadyPresent(String ticketId);

    String getBankCode(Integer bankId);
    String getIssueCategory(Integer catId);
    String getIssueSubCategory(Integer subCatId);

    IssueDetail getIssueById(long issueId);

    String getBankName(Integer bankId);

    List<UserDetail> getAllUsers();

    void assignIssue(long issueId, long fromId, long assignTo);

    void closeIssue(long issueId, String solution, long employeeId);

    List<IssueDetail> getIdOfIssue(String ticketId);

    List<AssigningDetail> getAssigningDetailsByIssueId(int issueId);

    List<IssueDetail> findByIssueTypeAndIssueStatus(int uatOrProd, int pendOrClosed);

    List<IssueDetail> findByIssueStatus(int pendOrClosed);

    List<IssueDetail> findByIssueType(int uatOrProd);

    List<IssueDetail> getAllIssues();
}
