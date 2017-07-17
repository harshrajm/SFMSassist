package com.sfms.sfmsassist.entities;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.Integer;
import java.util.Date;



/**
 * The persistent class for the ISSUE_STATUS database table.
 * 
 */
@Entity
@Table(name="ISSUE_STATUS")
@NamedQuery(name="IssueStatus.findAll", query="SELECT i FROM IssueStatus i")
public class IssueStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ISSUE_STATUS_ID")
	private long issueStatusId;

	@Column(name="CREATED_BY")
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="ISSUE_STATUS_DESC")
	private String issueStatusDesc;

	@Version
	@Column(name="ISSUE_STATUS_VER")
	private Integer issueStatusVer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	@Column(name="UPDATED_BY")
	private Integer updatedBy;

	public IssueStatus() {
	}

	public long getIssueStatusId() {
		return this.issueStatusId;
	}

	public void setIssueStatusId(long issueStatusId) {
		this.issueStatusId = issueStatusId;
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

	public String getIssueStatusDesc() {
		return this.issueStatusDesc;
	}

	public void setIssueStatusDesc(String issueStatusDesc) {
		this.issueStatusDesc = issueStatusDesc;
	}

	public Integer getIssueStatusVer() {
		return this.issueStatusVer;
	}

	public void setIssueStatusVer(Integer issueStatusVer) {
		this.issueStatusVer = issueStatusVer;
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