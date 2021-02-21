package com.formation.msglc.dao;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.formation.msglc.entities.Auteur;
import com.formation.msglc.entities.AuteurRole;
import com.formation.msglc.entities.Categorie;
import com.formation.msglc.entities.Reponse;
import com.formation.msglc.entities.SousCategorie;
import com.formation.msglc.entities.Sujet;

@Transactional
public class ForumDaoImpl implements IForumDao{
	
	@PersistenceContext(unitName = "Forum")
	private EntityManager em;

	@Override
	public Auteur addAuteur(Auteur auteur) {
		em.persist(auteur);
		return auteur;
	}

	@Override
	public Auteur getAuteur(Long id) {
		Auteur auteur=em.find(Auteur.class,id);
		if(auteur==null)
			throw new RuntimeException("Auteur introuvable !!");
		return auteur;
	}

	@Override
	public void deleteAuteur(Long id) {
		Auteur auteur=em.find(Auteur.class,id);
		if(auteur==null)
			throw new RuntimeException("Auteur introuvable !!");
		em.remove(auteur);
		
	}

	@Override
	public Auteur updateAuteur(Auteur auteur) {
		Auteur a=em.find(Auteur.class,auteur.getId());
		if(a==null)
			throw new RuntimeException("Auteur introuvable !!");
		a.setNom(auteur.getNom());
		a.setPrenom(auteur.getPrenom());
		a.setEmail(auteur.getEmail());
		a.setDateNaissance(auteur.getDateNaissance());
		a.setPassword(auteur.getPassword());
		a.setPays(auteur.getPays());
		if(auteur.getPhoto()!=null)
			a.setPhoto(auteur.getPhoto());
		a.setSexe(auteur.getSexe());
		return a;
	}

	@Override
	public List<Auteur> getAllAuteurs() {
		Query req=em.createNamedQuery("Auteur.getAll");
		return req.getResultList();
	}

	@Override
	public List<Auteur> getAuteursMC(String mc) {
		Query req=em.createNamedQuery("Auteur.getAuteursMC").setParameter(1, "%"+mc+"%");
		return req.getResultList();
	}

	@Override
	public Auteur updateRole(Long id, AuteurRole role) {
		Auteur auteur=em.find(Auteur.class,id);
		if(auteur==null)
			throw new RuntimeException("Auteur introuvable !!");
		auteur.setRole(role);
		return auteur;
	}

	@Override
	public List<Auteur> getAuteurByRole(AuteurRole role) {
		Query req=em.createNamedQuery("Auteur.getAuteurByRole").setParameter(1, role);
		return req.getResultList();
	}
///////////////////////////////////////////////////////////////
	@Override
	public Categorie addCategorie(Categorie categorie) {
		em.persist(categorie);
		return categorie;
	}

	@Override
	public Categorie getCategorie(Long id) {
		Categorie categorie=em.find(Categorie.class,id);
		if(categorie==null)
			throw new RuntimeException("Categorie introuvable !!");
		return categorie;
	}

	@Override
	public List<Categorie> getAllCategories() {
		Query req=em.createNamedQuery("Categorie.getAll");
		return req.getResultList();
	}

	@Override
	public List<Categorie> getCategoriesMC(String mc) {
		Query req=em.createNamedQuery("Categorie.getCategoriesMC").setParameter(1, "%"+mc+"%");
		return req.getResultList();
	}

	@Override
	public List<Categorie> getCategoriesToValidate() {
		Query req=em.createNamedQuery("Categorie.getCategoriesToValidate");
		return req.getResultList();
	}

	@Override
	public Categorie validerCategorie(Long id) {
		Categorie categorie=em.find(Categorie.class, id);
		if(categorie==null)
			throw new RuntimeException("Categorie introuvable !!");
		categorie.setValide(true);
		return categorie;
	}

	@Override
	public SousCategorie addSousCategorie(SousCategorie sousCategorie) {
		em.persist(sousCategorie);
		return sousCategorie;
	}

	@Override
	public SousCategorie getSousCategorie(Long id) {
		SousCategorie sc=em.find(SousCategorie.class, id);
		if(sc==null)
			throw new RuntimeException("SousCategorie introuvable");
		return sc;
	}

	@Override
	public List<SousCategorie> getAllSousCategories() {
		Query req=em.createNamedQuery("SousCategorie.getAll");
		return req.getResultList();
	}

	@Override
	public List<SousCategorie> getSousCategoriesMC(String mc) {
		Query req=em.createNamedQuery("SousCategorie.getSousCategoriesMC")
				.setParameter(1, "%"+mc+"%");
		return req.getResultList();
	}

	@Override
	public List<SousCategorie> getSousCategoriesToValidate() {
		Query req=em.createNamedQuery("SousCategorie.getSousCategoriesToValidate");
		return req.getResultList();
	}

	@Override
	public SousCategorie validerSousCategorie(Long id) {
		SousCategorie sousCategorie=em.find(SousCategorie.class, id);
		if(sousCategorie==null)
			throw new RuntimeException("sousCategorie introuvable !!");
		sousCategorie.setValide(true);
		return sousCategorie;
	}
	
