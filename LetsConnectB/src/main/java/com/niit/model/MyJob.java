package com.niit.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Table(name="My_Job", schema="ANGULARDB")
@Entity
public class MyJob {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long jobId;
	@Size(min=5, max=50, message="Job Title should be 5 - 50 characters.")
	private String title;
	@Size(min=5, max=300, message="Your description should be between 5 - 300 characters.")
	private String description;
	private Date dated;
	@Size(min=2, max=50, message="Your qualifications should be between 2 - 50 characters.")
	private String qualification;
	private String status;
	@Size(min=3, max=30, message="Your company name should be between 3 - 30 characters.")
	private String Companyname;
	private Integer userId;
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDated() {
		return dated;
	}
	public void setDated(Date dated) {
		this.dated = dated;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCompanyname() {
		return Companyname;
	}
	public void setCompanyname(String companyname) {
		Companyname = companyname;
	}
	
}
