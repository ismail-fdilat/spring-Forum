package com.formation.msglc.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@NamedQuery(name="Auteur.getAuteursMC", query = "SELECT a FROM Auteur a WHERE a.nom LIKE ?1")
@Entity
public class Auteur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="idAuteur")
	private Long id;
	
	@Email
	@Size(max = 50)
	@NotEmpty
	@Column(unique = true)
	@Pattern(regexp = ".+@.+\\..+", message = "Mauvise adresse Email")
	private String email;
	
	@NotEmpty
	@Size(max=40)
	@Column(length = 40)
	private String nom;
	
	@NotEmpty
	@Size(max=40)
	@Column(length = 40)
	private String prenom;
	
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	@NotEmpty
	@Size(max=26)
	@Column(length = 26)
	private String pays;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 1)
	@NotNull(message = "Veuillez indiquer votre sexe!")
	private Sexe sexe;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 9)
	private AuteurRole role;

	@Lob
	@Size(max = 500000)
	private byte[] photo;
	
	@NotEmpty
	@Size(min=6)
	private String password;
	
	@OneToMany(mappedBy = "auteur", fetch = FetchType.LAZY)
	private List<Reponse> reponses;
	
	@OneToMany(mappedBy = "auteur")
	private List<Sujet> sujets;
	
	@ManyToMany
	@JoinTable(name = "SujetSuivi",
	joinColumns = @JoinColumn(name="idAteur"),
	inverseJoinColumns = @JoinColumn(name="idSujet"))
	private List<Sujet> sujetsSuivis;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "follow",
	joinColumns = @JoinColumn(name="auteur_followings"),
	inverseJoinColumns = @JoinColumn(name="auteur_followers"))
    private List<Auteur> followings;

    @ManyToMany(mappedBy="followings",fetch=FetchType.EAGER)
    private List<Auteur> followers;

	private Long following;
	private Long follower;
	public Long getId() {
		return id;
	}
	public List<Auteur> getFollowings() {
		return followings;
	}
	public void setFollowings(List<Auteur> followings) {
		this.followings = followings;
	}
	public List<Auteur> getFollowers() {
		return followers;
	}
	public void setFollowers(List<Auteur> followers) {
		this.followers = followers;
	}
	public Long getFollowing() {
		return following;
	}
	public void setFollowing(Long following) {
		this.following = following;
	}
	public Long getFollower() {
		return follower;
	}
	public void setFollower(Long follower) {
		this.follower = follower;
	}
	public Auteur(Long id,
			@Email @Size(max = 50) @NotEmpty @Pattern(regexp = ".+@.+\\..+", message = "Mauvise adresse Email") String email,
			@NotEmpty @Size(max = 40) String nom, @NotEmpty @Size(max = 40) String prenom, Date dateNaissance,
			@NotEmpty @Size(max = 26) String pays, @NotNull(message = "Veuillez indiquer votre sexe!") Sexe sexe,
			AuteurRole role, @Size(max = 500000) byte[] photo, @NotEmpty @Size(min = 6) String password,
			List<Reponse> reponses, List<Sujet> sujets, List<Sujet> sujetsSuivis, List<Auteur> followings,
			List<Auteur> followers, Long following, Long follower) {
		super();
		this.id = id;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.pays = pays;
		this.sexe = sexe;
		this.role = role;
		this.photo = photo;
		this.password = password;
		this.reponses = reponses;
		this.sujets = sujets;
		this.sujetsSuivis = sujetsSuivis;
		this.followings = followings;
		this.followers = followers;
		this.following = following;
		this.follower = follower;
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	public AuteurRole getRole() {
		return role;
	}
	public void setRole(AuteurRole role) {
		this.role = role;
	}
	public Auteur() {
		super();
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public List<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}
	public List<Sujet> getSujets() {
		return sujets;
	}
	public void setSujets(List<Sujet> sujets) {
		this.sujets = sujets;
	}
	public List<Sujet> getSujetsSuivis() {
		return sujetsSuivis;
	}
	public void setSujetsSuivis(List<Sujet> sujetsSuivis) {
		this.sujetsSuivis = sujetsSuivis;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
