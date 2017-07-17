package com.sfms.sfmsassist.entities;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.Integer;
import java.util.Date;



/**
 * The persistent class for the ISSUE_CATEGORY database table.
 * 
 */
@Entity
@Table(name="ISSUE_CATEGORY")
@NamedQuery(name="IssueCategory.findAll", query="SELECT i FROM IssueCategory i")
public class IssueCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator="seq_issue_category")
	@SequenceGenerator(name="seq_issue_category",sequenceName="SEQ_ISSUE_CATEGORY", allocationSize=1)
	@Id
	@Column(name="ISSUE_CATEGORY_ID")
	private long issueCategoryId;

	@Column(name="CREATED_BY")
	private Integer createdBy;

	/*@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@CreatedDate*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="ISSUE_CATEGORY_DESC")
	private String issueCategoryDesc;

	@Version
	@Column(name="ISSUE_CATEGORY_VERSION")
	private Integer issueCategoryVersion;

	/*@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@LastModifiedDate*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	@Column(name="UPDATED_BY")
	private Integer updatedBy;

	public IssueCategory() {
	}

	public long getIssueCategoryId() {
		return this.issueCategoryId;
	}

	public void setIssueCategoryId(long issueCategoryId) {
		this.issueCategoryId = issueCategoryId;
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

	public String getIssueCategoryDesc() {
		return this.issueCategoryDesc;
	}

	public void setIssueCategoryDesc(String issueCategoryDesc) {
		this.issueCategoryDesc = issueCategoryDesc;
	}

	public Integer getIssueCategoryVersion() {
		return this.issueCategoryVersion;
	}

	public void setIssueCategoryVersion(Integer issueCategoryVersion) {
		this.issueCategoryVersion = issueCategoryVersion;
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
		this.createdOn = this.lastUpdate  = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.lastUpdate = new Date();
	}

}