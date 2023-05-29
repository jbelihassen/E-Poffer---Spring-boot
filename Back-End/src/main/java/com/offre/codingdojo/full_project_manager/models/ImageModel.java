package com.offre.codingdojo.full_project_manager.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="images")
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Column(length=50000000)
    private byte[] picbyte;
    
    public ImageModel() {}

	public ImageModel(String name, String type, byte[] picbyte) {
		this.name = name;
		this.type = type;
		this.picbyte = picbyte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicbyte() {
		return picbyte;
	}

	public void setPicbyte(byte[] picbyte) {
		this.picbyte = picbyte;
	}
	
    
    

}
