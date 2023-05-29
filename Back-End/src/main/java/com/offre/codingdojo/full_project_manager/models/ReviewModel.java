package com.offre.codingdojo.full_project_manager.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="reviews")
public class ReviewModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Title must not be blank")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters !")
	private String title;
	
	@NotNull(message="Enter your rate please")
	@Min(0)
	@Max(5)
	private int rate;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    // many to one with project
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id")
    //@JsonIgnoreProperties(value="projectReview", allowSetters = true)
    @JsonProperty(access = Access.WRITE_ONLY)
    private ProjectModel projectReview;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    //@JsonIgnoreProperties(value="postedBy", allowSetters = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
    private UserModel postedBy;
    
    
    public UserModel getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(UserModel postedBy) {
		this.postedBy = postedBy;
	}
	public Long getId() {
		return id;
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
	public ProjectModel getProjectReview() {
		return projectReview;
	}
	public void setProjectReview(ProjectModel projectReview) {
		this.projectReview = projectReview;
	}
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    public ReviewModel() {}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
    
}
