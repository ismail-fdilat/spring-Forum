package com.formation.msglc.models;

public class Post 
{
	private String contenu;
	private String intitule;
	private String notifCreateur;
	private String valide;
	private int idPage;
	private Long id=0L;
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getNotifCreateur() {
		return notifCreateur;
	}
	public void setNotifCreateur(String notifCreateur) {
		this.notifCreateur = notifCreateur;
	}
	public String getValide() {
		return valide;
	}
	public void setValide(String valide) {
		this.valide = valide;
	}
	public int getIdPage() {
		return idPage;
	}
	public void setIdPage(int idPage) {
		this.idPage = idPage;
	}
	public Post() {
		super();
	}
	public Post(Post post) {
		this.contenu=post.getContenu();
		this.id=post.getId();
		this.intitule=post.getIntitule();
		this.idPage=post.getIdPage();
		this.notifCreateur=post.getNotifCreateur();
		this.valide=post.getValide();
		
	}
	public Post(int idPage) {
		this.idPage = idPage;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
