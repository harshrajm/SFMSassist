package com.sfms.sfmsassist.entities;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.Integer;
import java.util.Date;



/**
 * The persistent class for the USER_LOGIN_STATUS database table.
 * 
 */
@Entity
@Table(name="USER_LOGIN_STATUS")
@NamedQuery(name="UserLoginStatus.findAll", query="SELECT u FROM UserLoginStatus u")
public class UserLoginStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_LOGIN_STATUS_ID")
	private long userLoginStatusId;

	@Column(name="CREATED_BY")
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON")
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	@Column(name="UPDATED_BY")
	private Integer updatedBy;

	@Column(name="USER_LOGIN_STATUS_DESC")
	private String userLoginStatusDesc;

	@Version
	@Column(name="USER_LOGIN_STATUS_VERSION")
	private Integer userLoginStatusVersion;

	public UserLoginStatus() {
	}

	public long getUserLoginStatusId() {
		return this.userLoginStatusId;
	}

	public void setUserLoginStatusId(long userLoginStatusId) {
		this.userLoginStatusId = userLoginStatusId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUserLoginStatusDesc() {
		return this.userLoginStatusDesc;
	}

	public void setUserLoginStatusDesc(String userLoginStatusDesc) {
		this.userLoginStatusDesc = userLoginStatusDesc;
	}

	public Integer getUserLoginStatusVersion() {
		return this.userLoginStatusVersion;
	}

	public void setUserLoginStatusVersion(Integer userLoginStatusVersion) {
		this.userLoginStatusVersion = userLoginStatusVersion;
	}

	@PrePersist
	void createdAt() {
		this.createdOn = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.lastUpdate = new Date();
	}
}