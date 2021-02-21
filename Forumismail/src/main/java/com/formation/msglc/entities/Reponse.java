package com.formation.msglc.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idReponse")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePost;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEdition;
	private int score=0;
	
	private String contenu;
	private boolean valide;
	
	private boolean notifCreateur;
	
	@ManyToOne
	@JoinColumn(name = "idSujet")
	private Sujet sujet;
	
	@ManyToOne
	@JoinColumn(name = "idAuteur")
	private Auteur auteur;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDatePost() {
		return datePost;
	}
	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}
	public Date getDateEdition() {
		return dateEdition;
	}
	public void setDateEdition(Date dateEdition) {
		this.dateEdition = dateEdition;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public Reponse() {
		super();
	}
	public Reponse(String contenu, Sujet sujet, boolean valide,
			boolean notifCreateur, Auteur auteur) { //<-----
		super();
		this.contenu = contenu;
		this.valide = valide;
		this.notifCreateur=notifCreateur;
		this.sujet=sujet;//<-----
		this.auteur=auteur;//<-----
		this.dateEdition=new Date();
		this.datePost=new Date();
	}
	public Sujet getSujet() {
		return sujet;
	}
	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}
	public Auteur getAuteur() {
		return auteur;
	}
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	public boolean isNotifCreateur() {
		return notifCreateur;
	}
	public void setNotifCreateur(boolean notifCreateur) {
		this.notifCreateur = notifCreateur;
	}
	

}
