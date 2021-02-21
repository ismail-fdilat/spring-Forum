package com.formation.msglc.metier;

import java.util.List;


import com.formation.msglc.dao.IForumDao;
import com.formation.msglc.entities.Auteur;
import com.formation.msglc.entities.AuteurRole;
import com.formation.msglc.entities.Categorie;
import com.formation.msglc.entities.Reponse;
import com.formation.msglc.entities.SousCategorie;
import com.formation.msglc.entities.Sujet;

public class ForumMetierImpl implements IForumMetier{

	private IForumDao dao;
	
	
	public IForumDao getDao() {
		return dao;
	}

	public void setDao(IForumDao dao) {
		this.dao = dao;
	}

	public Auteur addAuteur(Auteur auteur) {
		return dao.addAuteur(auteur);
	}

	@Override
	public Auteur getAuteur(Long id) {
		return dao.getAuteur(id);
	}

	@Override
	public void deleteAuteur(Long id) {
		dao.deleteAuteur(id);
		
	}

	@Override
	public Auteur updateAuteur(Auteur auteur) {
		return dao.updateAuteur(auteur);
	}

	@Override
	public List<Auteur> getAllAuteurs() {
		return dao.getAllAuteurs();
	}

	@Override
	public List<Auteur> getAuteursMC(String mc) {
		return dao.getAuteursMC(mc);
	}

	@Override
	public Auteur updateRole(Long id, AuteurRole role) {
		return dao.updateRole(id, role);
	}

	@Override
	public List<Auteur> getAuteurByRole(AuteurRole role) {
		return dao.getAuteurByRole(role);
	}

	@Override
	public Categorie addCategorie(Categorie categorie) {
		return dao.addCategorie(categorie);
	}

	@Override
	public Categorie getCategorie(Long id) {
		return dao.getCategorie(id);
	}

	@Override
	public List<Categorie> getAllCategories() {
		return dao.getAllCategories();
	}

	@Override
	public List<Categorie> getCategoriesMC(String mc) {
		return dao.getCategoriesMC(mc);
	}

	@Override
	public List<Categorie> getCategoriesToValidate() {
		return dao.getCategoriesToValidate();
	}

	@Override
	public Categorie validerCategorie(Long id) {
		return dao.validerCategorie(id);
	}

	@Override
	public SousCategorie addSousCategorie(SousCategorie sousCategorie) {
		return dao.addSousCategorie(sousCategorie);
	}

	@Override
	public SousCategorie getSousCategorie(Long id) {
		return dao.getSousCategorie(id);
	}

	@Override
	public List<SousCategorie> getAllSousCategories() {
		return dao.getAllSousCategories();
	}

	@Override
	public List<SousCategorie> getSousCategoriesMC(String mc) {
		return dao.getSousCategoriesMC(mc);
	}

	@Override
	public List<SousCategorie> getSousCategoriesToValidate() {
		return dao.getSousCategoriesToValidate();
	}

	@Override
	public SousCategorie validerSousCategorie(Long id) {
		return dao.validerSousCategorie(id);
	}

	@Override
	public List<SousCategorie> getSousCategoriesByCategorie(Long idCategorie) {
		return dao.getSousCategoriesByCategorie(idCategorie);
	}

	@Override
	public Sujet addSujet(Sujet sujet) {
		return dao.addSujet(sujet);
	}

	@Override
	public Sujet getSujet(Long id) {
		return dao.getSujet(id);
	}

	@Override
	public List<Sujet> getSujetBySousCategorie(Long idSousCategorie) {
		return dao.getSujetBySousCategorie(idSousCategorie);
	}

	@Override
	public List<Sujet> getSujetByAuteur(Long idAuteur) {
		return dao.getSujetByAuteur(idAuteur);
	}

	@Override
	public List<Sujet> getSujetsToValidate() {
		return dao.getSujetsToValidate();
	}

	@Override
	public Sujet validerSujet(Long id) {
		return dao.validerSujet(id);
	}

	@Override
	public Reponse addReponse(Reponse reponse) {
		return dao.addReponse(reponse);
	}

	@Override
	public Reponse getReponse(Long id) {
		return dao.getReponse(id);
	}

	@Override
	public List<Reponse> getReponseBySujet(Long idSujet) {
		return dao.getReponseBySujet(idSujet);
	}

	@Override
	public List<Reponse> getReponsesToValidate() {
		return dao.getReponsesToValidate();
	}

	@Override
	public Reponse validerReponse(Long id) {
		return dao.validerReponse(id);
	}

	@Override
	public List<Reponse> getReponseByAuteur(Long idAuteur) {
		return dao.getReponseByAuteur(idAuteur);
	}

	@Override
	public Auteur testeLogin(String email, String password) {
		return dao.testeLogin(email, password);
	}

	@Override
	public void updateFollow(Long idF, Long idt) {
		System.out.println("9bel assat");
		dao.updateFollow(idF, idt);	
		
	}
}
