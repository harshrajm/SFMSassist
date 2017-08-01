package com.sfms.sfmsassist.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;



/**
 * The persistent class for the ASSIGNING_DETAILS database table.
 * 
 */
@Entity
@Table(name="ASSIGNING_DETAILS")
@NamedQuery(name="AssigningDetail.findAll", query="SELECT a FROM AssigningDetail a")
public class AssigningDetail implements Serializable {
	private static final long serialVersionUID = 1L;

    @GeneratedValue(generator="seq_assigning_details")
    @SequenceGenerator(name="seq_assigning_details",sequenceName="SEQ_ASSIGNING_DETAILS", allocationSize=1)
	@Id
	@Column(name="ASSIGN_ID")
	private long assignId;

	@Version
	@Column(name="ASSIGNING_DETAILS_VERSION")
	private Integer assigningDetailsVersion;

	@Column(name="CREATED_BY")
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="ISSUE_ASSIGNED_BY")
	private Integer issueAssignedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ISSUE_ASSIGNED_ON")
	private Date issueAssignedOn;

	@Column(name="ISSUE_ASSIGNED_TO")
	private Integer issueAssignedTo;

	@Column(name="ISSUE_ID")
	private Integer issueId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	@Column(name="UPDATED_BY")
	private Integer updatedBy;

	@Transient
	@JsonProperty("fromName")
	private String fromName;

	@Transient
	@JsonProperty("toName")
	private String toName;

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public AssigningDetail() {
	}

	public long getAssignId() {
		return this.assignId;
	}

	public void setAssignId(long assignId) {
		this.assignId = assignId;
	}

	public Integer getAssigningDetailsVersion() {
		return this.assigningDetailsVersion;
	}

	public void setAssigningDetailsVersion(Integer assigningDetailsVersion) {
		this.assigningDetailsVersion = assigningDetailsVersion;
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

	public Integer getIssueAssignedBy() {
		return this.issueAssignedBy;
	}

	public void setIssueAssignedBy(Integer issueAssignedBy) {
		this.issueAssignedBy = issueAssignedBy;
	}

	public Date getIssueAssignedOn() {
		return this.issueAssignedOn;
	}

	public void setIssueAssignedOn(Date issueAssignedOn) {
		this.issueAssignedOn = issueAssignedOn;
	}

	public Integer getIssueAssignedTo() {
		return this.issueAssignedTo;
	}

	public void setIssueAssignedTo(Integer issueAssignedTo) {
		this.issueAssignedTo = issueAssignedTo;
	}

	public Integer getIssueId() {
		return this.issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
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
		this.createdOn = this.issueAssignedOn = this.lastUpdate  = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.lastUpdate = new Date();
	}
}