	@Override
	public List<SousCategorie> getSousCategoriesByCategorie(Long idCategorie){
		Query req=em.createNamedQuery("SousCategorie.getSousCategoriesByCategorie")
				.setParameter("id", idCategorie);
		return req.getResultList();
	}

	@Override
	public Sujet addSujet(Sujet sujet) {
		em.persist(sujet);
		if(sujet.isNotifCreateur()==true)
		{
			Auteur auteur=em.find(Auteur.class, sujet.getAuteur().getId());
			auteur.getSujetsSuivis().add(sujet);
			sujet.setInteresses(new ArrayList<Auteur>());
			sujet.getInteresses().add(auteur);
		}
		return sujet;
	}

	@Override
	public Sujet getSujet(Long id) {
		Sujet sujet=em.find(Sujet.class, id);
		if(sujet == null)
			throw new RuntimeException("Sujet introuvable !!");
		return sujet;
	}

	@Override
	public List<Sujet> getSujetBySousCategorie(Long idSousCategorie) {
		Query req=em.createNamedQuery("Sujet.getSujetBySousCategorie")
				.setParameter(1, idSousCategorie);
		return req.getResultList();
	}

	@Override
	public List<Sujet> getSujetByAuteur(Long idAuteur) {
		Query req=em.createNamedQuery("Sujet.getSujetByAuteur")
				.setParameter(1, idAuteur);
		return req.getResultList();
	}

	@Override
	public List<Sujet> getSujetsToValidate() {
		Query req=em.createNamedQuery("Sujet.getSujetsToValidate");
		return req.getResultList();
	}

	@Override
	public Sujet validerSujet(Long id) {
		Sujet s=em.find(Sujet.class, id);
		if(s==null)
			throw new RuntimeException("Sujet introuvable !!");
		s.setValide(true);
		return s;
	}

	@Override
	public Reponse addReponse(Reponse reponse) {
		em.persist(reponse);
		Sujet sujet=em.find(Sujet.class,reponse.getSujet().getId());
		sujet.setNbreMessage(sujet.getNbreMessage()+1);
		if(reponse.isNotifCreateur()==true)
		{
			Auteur auteur=em.find(Auteur.class, reponse.getAuteur().getId());
			if(!auteur.getSujetsSuivis().contains(sujet))
			{
				auteur.getSujetsSuivis().add(sujet);
				sujet.getInteresses().add(auteur);
			}
		}
		return reponse;
	}

	@Override
	public Reponse getReponse(Long id) {
		Reponse r=em.find(Reponse.class, id);
		if(r==null)
			throw new RuntimeException("Reponse introuvable");
		return r;
	}

	@Override
	public List<Reponse> getReponseBySujet(Long idSujet) {
		Query req=em.createNamedQuery("Reponse.getReponseBySujet").setParameter(1, idSujet);
		return req.getResultList();
	}

	@Override
	public List<Reponse> getReponsesToValidate() {
		Query req=em.createNamedQuery("Reponse.getReponsesToValidate");
		return req.getResultList();
	}

	@Override
	public Reponse validerReponse(Long id) {
		Reponse r=em.find(Reponse.class, id);
		if(r==null)
			throw new RuntimeException("Reponse introuvable !!");
		r.setValide(true);
		return r;
	}

	@Override
	public List<Reponse> getReponseByAuteur(Long idAuteur) {
		Query req=em.createNamedQuery("Reponse.getReponseByAuteur").setParameter(1, idAuteur);
		return req.getResultList();
	}

	@Override
	public Auteur testeLogin(String email, String password) {
		Query req=em.createNamedQuery("Auteur.testeLogin").setParameter(1, email);
		List<Auteur>ls=req.getResultList();
		Auteur auteur=null;
		if(!ls.isEmpty())
		{
			auteur=ls.get(0);
			if(auteur.getEmail().equals(email) && auteur.getPassword().equals(password))
			return auteur;
		}
		return null;
	}



	@Override
	public void updateFollow(Long idF, Long idT) {
		Auteur auteurF =em.find(Auteur.class,idF);
		Auteur auteurT =em.find(Auteur.class,idT);		
		System.out.println("dao assat");
		if(auteurF.getFollowings().contains(auteurT)) 
		{
		      if(auteurT.getFollowers().contains(auteurF)) {
		    		auteurF.getFollowings().remove(auteurT);
					auteurT.getFollowers().remove(auteurF);
		            auteurF.setFollowing(auteurF.getFollowing()-1);
		            auteurT.setFollower(auteurT.getFollower()-1); 
		      }
		      else {
		    		auteurF.getFollowings().remove(auteurT);
				    auteurF.setFollowing(auteurF.getFollowing()-1);
		      }
		    return;
		}
		auteurF.getFollowings().add(auteurT);
		auteurT.getFollowers().add(auteurF);
        auteurF.setFollowing(auteurF.getFollowing()+1);
        auteurT.setFollower(auteurT.getFollower()+1); 
     }
}