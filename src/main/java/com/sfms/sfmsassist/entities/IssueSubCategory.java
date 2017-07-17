package com.sfms.sfmsassist.entities;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.Integer;
import java.util.Date;



/**
 * The persistent class for the ISSUE_SUB_CATEGORY database table.
 * 
 */
@Entity
@Table(name="ISSUE_SUB_CATEGORY")
@NamedQuery(name="IssueSubCategory.findAll", query="SELECT i FROM IssueSubCategory i")
public class IssueSubCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator="seq_issue_sub_category")
	@SequenceGenerator(name="seq_issue_sub_category",sequenceName="SEQ_ISSUE_SUB_CATEGORY", allocationSize=1)
	@Id
	@Column(name="ISSUE_SUB_CATEGORY_ID")
	private long issueSubCategoryId;

	@Column(name="CREATED_BY")
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="ISSUE_CATEGORY_ID")
	private Integer issueCategoryId;

	@Column(name="ISSUE_SUB_CATEGORY_DESC")
	private String issueSubCategoryDesc;

	@Version
	@Column(name="ISSUE_SUB_CATEGORY_VER")
	private Integer issueSubCategoryVer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	@Column(name="UPDATED_BY")
	private Integer updatedBy;

	public IssueSubCategory() {
	}

	public long getIssueSubCategoryId() {
		return this.issueSubCategoryId;
	}

	public void setIssueSubCategoryId(long issueSubCategoryId) {
		this.issueSubCategoryId = issueSubCategoryId;
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

	public Integer getIssueCategoryId() {
		return this.issueCategoryId;
	}

	public void setIssueCategoryId(Integer issueCategoryId) {
		this.issueCategoryId = issueCategoryId;
	}

	public String getIssueSubCategoryDesc() {
		return this.issueSubCategoryDesc;
	}

	public void setIssueSubCategoryDesc(String issueSubCategoryDesc) {
		this.issueSubCategoryDesc = issueSubCategoryDesc;
	}

	public Integer getIssueSubCategoryVer() {
		return this.issueSubCategoryVer;
	}

	public void setIssueSubCategoryVer(Integer issueSubCategoryVer) {
		this.issueSubCategoryVer = issueSubCategoryVer;
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
		this.createdOn = this.lastUpdate = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.lastUpdate = new Date();
	}
}