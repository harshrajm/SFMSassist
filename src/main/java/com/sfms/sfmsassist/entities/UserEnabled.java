package com.sfms.sfmsassist.entities;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.Integer;
import java.util.Date;



/**
 * The persistent class for the USER_ENABLED database table.
 * 
 */
@Entity
@Table(name="USER_ENABLED")
@NamedQuery(name="UserEnabled.findAll", query="SELECT u FROM UserEnabled u")
public class UserEnabled implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ENABLED_ID")
	private long userEnabledId;

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

	@Column(name="USER_ENABLED_DESC")
	private String userEnabledDesc;

	@Version
	@Column(name="USER_ENABLED_VERSION")
	private Integer userEnabledVersion;

	public UserEnabled() {
	}

	public long getUserEnabledId() {
		return this.userEnabledId;
	}

	public void setUserEnabledId(long userEnabledId) {
		this.userEnabledId = userEnabledId;
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

	public String getUserEnabledDesc() {
		return this.userEnabledDesc;
	}

	public void setUserEnabledDesc(String userEnabledDesc) {
		this.userEnabledDesc = userEnabledDesc;
	}

	public Integer getUserEnabledVersion() {
		return this.userEnabledVersion;
	}

	public void setUserEnabledVersion(Integer userEnabledVersion) {
		this.userEnabledVersion = userEnabledVersion;
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