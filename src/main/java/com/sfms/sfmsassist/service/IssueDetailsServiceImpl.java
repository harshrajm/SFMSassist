package com.sfms.sfmsassist.service;

import com.sfms.sfmsassist.constants.Constants;
import com.sfms.sfmsassist.entities.*;
import com.sfms.sfmsassist.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    AssigningDetailsRepository assigningDetailsRepository;

    @Autowired
    IssueStatusRepository issueStatusRepository;


    @Autowired
    UserService userService;



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

        UserDetail principal = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        IssueDetail issueDetail = new IssueDetail();
        //issueDetail.setIssueId(0L);
        issueDetail.setBankId(bankId);
        issueDetail.setIssueCategory(selectedCat);
        issueDetail.setIssueCurOwner((int) principal.getEmployeeId());
        issueDetail.setIssueDesc(desc);
        //issueDetail.setIssueLastUpdate(new Date());
        issueDetail.setIssueLoggedBy((int) principal.getEmployeeId());
        //issueDetail.setIssueLoggedOn(new Date());
       //issueDetail.setIssueResolvedBy(0);
       // issueDetail.setIssueResolvedOn(new Date());
        //issueDetail.setIssueSolution("");
        issueDetail.setIssueStatus(Constants.ISSUE_STATUS_OPEN);
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

        UserDetail principal = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        IssueDetail issueDetail = new IssueDetail();
        //issueDetail.setIssueId(0L);
        issueDetail.setBankId(bankId);
        issueDetail.setIssueCategory(selectedCat);
        issueDetail.setIssueCurOwner((int) principal.getEmployeeId());
        issueDetail.setIssueDesc(desc);
        //issueDetail.setIssueLastUpdate(new Date());
        issueDetail.setIssueLoggedBy((int) principal.getEmployeeId());
        //issueDetail.setIssueLoggedOn(new Date());
        issueDetail.setIssueResolvedBy((int) principal.getEmployeeId());
        issueDetail.setIssueResolvedOn(new Date());
        issueDetail.setIssueSolution(solution);
        issueDetail.setIssueStatus(Constants.ISSUE_STATUS_CLOSED);
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

    @Override
    public IssueDetail getIssueById(long issueId) {
        return issueDetailRepository.getOne(issueId);
    }

    @Override
    public String getBankName(Integer bankId) {
        return bankDetailRepository.getOne(Long.valueOf(bankId)).getBankName();
    }

    @Override
    public List<UserDetail> getAllUsers() {
        return userDetailsRepository.findAll();
    }

    @Override
    public void assignIssue(long issueId, long fromId, long assignToId) {

        AssigningDetail assigningDetail = new AssigningDetail();

        assigningDetail.setIssueAssignedBy((int) fromId);
        assigningDetail.setIssueAssignedTo((int) assignToId);
        assigningDetail.setIssueId((int)issueId);
        assigningDetail.setCreatedBy((int)fromId);

        assigningDetailsRepository.save(assigningDetail);

        IssueDetail issueDetail = issueDetailRepository.getOne(issueId);
        issueDetail.setIssueCurOwner((int) assignToId);
        issueDetail.setIssueUpdatedBy((int) fromId);

        issueDetailRepository.save(issueDetail);

    }

    @Override
    public void closeIssue(long issueId, String solution, long employeeId) {

        IssueDetail issueDetail =  issueDetailRepository.getOne(issueId);

        issueDetail.setIssueResolvedBy((int) employeeId);
        issueDetail.setIssueResolvedOn(new Date());
        issueDetail.setIssueStatus(Constants.ISSUE_STATUS_CLOSED);
        issueDetail.setIssueUpdatedBy((int) employeeId);
        issueDetail.setIssueSolution(solution);

        issueDetailRepository.save(issueDetail);


    }

    @Override
    public List<IssueDetail> getIdOfIssue(String ticketId) {
        return issueDetailRepository.findByTicketId(ticketId);
    }

    @Override
    public List<AssigningDetail> getAssigningDetailsByIssueId(int issueId) {
        System.out.println("in servImp"+issueId);
        return assigningDetailsRepository.findByIssueId(issueId);
    }

    @Override
    public List<IssueDetail> findByIssueTypeAndIssueStatus(int uatOrProd, int pendOrClosed) {
        return issueDetailRepository.findByIssueTypeAndIssueStatus(uatOrProd,pendOrClosed);
    }

    @Override
    public List<IssueDetail> findByIssueStatus(int pendOrClosed) {
        return issueDetailRepository.findByIssueStatus(pendOrClosed);
    }

    @Override
    public List<IssueDetail> findByIssueType(int uatOrProd) {
        return issueDetailRepository.findByIssueType( uatOrProd);
    }

    @Override
    public List<IssueDetail> getAllIssues() {
        return issueDetailRepository.findAll();
    }

    @Override
    public List<IssueDetail> searchAllIssuesByKeyword(String searchQuery) {
        return issueDetailRepository.findByIssueDescContainingIgnoreCaseOrIssueSolutionContainingIgnoreCaseOrIssueTitleContainingIgnoreCase(searchQuery,searchQuery,searchQuery);
    }

   /* @Override
    public List<Map<String, String>> getIssueDetails(Date fDate, Date tDate) {
        return issueDetailRepository.findByIssueLoggedOnBetween(fDate,tDate);
    }*/


    public List<Map<String, String>> getIssueDetails(Date fDate, Date tDate) {




        List<IssueDetail> data = new ArrayList<>();
        data = issueDetailRepository.findByIssueLoggedOnBetween(fDate, tDate);
        List<Map<String, String>> listTrans = new ArrayList<>();

        for (IssueDetail report : data) {

            if (report.getIssueStatus() == 2) {

                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");

                Map<String, String> transMap = new HashMap<>();

                transMap.put("ticketId", report.getTicketId());
                transMap.put("date", report.getIssueLoggedOn().toString());
                // System.out.println(Month.of(report.getIssueLoggedOn().getMonth()).name());
                transMap.put("dateMonth", new SimpleDateFormat("MMMM").format(report.getIssueLoggedOn()));
                System.out.println(new SimpleDateFormat("MMMM").format(report.getIssueLoggedOn()) + "ddaaaaattteee");

                transMap.put("issueLoggedTime", report.getIssueLoggedOn().toString());
                transMap.put("callMode", "By EMS");

                transMap.put("rootCause", getIssueSubCategory(report.getIssueSubCategory()) + " issue in "
                        + getIssueCategory(report.getIssueCategory()));

                transMap.put("module", getIssueCategory(report.getIssueCategory()));

                transMap.put("title", report.getIssueTitle());
                transMap.put("status", getStatus(new Long(report.getIssueStatus())));
                transMap.put("ownerName", userService.getUserByEmpId(report.getIssueResolvedBy()).getFirstName()
                        +" "+userService.getUserByEmpId(report.getIssueResolvedBy()).getLastName());

                transMap.put("bank", getBankNames(report.getBankId()));

                transMap.put("solution", report.getIssueSolution());

                transMap.put("resolvedOn", formatter.format(report.getIssueResolvedOn()));

                transMap.put("resolvedOnDate", localDateFormat.format(report.getIssueResolvedOn()));


                long second = 1000l;
                long minute = 60l * second;
                long hour = 60l * minute;

                long difference = report.getIssueResolvedOn().getTime() - report.getIssueLoggedOn().getTime();
                System.out.println("--------------------------------------------");
                System.out.print(String.format("%02d", difference / hour));
                System.out.print(":");
                System.out.print(String.format("%02d", (difference % hour) / minute));
                System.out.print(":");
                System.out.print(String.format("%02d", (difference % minute) / second));
                System.out.println("+++++++++++++++++++++++++++++++++++++++++");

                transMap.put("totalResolutionTime",String.format("%02d", difference / hour)+":"+String.format("%02d", (difference % hour) / minute)+":"+String.format("%02d", (difference % minute) / second));


                listTrans.add(transMap);

            }
        }

        return listTrans;



    }

    @Override
    public List<IssueDetail> getIssueBetween(Date fDate, Date tDate) {
        return issueDetailRepository.findByIssueLoggedOnBetween(fDate,tDate);
    }

    public String getBankNames(Integer bankId )
    {

        return bankDetailRepository.getOne(Long.valueOf(bankId)).getBankName();
    }

    private String getStatus(Long issueStatus) {

        return issueStatusRepository.getOne(Long.valueOf(issueStatus)).getIssueStatusDesc();
    }

}
