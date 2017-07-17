package com.sfms.sfmsassist.entities;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.Integer;
import java.util.Date;



/**
 * The persistent class for the USER_SESSION_HIST database table.
 * 
 */
@Entity
@Table(name="USER_SESSION_HIST")
@NamedQuery(name="UserSessionHist.findAll", query="SELECT u FROM UserSessionHist u")
public class UserSessionHist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOGIN_SESSION_ID")
	private long loginSessionId;

	@Column(name="CREATED_BY")
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON")
	private Date createdOn;

	/*@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INSERTED_ON")
	private Date insertedOn;*/

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	@Column(name="LOGIN_OR_LOGOUT")
	private Integer loginOrLogout;

	@Column(name="UPDATED_BY")
	private Integer updatedBy;

	@Column(name="USER_ID")
	private Integer userId;

	@Version
	@Column(name="USER_SESSION_HIST_VERSION")
	private Integer userSessionHistVersion;

	public UserSessionHist() {
	}

	public long getLoginSessionId() {
		return this.loginSessionId;
	}

	public void setLoginSessionId(long loginSessionId) {
		this.loginSessionId = loginSessionId;
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

	/*public Date getInsertedOn() {
		return this.insertedOn;
	}

	public void setInsertedOn(Date insertedOn) {
		this.insertedOn = insertedOn;
	}*/

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getLoginOrLogout() {
		return this.loginOrLogout;
	}

	public void setLoginOrLogout(Integer loginOrLogout) {
		this.loginOrLogout = loginOrLogout;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserSessionHistVersion() {
		return this.userSessionHistVersion;
	}

	public void setUserSessionHistVersion(Integer userSessionHistVersion) {
		this.userSessionHistVersion = userSessionHistVersion;
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