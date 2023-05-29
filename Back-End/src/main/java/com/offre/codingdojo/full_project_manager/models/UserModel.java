package com.offre.codingdojo.full_project_manager.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="first Name is required!")
    @Size(min=3, max=30, message="first Name must be between 3 and 30 characters")
    private String firstName;
    
    
    @NotEmpty(message="last Name is required!")
    @Size(min=3, max=30, message="last Name must be between 3 and 30 characters")
    private String lastName;
    
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty(message="phone Number is required!")
    @Size(min=8, max=20, message="phone Number is required!")
    private String phoneNumber;
    
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
  
    //one to many
    @OneToMany(mappedBy="posted_user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonIgnoreProperties(value="posted_user", allowSetters = true)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<ProjectModel> projects;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_projects", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    //@JsonIgnoreProperties(value="userProjects", allowSetters = true)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<ProjectModel> userProjects;
    
    // one to many with reviews
    @OneToMany(mappedBy="postedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonIgnoreProperties(value="postedBy", allowSetters = true)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<ReviewModel> reviews;

	public List<ReviewModel> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewModel> reviews) {
		this.reviews = reviews;
	}

	public List<ProjectModel> getUserProjects() {
		return userProjects;
	}

	public void setUserProjects(List<ProjectModel> userProjects) {
		this.userProjects = userProjects;
	}

	public UserModel() {}
	
    
  public UserModel(
			String firstName,
			String lastName,
			 String email,
			String phoneNumber,
			 String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password ;
		
	}
  

	public UserModel(Long id,
		@NotEmpty(message = "first Name is required!") @Size(min = 3, max = 30, message = "first Name must be between 3 and 30 characters") String firstName,
		@NotEmpty(message = "last Name is required!") @Size(min = 3, max = 30, message = "last Name must be between 3 and 30 characters") String lastName,
		@NotEmpty(message = "Email is required!") @Email(message = "Please enter a valid email!") String email,
		@NotEmpty(message = "phone Number is required!") @Size(min = 8, max = 20, message = "phone Number is required!") String phoneNumber,
		@NotEmpty(message = "Password is required!") @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") String password,
		Date createdAt, Date updatedAt, List<ProjectModel> projects, List<ProjectModel> userProjects,
		List<ReviewModel> reviews) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.password = password;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.projects = projects;
	this.userProjects = userProjects;
	this.reviews = reviews;
}

	//getter and setter
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    
    
    
    /// Getters And Setters
    
    
    
    

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<ProjectModel> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectModel> projects) {
		this.projects = projects;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
    
    


}
