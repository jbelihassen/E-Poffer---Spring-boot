package com.offre.codingdojo.full_project_manager.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="projects")
public class ProjectModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Title must not be blank")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters !")
	private String title;

	@NotEmpty(message="Description must not be blank")
	@Size(min = 3, message = "Description must be at least 3  characters !")
	private String description;
	
	@NotNull(message="Initial Price must not be blank")
	private double iPrice;
	
	@NotNull(message="Offre Price must not be blank")
	private double oPrice;
	
	@NotNull(message="Condition Offre  must not be blank")
	private int cOffer;

	@NotEmpty(message="Choose the categorie of the offre")
	private String categorie;
	
	@NotNull(message="Due Date is required!")
	@FutureOrPresent(message="Due Date must not be in the future")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dueDate;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
  //many to one
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    //@JsonIgnoreProperties(value="user_id", allowSetters = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
    private UserModel posted_user;
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_projects", 
        joinColumns = @JoinColumn(name = "project_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    //@JsonIgnoreProperties(value="joinedUsers", allowSetters = true)
  // @JsonProperty(access = Access.WRITE_ONLY)
    private List<UserModel> joinedUsers;
    
    
    // one to many with reviews
    @OneToMany(mappedBy="projectReview", fetch = FetchType.LAZY)
    //@JsonIgnoreProperties(value="projectReview", allowSetters = true)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<ReviewModel> reviews;
    
    // many to many with images
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "project_images", 
        joinColumns = @JoinColumn(name = "project_id"), 
        inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    //@JsonIgnoreProperties(value="projectImages", allowSetters = true)
    //@JsonProperty(access = Access.WRITE_ONLY)
    private Set<ImageModel> projectImages;
    
    // constructors  
        
    public List<ReviewModel> getReviews() {
		return reviews;
	}
	public void setReviews(List<ReviewModel> reviews) {
		this.reviews = reviews;
	}
	public List<UserModel> getJoinedUsers() {
		return joinedUsers;
	}
	public void setJoinedUsers(List<UserModel> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}
	public ProjectModel() {}

	
	
	
	public ProjectModel(Long id,
			@NotEmpty(message = "Title must not be blank") @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters !") String title,
			@NotEmpty(message = "Description must not be blank") @Size(min = 3, message = "Description must be at least 3  characters !") String description,
			@NotNull(message = "Initial Price must not be blank") double iPrice,
			@NotNull(message = "Offre Price must not be blank") double oPrice,
			@NotNull(message = "Condition Offre  must not be blank") int cOffer,
			@NotEmpty(message = "Choose the categorie of the offre") String categorie,
			@NotNull(message = "Due Date is required!") @FutureOrPresent(message = "Due Date must not be in the future") Date dueDate,
			UserModel posted_user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.iPrice = iPrice;
		this.oPrice = oPrice;
		this.cOffer = cOffer;
		this.categorie = categorie;
		this.dueDate = dueDate;
		this.posted_user = posted_user;

	}
	
	
	//getter and setters
	
	
	
	public Long getId() {
		return id;
	}
	public double getiPrice() {
		return iPrice;
	}
	public void setiPrice(double iPrice) {
		this.iPrice = iPrice;
	}
	public double getoPrice() {
		return oPrice;
	}
	public void setoPrice(double oPrice) {
		this.oPrice = oPrice;
	}
	public int getcOffer() {
		return cOffer;
	}
	public void setcOffer(int cOffer) {
		this.cOffer = cOffer;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
	public UserModel getPosted_user() {
		return posted_user;
	}
	public void setPosted_user(UserModel posted_user) {
		this.posted_user = posted_user;
	}
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	public Set<ImageModel> getProjectImages() {
		return projectImages;
	}
	public void setProjectImages(Set<ImageModel> projectImages) {
		this.projectImages = projectImages;
	}

	
	

}
