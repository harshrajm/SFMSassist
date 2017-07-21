package com.sfms.sfmsassist.dto;

/**
 * Created by Administrator on 17-07-2017.
 */
public class IssueDetailsDto {

    String ticketId;
    int bankId;
    String sfmsVer;
    String title;
    String desc;
    int issueType;
    int categary;
    int subCategory;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getSfmsVer() {
        return sfmsVer;
    }

    public void setSfmsVer(String sfmsVer) {
        this.sfmsVer = sfmsVer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIssueType() {
        return issueType;
    }

    public void setIssueType(int issueType) {
        this.issueType = issueType;
    }

    public int getCategary() {
        return categary;
    }

    public void setCategary(int categary) {
        this.categary = categary;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }


    @Override
    public String toString() {
        return "IssueDetailsDto{" +
                "ticketId='" + ticketId + '\'' +
                ", bankId=" + bankId +
                ", sfmsVer='" + sfmsVer + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", issueType=" + issueType +
                ", categary=" + categary +
                ", subCategory=" + subCategory +
                '}';
    }
}
