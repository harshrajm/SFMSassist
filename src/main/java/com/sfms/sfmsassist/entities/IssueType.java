package com.sfms.sfmsassist.entities;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.Integer;
import java.util.Date;



/**
 * The persistent class for the ISSUE_TYPE database table.
 * 
 */
@Entity
@Table(name="ISSUE_TYPE")
@NamedQuery(name="IssueType.findAll", query="SELECT i FROM IssueType i")
public class IssueType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ISSUE_TYPE_ID")
	private long issueTypeId;

	@Column(name="CREATED_BY")
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="ISSUE_TYPE_DESC")
	private String issueTypeDesc;

	@Version
	@Column(name="ISSUE_TYPE_VERSION")
	private Integer issueTypeVersion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	@Column(name="UPDATED_BY")
	private Integer updatedBy;

	public IssueType() {
	}

	public long getIssueTypeId() {
		return this.issueTypeId;
	}

	public void setIssueTypeId(long issueTypeId) {
		this.issueTypeId = issueTypeId;
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

	public String getIssueTypeDesc() {
		return this.issueTypeDesc;
	}

	public void setIssueTypeDesc(String issueTypeDesc) {
		this.issueTypeDesc = issueTypeDesc;
	}

	public Integer getIssueTypeVersion() {
		return this.issueTypeVersion;
	}

	public void setIssueTypeVersion(Integer issueTypeVersion) {
		this.issueTypeVersion = issueTypeVersion;
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

	@PrePersist
	void createdAt() {
		this.createdOn = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.lastUpdate = new Date();
	}
}