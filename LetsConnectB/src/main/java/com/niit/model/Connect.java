package com.niit.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Table(name="Connect_User", schema="ANGULARDB")
@Entity
public class Connect implements Serializable 
{   @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userid;
	@Column(unique = true)
	@Pattern(regexp = ".+@.+\\..+", message = "Wrong email!")
	private String email;
	@Size(min = 1, max = 10, message = "Your name should be between 1 to 10 characters.")
	private String username;
	@NotNull(message = "Please enter a password")
	@Length(min = 4, max = 10, message = "Password should be between 4 to 10 charactes")
	private String password;
	@Size(min = 10, message = "You cannot entered lessthan 10 digits.")
	private String contactno;
	private String role;
	private boolean isactive;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
}
