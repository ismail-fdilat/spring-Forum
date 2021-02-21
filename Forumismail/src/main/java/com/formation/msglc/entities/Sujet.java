package com.formation.msglc.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Sujet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSujet")
	private Long id;
	
	@Column(length = 60)
	private String intitule;
	
	private String contenu;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateHeureCreation;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateHeureEdition;
	
	private boolean valide;
	
	private boolean resolu;
	private boolean notifCreateur;
	private int nbreAffichage;
	private int nbreMessage;
	
	@ManyToOne
	@JoinColumn(name = "idSousCategorie")
	private SousCategorie sousCategorie;
	
	@OneToMany(mappedBy = "sujet")
	private List<Reponse> reponses;
	
	@ManyToOne
	@JoinColumn(name="idAuteur")
	private Auteur auteur;
	
	@ManyToMany(mappedBy = "sujetsSuivis")
	private List<Auteur> interesses;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Date getDateHeureCreation() {
		return dateHeureCreation;
	}
	public void setDateHeureCreation(Date dateHeureCreation) {
		this.dateHeureCreation = dateHeureCreation;
	}
	public Date getDateHeureEdition() {
		return dateHeureEdition;
	}
	public void setDateHeureEdition(Date dateHeureEdition) {
		this.dateHeureEdition = dateHeureEdition;
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public boolean isResolu() {
		return resolu;
	}
	public void setResolu(boolean resolu) {
		this.resolu = resolu;
	}
	public boolean isNotifCreateur() {
		return notifCreateur;
	}
	public void setNotifCreateur(boolean notifCreateur) {
		this.notifCreateur = notifCreateur;
	}
	public int getNbreAffichage() {
		return nbreAffichage;
	}
	public void setNbreAffichage(int nbreAffichage) {
		this.nbreAffichage = nbreAffichage;
	}
	public int getNbreMessage() {
		return nbreMessage;
	}
	public void setNbreMessage(int nbreMessage) {
		this.nbreMessage = nbreMessage;
	}
	public Sujet() {
		super();
	}
	public Sujet(String intitule, String contenu, boolean valide, boolean notifCreateur, Auteur auteur, SousCategorie sousCategorie) {//<-----
		super();
		this.intitule = intitule;
		this.contenu = contenu;
		this.valide = valide;
		this.notifCreateur = notifCreateur;
		this.auteur=auteur;
		this.sousCategorie=sousCategorie;
		this.dateHeureCreation=new Date();
		this.dateHeureEdition= new Date();
	}
	public SousCategorie getSousCategorie() {
		return sousCategorie;
	}
	public void setSousCategorie(SousCategorie sousCategorie) {
		this.sousCategorie = sousCategorie;
	}
	public List<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}
	public Auteur getAuteur() {
		return auteur;
	}
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	public List<Auteur> getInteresses() {
		return interesses;
	}
	public void setInteresses(List<Auteur> interesses) {
		this.interesses = interesses;
	}
	
	

}
