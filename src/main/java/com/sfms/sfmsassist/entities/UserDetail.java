package com.sfms.sfmsassist.entities;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.Integer;
import java.util.Date;



/**
 * The persistent class for the USER_DETAILS database table.
 * 
 */
@Entity
@Table(name="USER_DETAILS")
@NamedQuery(name="UserDetail.findAll", query="SELECT u FROM UserDetail u")
public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLOYEE_ID")
	private long employeeId;

	private String address;

	@Column(name="ALT_MAILID")
	private String altMailid;

	@Column(name="ALT_MOBILE")
	private Long altMobile;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DM_LSTUPDDT")
	private Date dmLstupddt;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@Temporal(TemporalType.DATE)
	private Date doj;

	@Column(name="FIRST_NAME")
	private String firstName;

	private Integer gender;

	@Column(name="LAST_NAME")
	private String lastName;

	private Long mobile;

	private String password;

	@Column(name="PICTURE_LINK")
	private String pictureLink;

	@Column(name="TCS_MAILID")
	private String tcsMailid;

	@Column(name="USER_CREATED_BY")
	private Integer userCreatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="USER_CREATED_ON")
	private Date userCreatedOn;

	@Version
	@Column(name="USER_DETAILS_VERSION")
	private Integer userDetailsVersion;

	@Column(name="USER_ENABLED")
	private Integer userEnabled;

	@Column(name="USER_LOGIN_STAT")
	private Integer userLoginStat;

	@Column(name="USER_TYPE")
	private Integer userType;

	@Column(name="USER_UPDATED_BY")
	private Integer userUpdatedBy;

	public UserDetail() {
	}

	public long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAltMailid() {
		return this.altMailid;
	}

	public void setAltMailid(String altMailid) {
		this.altMailid = altMailid;
	}

	public Long getAltMobile() {
		return this.altMobile;
	}

	public void setAltMobile(Long altMobile) {
		this.altMobile = altMobile;
	}

	public Date getDmLstupddt() {
		return this.dmLstupddt;
	}

	public void setDmLstupddt(Date dmLstupddt) {
		this.dmLstupddt = dmLstupddt;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return this.doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getMobile() {
		return this.mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPictureLink() {
		return this.pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public String getTcsMailid() {
		return this.tcsMailid;
	}

	public void setTcsMailid(String tcsMailid) {
		this.tcsMailid = tcsMailid;
	}

	public Integer getUserCreatedBy() {
		return this.userCreatedBy;
	}

	public void setUserCreatedBy(Integer userCreatedBy) {
		this.userCreatedBy = userCreatedBy;
	}

	public Date getUserCreatedOn() {
		return this.userCreatedOn;
	}

	public void setUserCreatedOn(Date userCreatedOn) {
		this.userCreatedOn = userCreatedOn;
	}

	public Integer getUserDetailsVersion() {
		return this.userDetailsVersion;
	}

	public void setUserDetailsVersion(Integer userDetailsVersion) {
		this.userDetailsVersion = userDetailsVersion;
	}

	public Integer getUserEnabled() {
		return this.userEnabled;
	}

	public void setUserEnabled(Integer userEnabled) {
		this.userEnabled = userEnabled;
	}

	public Integer getUserLoginStat() {
		return this.userLoginStat;
	}

	public void setUserLoginStat(Integer userLoginStat) {
		this.userLoginStat = userLoginStat;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserUpdatedBy() {
		return this.userUpdatedBy;
	}

	public void setUserUpdatedBy(Integer userUpdatedBy) {
		this.userUpdatedBy = userUpdatedBy;
	}

	@PrePersist
	void createdAt() {
		this.userCreatedOn = this.dmLstupddt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.dmLstupddt = new Date();
	}
}