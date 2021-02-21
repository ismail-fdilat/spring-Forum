package com.formation.msglc.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SousCategorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSousCategorie")
	private Long id;
	
	@Column(length = 60)
	private String description;
	
	private boolean valide;
	
	@ManyToOne
	@JoinColumn(name = "idCategorie")
	private Categorie categorie;
	
	@OneToMany(mappedBy = "sousCategorie")
	private List<Sujet> sujets;
	
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
	public SousCategorie() {
		super();
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public SousCategorie(String description,Categorie categorie, //<-----
			boolean valide) {
		super();
		this.description = description;
		this.valide = valide;
		this.categorie=categorie;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public List<Sujet> getSujets() {
		return sujets;
	}
	public void setSujets(List<Sujet> sujets) {
		this.sujets = sujets;
	}
	
	

}
