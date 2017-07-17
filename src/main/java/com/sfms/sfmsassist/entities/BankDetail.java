package com.sfms.sfmsassist.entities;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.Integer;
import java.util.Date;


/**
 * The persistent class for the BANK_DETAILS database table.
 * 
 */
@Entity
@Table(name="BANK_DETAILS")
@NamedQuery(name="BankDetail.findAll", query="SELECT b FROM BankDetail b")
public class BankDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BANK_ID")
	private long bankId;

	@Column(name="BANK_CODE")
	private String bankCode;

	@Version
	@Column(name="BANK_DETAILS_VERSION")
	private Integer bankDetailsVersion;

	@Column(name="BANK_NAME")
	private String bankName;

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

	public BankDetail() {
	}

	public long getBankId() {
		return this.bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Integer getBankDetailsVersion() {
		return this.bankDetailsVersion;
	}

	public void setBankDetailsVersion(Integer bankDetailsVersion) {
		this.bankDetailsVersion = bankDetailsVersion;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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


	@PrePersist
	void createdAt() {
		this.createdOn = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.lastUpdate = new Date();
	}
}