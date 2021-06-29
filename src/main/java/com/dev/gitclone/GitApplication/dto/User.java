package com.dev.gitclone.GitApplication.dto;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	
	    private  String username;
	    private  String password;
	    private  String email;
	    private  String mobileNum;
	    private  boolean loggedIn;
	    public User() {
	    }
	    public User(String username, 
	                 String password) {
	        this.username = username;
	        this.password = password;
	        this.loggedIn = false;
	    }
	    public int getId() {
	        return id;
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
	    public boolean isLoggedIn() {
	        return loggedIn;
	    }
	    public void setLoggedIn(boolean loggedIn) {
	        this.loggedIn = loggedIn;
	    }
	    
	    public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobileNum() {
			return mobileNum;
		}
		public void setMobileNum(String mobileNum) {
			this.mobileNum = mobileNum;
		}
		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof User)) return false;
	        User user = (User) o;
	        return Objects.equals(username, user.username) &&
	                Objects.equals(password, user.password);
	    }
	    @Override
	    public int hashCode() {
	        return Objects.hash(id, username, password, 
	                            loggedIn);
	    }
		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
					+ ", mobileNum=" + mobileNum + ", loggedIn=" + loggedIn + "]";
		}
	   
	}
