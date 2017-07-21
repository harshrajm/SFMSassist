package com.sfms.sfmsassist.service;

import com.sfms.sfmsassist.entities.BankDetail;
import com.sfms.sfmsassist.entities.IssueCategory;
import com.sfms.sfmsassist.entities.IssueSubCategory;

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
}
