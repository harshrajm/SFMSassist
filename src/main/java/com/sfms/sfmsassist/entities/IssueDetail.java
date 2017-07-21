package com.sfms.sfmsassist.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;



/**
 * The persistent class for the ISSUE_DETAILS database table.
 * 
 */
@Entity
@Table(name="ISSUE_DETAILS")
@NamedQuery(name="IssueDetail.findAll", query="SELECT i FROM IssueDetail i")
public class IssueDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator="seq_issue_details")
	@SequenceGenerator(name="seq_issue_details",sequenceName="SEQ_ISSUE_DETAILS", allocationSize=1)
	@Id
	@Column(name="ISSUE_ID")
	private long issueId;

	@Column(name="BANK_ID")
	private Integer bankId;

	@Column(name="ISSUE_CATEGORY")
	private Integer issueCategory;

	@Column(name="ISSUE_CUR_OWNER")
	private Integer issueCurOwner;

	@Column(name="ISSUE_DESC")
	private String issueDesc;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ISSUE_LAST_UPDATE")
	private Date issueLastUpdate;

	@Column(name="ISSUE_LOGGED_BY")
	private Integer issueLoggedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ISSUE_LOGGED_ON")
	private Date issueLoggedOn;

	@Column(name="ISSUE_RESOLVED_BY")
	private Integer issueResolvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ISSUE_RESOLVED_ON")
	private Date issueResolvedOn;

	@Column(name="ISSUE_SOLUTION")
	private String issueSolution;

	@Column(name="ISSUE_STATUS")
	private Integer issueStatus;

	@Column(name="ISSUE_SUB_CATEGORY")
	private Integer issueSubCategory;

	@Column(name="ISSUE_TITLE")
	private String issueTitle;

	@Column(name="ISSUE_TYPE")
	private Integer issueType;

	@Column(name="ISSUE_UPDATED_BY")
	private Integer issueUpdatedBy;

	@Version
	@Column(name="ISSUE_VERSION")
	private Integer issueVersion;

	@Column(name="SFMS_VER")
	private Integer sfmsVer;

	@Column(name="TICKET_ID")
	private String ticketId;

	@Transient
	@JsonProperty("bankCodeStr")
	private String bankCodeStr;

	@Transient
	@JsonProperty("issueCatStr")
	private String issueCatStr;

	@Transient
	@JsonProperty("issueSubCatStr")
	private String issueSubCatStr;

	@Transient
	@JsonProperty("issueTypeStr")
	private String issueTypeStr;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getBankCodeStr() {
		return bankCodeStr;
	}

	public void setBankCodeStr(String bankCodeStr) {
		this.bankCodeStr = bankCodeStr;
	}

	public String getIssueCatStr() {
		return issueCatStr;
	}

	public void setIssueCatStr(String issueCatStr) {
		this.issueCatStr = issueCatStr;
	}

	public String getIssueSubCatStr() {
		return issueSubCatStr;
	}

	public void setIssueSubCatStr(String issueSubCatStr) {
		this.issueSubCatStr = issueSubCatStr;
	}

	public String getIssueTypeStr() {
		return issueTypeStr;
	}

	public void setIssueTypeStr(String issueTypeStr) {
		this.issueTypeStr = issueTypeStr;
	}

	public IssueDetail() {
	}

	public long getIssueId() {
		return this.issueId;
	}

	public void setIssueId(long issueId) {
		this.issueId = issueId;
	}

	public Integer getBankId() {
		return this.bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Integer getIssueCategory() {
		return this.issueCategory;
	}

	public void setIssueCategory(Integer issueCategory) {
		this.issueCategory = issueCategory;
	}

	public Integer getIssueCurOwner() {
		return this.issueCurOwner;
	}

	public void setIssueCurOwner(Integer issueCurOwner) {
		this.issueCurOwner = issueCurOwner;
	}

	public String getIssueDesc() {
		return this.issueDesc;
	}

	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}

	public Date getIssueLastUpdate() {
		return this.issueLastUpdate;
	}

	public void setIssueLastUpdate(Date issueLastUpdate) {
		this.issueLastUpdate = issueLastUpdate;
	}

	public Integer getIssueLoggedBy() {
		return this.issueLoggedBy;
	}

	public void setIssueLoggedBy(Integer issueLoggedBy) {
		this.issueLoggedBy = issueLoggedBy;
	}

	public Date getIssueLoggedOn() {
		return this.issueLoggedOn;
	}

	public void setIssueLoggedOn(Date issueLoggedOn) {
		this.issueLoggedOn = issueLoggedOn;
	}

	public Integer getIssueResolvedBy() {
		return this.issueResolvedBy;
	}

	public void setIssueResolvedBy(Integer issueResolvedBy) {
		this.issueResolvedBy = issueResolvedBy;
	}

	public Date getIssueResolvedOn() {
		return this.issueResolvedOn;
	}

	public void setIssueResolvedOn(Date issueResolvedOn) {
		this.issueResolvedOn = issueResolvedOn;
	}

	public String getIssueSolution() {
		return this.issueSolution;
	}

	public void setIssueSolution(String issueSolution) {
		this.issueSolution = issueSolution;
	}

	public Integer getIssueStatus() {
		return this.issueStatus;
	}

	public void setIssueStatus(Integer issueStatus) {
		this.issueStatus = issueStatus;
	}

	public Integer getIssueSubCategory() {
		return this.issueSubCategory;
	}

	public void setIssueSubCategory(Integer issueSubCategory) {
		this.issueSubCategory = issueSubCategory;
	}

	public String getIssueTitle() {
		return this.issueTitle;
	}

	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}

	public Integer getIssueType() {
		return this.issueType;
	}

	public void setIssueType(Integer issueType) {
		this.issueType = issueType;
	}

	public Integer getIssueUpdatedBy() {
		return this.issueUpdatedBy;
	}

	public void setIssueUpdatedBy(Integer issueUpdatedBy) {
		this.issueUpdatedBy = issueUpdatedBy;
	}

	public Integer getIssueVersion() {
		return this.issueVersion;
	}

	public void setIssueVersion(Integer issueVersion) {
		this.issueVersion = issueVersion;
	}

	public Integer getSfmsVer() {
		return this.sfmsVer;
	}

	public void setSfmsVer(Integer sfmsVer) {
		this.sfmsVer = sfmsVer;
	}

	public String getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	@PrePersist
	void createdAt() {
		this.issueLoggedOn = this.issueLastUpdate = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.issueLastUpdate = new Date();
	}
}