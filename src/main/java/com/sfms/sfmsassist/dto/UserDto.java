package com.sfms.sfmsassist.dto;

/**
 * Created by Administrator on 14-07-2017.
 */
public class UserDto {

    private Long empId;

    private String fName;

    private String lName;

    private String tcsMail;

    private String altMail;

    private String dob;

    private String doj;

    private String password;

    private Long mobile;

    private Long altMobile;

    private String address;

    private int gender;

    //private String pLink;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getTcsMail() {
        return tcsMail;
    }

    public void setTcsMail(String tcsMail) {
        this.tcsMail = tcsMail;
    }

    public String getAltMail() {
        return altMail;
    }

    public void setAltMail(String altMail) {
        this.altMail = altMail;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getAltMobile() {
        return altMobile;
    }

    public void setAltMobile(Long altMobile) {
        this.altMobile = altMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "empId=" + empId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", tcsMail='" + tcsMail + '\'' +
                ", altMail='" + altMail + '\'' +
                ", dob='" + dob + '\'' +
                ", doj='" + doj + '\'' +
                ", password='" + password + '\'' +
                ", mobile=" + mobile +
                ", altMobile=" + altMobile +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                '}';
    }
}
