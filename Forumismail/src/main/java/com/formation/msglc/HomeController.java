package com.formation.msglc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.formation.msglc.entities.Auteur;
import com.formation.msglc.entities.AuteurRole;
import com.formation.msglc.entities.Categorie;
import com.formation.msglc.entities.Reponse;
import com.formation.msglc.entities.SousCategorie;
import com.formation.msglc.entities.Sujet;
import com.formation.msglc.metier.IForumMetier;
import com.formation.msglc.models.Identifiant;
import com.formation.msglc.models.Message;
import com.formation.msglc.models.Nav;
import com.formation.msglc.models.Post;
import com.formation.msglc.models.Validation;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private IForumMetier metier;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "forward:/accueil";
	}

	@RequestMapping(value = "/inscription", method = RequestMethod.GET)
	public String ajoutAteur(Model model) {
		model.addAttribute("auteur", new Auteur());
		return "inscription";
	}
	
	@RequestMapping(value = "/inscription", method = RequestMethod.POST)
	protected String inscription(@Valid Auteur a, BindingResult bindingResult, Model model, MultipartFile file,
			HttpSession session) {
//         a.setFollower(0L);
//         a.setFollowing(0L);
//         List<Auteur> l =new ArrayList<Auteur>();
//         a.setFollowers(l);
//         a.setFollowings(l);
//         
		if (bindingResult.hasErrors()) {
			model.addAttribute("auteur", a);
			return "inscription";
		}

		if (!file.isEmpty()) {
			try {
				a.setPhoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		a.setRole(AuteurRole.USER);

		if (session.getAttribute("post") != null) {
			Post post = (Post) session.getAttribute("post");
			int idPage = post.getIdPage();
			Long id = post.getId();
			if (idPage == 1) {
				model.addAttribute("categories", metier.getAllCategories());
			} else if (idPage == 2) {
				model.addAttribute("sousCategories", metier.getSousCategoriesByCategorie(id));
			} else if (idPage == 3) {
				model.addAttribute("sujets", metier.getSujetBySousCategorie(id));
			} else {
				model.addAttribute("reponses", metier.getReponseBySujet(id));
			}
			model.addAttribute("post", new Post(post));
			
			if(post.getContenu()!=null && !post.getContenu().trim().equals(""))
			{
				addPost(model, a, post);
				session.removeAttribute("post");
				//session.removeAttribute("id");
				post.setContenu("");
				post.setIntitule("");
				post.setNotifCreateur(null);
				return "accueil";
			}
			else
			{
				session.removeAttribute("post");
				session.setAttribute("auteur", a);
				return "accueil";
			}
		} else {
			model.addAttribute("identifiant", new Identifiant());
			return "login";
		}
	}

	@RequestMapping(value = "/inscription/{id}/{idPage}")
	protected String inscription(@PathVariable(value = "id") Long id, @PathVariable(value = "idPage") int idPage,
			Model model, HttpSession session) {

		model.addAttribute("auteur", new Auteur());
		Post post = new Post(idPage);
		post.setId(id);
		session.setAttribute("post", post);
		return "inscription";
	}
	

	@RequestMapping(value = "/addAuteur", method = RequestMethod.POST)
	public String ajoutAteur(@Valid Auteur a, BindingResult bindingResult, Model model, MultipartFile file) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("auteur", a);
			return "inscription";
		}
		if (!file.isEmpty()) {
			try {
				a.setPhoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		a.setRole(AuteurRole.USER);
		 a.setFollower(0L);
         a.setFollowing(0L);
         List<Auteur> l =new ArrayList<Auteur>();
         a.setFollowers(l);
         a.setFollowings(l);
         
		metier.addAuteur(a);
		
		model.addAttribute("identifiant", new Identifiant()); //<------
		return "login";
	}

	@RequestMapping(value = "/Log", method = RequestMethod.GET)
	public String log(Model model) {
		model.addAttribute("identifiant", new Identifiant());
		return "login";
	}
	
	@RequestMapping(value = "/Log/{id}/{idPage}")
	public String log(@PathVariable(value = "id") Long id, @PathVariable(value = "idPage") int idPage,
			 Model model, HttpSession session) {
		Post post=new Post(idPage);
		post.setId(id);
		session.setAttribute("post", post);
		model.addAttribute("identifiant", new Identifiant());
		return "login";
	}

	@RequestMapping(value = "/Log", method = RequestMethod.POST)
	public String log(@Valid Identifiant iden, BindingResult bindingResult, Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("identifiant", iden);
			return "login";
		}
		Auteur a = metier.testeLogin(iden.getEmail(), iden.getPassword());
		if (a == null) {
			model.addAttribute("errorBean", new Message("Email ou mot de passe incorrecte !!"));
			model.addAttribute("identifiant", iden);
			return "login";
		}
		session.setAttribute("auteur", a);
		if(session.getAttribute("post")!=null)
		{
			Post post=(Post) session.getAttribute("post");
			int idPage =post.getIdPage();
			Long id=post.getId();
			if (idPage == 1) {
				model.addAttribute("categories", metier.getAllCategories());
			} else if (idPage == 2) {
				model.addAttribute("sousCategories", metier.getSousCategoriesByCategorie(id));
			} else if (idPage == 3) {
				model.addAttribute("sujets", metier.getSujetBySousCategorie(id));
			} else {
				model.addAttribute("reponses", metier.getReponseBySujet(id));
			}
			if(post.getContenu()!=null && !post.getContenu().trim().equals(""))
			{
				addPost(model, a, post);
				session.removeAttribute("post");
				//session.removeAttribute("id");
				post.setContenu("");
				post.setIntitule("");
				post.setNotifCreateur(null);
				model.addAttribute("post", new Post(post));
				return "accueil";
			}
			else
			{
				model.addAttribute("post", new Post(post));
				return "accueil";
			}
		}
		else
		{
			model.addAttribute("categories",metier.getAllCategories());
			model.addAttribute("post", new Post(1));
			Nav nav=(Nav) session.getAttribute("nav");
			if(nav==null)
			{
				nav=new Nav();
				session.setAttribute("nav", nav);
			}
			return "accueil";
		}
	}
	
	@RequestMapping(value = "/accueil", method = RequestMethod.GET)
	public String accueil(Model model, HttpSession session) {
		Nav nav=(Nav) session.getAttribute("nav");
		if(nav==null)
		{
			nav=new Nav();
			session.setAttribute("nav", nav);
		}
		model.addAttribute("categories", metier.getAllCategories());
		model.addAttribute("post", new Post(1));
		return "accueil";		
	}
	@RequestMapping(value= "/follow/{id}/{idReponse}",method=RequestMethod.GET)
	public String Follow( @PathVariable(value="id") Long id,@PathVariable(value="idReponse") Long idReponse,HttpSession session) {
	   System.out.println("hello world ");
		Auteur aut_follower =(Auteur)session.getAttribute("auteur");
               metier.updateFollow(aut_follower.getId(),id);
               Long idSujet = metier.getReponse(idReponse).getSujet().getId();
              
		return "redirect:/sujet/"+idSujet;
	}
	@RequestMapping(value= "/abonnement/{id}",method=RequestMethod.GET)
	public String Follow( @PathVariable(value="id") Long id,HttpSession session) {
	   System.out.println("hello world ");
		Auteur aut_follower =(Auteur)session.getAttribute("auteur");
               metier.updateFollow(aut_follower.getId(),id);
              
		return "abonnements";
	}

	@RequestMapping(value = "/addPost/{id}/{idPage}", method = RequestMethod.POST)
	protected String addPost(@PathVariable(value = "id") Long id, @PathVariable(value = "idPage") int idPage, Post post,
			@RequestParam(value = "send", required = false) String sendButton, BindingResult bindingResult, Model model,
			HttpSession session) {
		post.setId(id);
		post.setIdPage(idPage);
		if (sendButton != null) // Send Button pressed
		{
			Auteur a = (Auteur) session.getAttribute("auteur");
			if (a != null) {
				if (post.getContenu().trim().equals("")) {
					Message bean = new Message("Pas de message vide svp!");
					model.addAttribute("erreurBean", bean);
					if (idPage == 1)// add cat�gorie
					{
						model.addAttribute("categories", metier.getAllCategories());
					} else if (idPage == 2)// add sous cat�gorie
					{
						model.addAttribute("sousCategories", metier.getSousCategoriesByCategorie(id));
					} else if (idPage == 3)// add sujet
					{
						model.addAttribute("sujets", metier.getSujetBySousCategorie(id));
					} else if (idPage == 4)// add reponse
					{
						model.addAttribute("reponses", metier.getReponseBySujet(id));
					}
					model.addAttribute("post", new Post(post));
				} else {
					addPost(model,a,post);
				}
				post.setContenu("");
				post.setIntitule("");
				post.setNotifCreateur(null);
				model.addAttribute("post", new Post(post));
				return "accueil";
			} else if (post.getContenu().trim().equals("")) {
				Message bean = new Message("Pas de message vide svp!");
				model.addAttribute("post", new Post(post));
				model.addAttribute("erreurBean", bean);
				if (idPage == 1)// add cat�gorie
				{
					model.addAttribute("categories", metier.getAllCategories());
				} else if (idPage == 2)// add sous cat�gorie
				{
					model.addAttribute("sousCategories", metier.getSousCategoriesByCategorie(id));
				} else if (idPage == 3)// add sujet
				{
					model.addAttribute("sujets", metier.getSujetBySousCategorie(id));
				} else if (idPage == 4)// add reponse
				{
					model.addAttribute("reponses", metier.getReponseBySujet(id));
				}
				return "accueil";
			} else {
				session.setAttribute("post", new Post(post));
				model.addAttribute("identifiant", new Identifiant());
				return "login";
			}
		} else {
			// <---------------------------------------------------la liste � afficher
			if (idPage == 1)// add cat�gorie
			{
				model.addAttribute("categories", metier.getAllCategories());
			} else if (idPage == 2)// add sous cat�gorie
			{
				model.addAttribute("sousCategories", metier.getSousCategoriesByCategorie(id));
			} else if (idPage == 3)// add sujet
			{
				model.addAttribute("sujets", metier.getSujetBySousCategorie(id));
			} else if (idPage == 4)// add reponse
			{
				model.addAttribute("reponses", metier.getReponseBySujet(id));
			}
			Message bean = new Message("Page � jour: " + new Date());
			model.addAttribute("erreurBean", bean);
			model.addAttribute("post", new Post(post));
			return "accueil";
		}
	}
	
	private void addPost(Model model, Auteur a, Post post) {
		int idPage = post.getIdPage();
		Long id=post.getId();
		// Auteur a = (Auteur) session.getAttribute("auteur");
		if (idPage == 1)// add cat�gorie
		{
			if (a.getRole() == AuteurRole.ADMIN || a.getRole() == AuteurRole.MODERATOR) {
				metier.addCategorie(new Categorie(post.getContenu(), true));
			} else {
				Message bean = new Message("Votre suggestion � bien �t� enregistr�e. Merci.");
				model.addAttribute("erreurBean", bean);
				metier.addCategorie(new Categorie(post.getContenu(), false));
			}
			model.addAttribute("categories", metier.getAllCategories());
		} else if (idPage == 2)// add sous cat�gorie
		{
			if (a.getRole() == AuteurRole.ADMIN || a.getRole() == AuteurRole.MODERATOR) {
				metier.addSousCategorie(
						new SousCategorie(post.getContenu(), metier.getCategorie(id), true));
			} else {
				Message bean = new Message("Votre suggestion � bien �t� enregistr�e. Merci.");
				model.addAttribute("erreurBean", bean);
				metier.addSousCategorie(
						new SousCategorie(post.getContenu(), metier.getCategorie(id), false));
			}
			model.addAttribute("sousCategories", metier.getSousCategoriesByCategorie(id));
		} else if (idPage == 3)// add sujet
		{
			if (a.getRole() == AuteurRole.ADMIN || a.getRole() == AuteurRole.MODERATOR) {
				metier.addSujet(new Sujet(post.getIntitule(), post.getContenu(), true,
						(post.getNotifCreateur() != null), a, metier.getSousCategorie(id)));
			} else {
				Message bean = new Message("Votre suggestion � bien �t� enregistr�e. Merci.");
				model.addAttribute("erreurBean", bean);
				metier.addSujet(new Sujet(post.getIntitule(), post.getContenu(), false,
						(post.getNotifCreateur() != null), a, metier.getSousCategorie(id)));
			}
			model.addAttribute("sujets", metier.getSujetBySousCategorie(id));
		} else if (idPage == 4)// add reponse
		{
			if (a.getRole() == AuteurRole.ADMIN || a.getRole() == AuteurRole.MODERATOR) {
				metier.addReponse(new Reponse(post.getContenu(), metier.getSujet(id), true,
						(post.getNotifCreateur() != null), a));
			} else {
				Message bean = new Message("Votre suggestion � bien �t� enregistr�e. Merci.");
				model.addAttribute("erreurBean", bean);
				metier.addReponse(new Reponse(post.getContenu(), metier.getSujet(id), false,
						(post.getNotifCreateur() != null), a));
			}
			model.addAttribute("reponses", metier.getReponseBySujet(id));
		}
	}

	@RequestMapping(value = "/categorie/{id}")
	public String categorie(@PathVariable(value = "id") Long id, Model model, HttpSession session) {
		Nav nav = (Nav) session.getAttribute("nav");
		Categorie c = metier.getCategorie(id);
		if (nav.getIdCat() == null || nav.getIdCat() != id) {
			nav.setCDescription(c.getDescription());
			nav.setIdCat(id);
			nav.setIdSCat(null);
			nav.setIdSuj(null);
		}
		Post post = new Post(2);
		post.setId(id);
		model.addAttribute("sousCategories", metier.getSousCategoriesByCategorie(id));
		model.addAttribute("post", post);
		return "accueil";

	}

	@RequestMapping(value = "/sousCategorie/{id}")
	public String sousCategorie(@PathVariable(value = "id") Long id, Model model, HttpSession session) {
		model.addAttribute("sujets", metier.getSujetBySousCategorie(id));
		Nav nav = (Nav) session.getAttribute("nav");
		SousCategorie sc = metier.getSousCategorie(id);
		if (nav.getIdSCat() == null || nav.getIdSCat() != id) {
			nav.setSCDescription(sc.getDescription());
			nav.setIdSCat(id);
			nav.setIdSuj(null);
		}
		Post post = new Post(3);
		post.setId(id);
		model.addAttribute("post", post);
		return "accueil";

	}

	@RequestMapping(value = "/sujet/{id}")
	public String reponse(@PathVariable(value = "id") Long id, Model model, HttpSession session) {
		Nav nav = (Nav) session.getAttribute("nav");
		Sujet s = metier.getSujet(id);
		nav.setSDescription(s.getIntitule());
		nav.setIdSuj(id);
		Post post = new Post(4);
		post.setId(id);
		model.addAttribute("reponses", metier.getReponseBySujet(id));
		model.addAttribute("post", post);
		return "accueil";

	}

	@RequestMapping(value = "/updateAuteur/{id}/{idPage}")
	protected String updateAuteur(@PathVariable(value = "id") Long id, @PathVariable(value = "idPage") int idPage,
			Model model, HttpSession session) {

		model.addAttribute("auteur", session.getAttribute("auteur"));
		Post post = new Post(idPage);
		post.setId(id);
		session.setAttribute("post", post);
		return "update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	protected String update(@Valid Auteur a, BindingResult bindingResult,
			@RequestParam(name = "Annuler", required = false) String annuler, Model model,
			MultipartFile file, HttpSession session) {
		Post post = (Post) session.getAttribute("post");
		int idPage = post.getIdPage();
		Long id = post.getId();
		if (annuler!=null) { 
			model.addAttribute("post", new Post(post));
			session.removeAttribute("post");
			return "accueil";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("auteur", a);
			return "update";
		}

		if (!file.isEmpty()) {
			try {
				a.setPhoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Auteur auteur = (Auteur) session.getAttribute("auteur");
		// mise � jour des attribut de l'auteur
		a.setRole(auteur.getRole());
		a.setId(auteur.getId());
		a = metier.updateAuteur(a);
		session.setAttribute("auteur", a);
		if (idPage == 1) {
			model.addAttribute("categories", metier.getAllCategories());
		} else if (idPage == 2) {
			model.addAttribute("sousCategories", metier.getSousCategoriesByCategorie(id));
		} else if (idPage == 3) {
			model.addAttribute("sujets", metier.getSujetBySousCategorie(id));
		} else {
			model.addAttribute("reponses", metier.getReponseBySujet(id));
		}
		model.addAttribute("post", new Post(post));
		session.removeAttribute("post");
		session.setAttribute("auteur", a);
		return "accueil";
	}
	
	@RequestMapping(value = "/deconnecter/{id}/{idPage}")
	public String se_deconnecter(@PathVariable(value = "id") Long id, @PathVariable(value = "idPage") int idPage,
			Model model, HttpSession session) {
		session.removeAttribute("auteur");
		// <-------- categories ou
		// souscategories , ...; use id and idpage ???
		if (idPage == 1) {
			model.addAttribute("categories", metier.getAllCategories());
		} else if (idPage == 2) {
			model.addAttribute("sousCategories", metier.getSousCategoriesByCategorie(id));
		} else if (idPage == 3) {
			model.addAttribute("sujets", metier.getSujetBySousCategorie(id));
		} else {
			model.addAttribute("reponses", metier.getReponseBySujet(id));
		}
		Post post = new Post();
		post.setIdPage(idPage);
		post.setId(id);
		model.addAttribute("post", post);
		return "accueil";
	}

	@RequestMapping(value = "/photoAuteur", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws IOException {
		Auteur a = metier.getAuteur(id);
		if (a.getPhoto() == null)
			return new byte[0];
		else
			return IOUtils.toByteArray(new ByteArrayInputStream(a.getPhoto()));
	}
	
	@RequestMapping(value = "/validation/{id}/{idPage}")
	public String validerPost(@PathVariable(value = "id") Long id, @PathVariable(value = "idPage") int idPage,
			Model model, HttpSession session) {

		Auteur a = (Auteur) session.getAttribute("auteur");
		if (a == null || a.getRole() == AuteurRole.USER) {
			model.addAttribute("identifiant", new Identifiant());
			model.addAttribute("post", new Post(1));
			return "accueil";
		}
		model.addAttribute("categories", metier.getCategoriesToValidate());
		model.addAttribute("sousCategories", metier.getSousCategoriesToValidate());
		model.addAttribute("sujets", metier.getSujetsToValidate());
		model.addAttribute("reponses", metier.getReponsesToValidate());
		model.addAttribute("validationModel", new Validation());
		Post post=new Post(idPage);
		post.setId(id);
		model.addAttribute("post", post);
		return "validerPost";

	}

	@RequestMapping(value = "/validerPost/{id}/{idPage}")
	public String validerPost2(@PathVariable(value = "id") Long id, @PathVariable(value = "idPage") int idPage,
			Validation validationModel, BindingResult bindingResult, Model model, HttpSession session) {
		Auteur a = (Auteur) session.getAttribute("auteur");
		if (a == null || a.getRole() == AuteurRole.USER) {
			model.addAttribute("identifiant", new Identifiant());
			model.addAttribute("post", new Post(1));
			return "accueil";
		}
		if(validationModel.getCategories()!=null)
		for (String item : validationModel.getCategories()) {
			metier.validerCategorie(Long.parseLong(item));
		}
		if(validationModel.getSousCategories()!=null)
		for (String item : validationModel.getSousCategories()) {
			metier.validerSousCategorie(Long.parseLong(item));
		}
		if(validationModel.getSujets()!=null)
		for (String item : validationModel.getSujets()) {
			metier.validerSujet(Long.parseLong(item));
		}
		if(validationModel.getReponses()!=null)
		for (String item : validationModel.getReponses()) {
			metier.validerReponse(Long.parseLong(item));
		}

		//model.addAttribute("identifiant", new Identifiant());
		// <------------------------- categories ou souscategories ???
		if (idPage == 1) {
			model.addAttribute("categories", metier.getAllCategories());
		} else if (idPage == 2) {
			model.addAttribute("sousCategories", metier.getSousCategoriesByCategorie(id));
		} else if (idPage == 3) {
			model.addAttribute("sujets", metier.getSujetBySousCategorie(id));
		} else {
			model.addAttribute("reponses", metier.getReponseBySujet(id));
		}
		Post post = new Post();
		post.setIdPage(idPage);
		post.setId(id);
		model.addAttribute("post", post);

		return "accueil";
	}
	@RequestMapping(value = "/abonnés/{id}/{idPage}")
	protected String abonnés(@PathVariable(value = "id") Long id, @PathVariable(value = "idPage") int idPage,
			Model model, HttpSession session) {	
		return "abonnés";
	}
	@RequestMapping(value = "/abonnements/{id}/{idPage}")
	protected String abonnements(@PathVariable(value = "id") Long id, @PathVariable(value = "idPage") int idPage,
			Model model, HttpSession session) {
		return "abonnements";
	}
	@RequestMapping(value = "/notification/{id}/{idPage}")
	protected String notification(@PathVariable(value = "id") Long id, @PathVariable(value = "idPage") int idPage,
			Model model, HttpSession session) {
		Auteur a = (Auteur) session.getAttribute("auteur");
		for(Auteur f:a.getFollowings()) {
			model.addAttribute("reponse", metier.getReponseByAuteur(f.getId()));
			model.addAttribute("followings",f);
		}
		return "notification";
	}
}
