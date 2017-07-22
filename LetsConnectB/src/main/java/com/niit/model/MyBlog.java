package com.niit.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Table(name="My_blog", schema="ANGULARDB")
@Entity
public class MyBlog implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long blogId;
	@Size(min=5,max=50, message="Blog title should be between 5 to 50 characters.")
	private String title;
	@Size(min=5,max=1000, message="Blog description should be between 5 to 1000 characters.")
	private String description;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="userid")
	private Connect userId;
	private String status;
	public long getBlogId() {
		return blogId;
	}
	public void setBlogId(long blogId) {
		this.blogId = blogId;
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
	
	
	public Connect getUserId() {
		return userId;
	}
	public void setUserId(Connect userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	}
