package com.formation.msglc.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie {
	@Id
	@Column(name = "idCategorie")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 60)
	private String description;
	
	private boolean valide;
	
	@OneToMany(mappedBy = "categorie")
	private List<SousCategorie> sousCategories;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Categorie() {
		super();
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public Categorie(String description, boolean valide) {
		super();
		this.description = description;
		this.valide = valide;
	}
	public List<SousCategorie> getSousCategories() {
		return sousCategories;
	}
	public void setSousCategories(List<SousCategorie> sousCategories) {
		this.sousCategories = sousCategories;
	}
	
	

}
