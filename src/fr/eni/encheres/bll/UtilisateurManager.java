package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private static UtilisateurDAO utilisateurDAO;

	/**
	 * @param daoRepas
	 */
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	

	public Utilisateur getUtilisateurPourSession(String login, String password) {
		
		String hashPassword = MD5Utils.digest(password);
		
		try {
			return utilisateurDAO.getUserforSession(login, hashPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> verificationSaisieUtilisateur(String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, String motDePasse, String motDePasse1) {
		List<String> MessageErreur = new ArrayList<>();
		if (pseudo == null || !pseudo.matches("[a-zA-Z0-9]+")) {
			MessageErreur.add("erreur de saisie du pseudonyme : null ou caractères interdit");

		} else if (nom == null || !nom.matches("^[a-zA-Z]*$")) {
			MessageErreur.add("erreur de saisie du Nom : null ou caractère interdit");

		} else if (prenom == null || !prenom.matches("^[a-zA-Z]*$")) {
			MessageErreur.add("erreur de saisie du Prenom : null ou caractère interdit");

		} else if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
			MessageErreur.add("erreur de saisie de l'email : null ou format interdit");

		} else if (telephone == null || !telephone.matches("(0|(\\+33)|(0033))[1-9][0-9]{8}")) {
			MessageErreur.add("erreur de saisie du numéro de téléphone : null ou format interdit");
			
		} else if (rue == null) {
			MessageErreur.add("erreur de saisie de la rue : null ");

		} else if (ville == null || !ville.matches("^[a-zA-Z]*$")) {
			MessageErreur.add("erreur de saisie de la ville : null ou caractère interdit");
		
		} else if (codePostal == null || !codePostal.matches("^[0-9]{5}$")) {
			MessageErreur.add("erreur de saisie du code postal : null ou caractère interdit");

		} else if (!motDePasse.equals(motDePasse1)) {
			MessageErreur.add("erreur de confirmation du mot de passe : veuillez entrer de nouveau votre mot de passe");
	
		} else if (motDePasse1 == null
				|| !motDePasse1.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,12})$")) {
			MessageErreur.add(
					"le mot de passe doit contenir entre 8 et 12 caractères, au moins une lettre (minuscule ou majuscule), au moins un chiffre, au moins un caractère spécial");

		}
		return MessageErreur;

	}

	public boolean validationCreationCompte(List<String> messageErreur) {
		Boolean validationOk = false;
		if (messageErreur.isEmpty()) {
			validationOk = true;
		}
		return validationOk;
	}
	

	public boolean creerUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) throws SQLException {
		String hashPassword = MD5Utils.digest(motDePasse);
		Boolean insertOK = false;
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setVille(ville);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setMotDePasse(hashPassword);
		try {
			utilisateurDAO.insertUtilisateur(utilisateur);
			insertOK = true;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return insertOK;
	}

	public void insertUtilisateur(Utilisateur utilisateur) throws Exception {

		utilisateurDAO.insertUtilisateur(utilisateur);

	}
	public String modifierUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) throws Exception {
		String statutUpdate = "validation de la modification";
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setVille(ville);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setMotDePasse(motDePasse);
		try {
			utilisateurDAO.update(utilisateur);
		} catch (Exception e) {
			e.printStackTrace();
			statutUpdate = "La modification de l'utilisateur n'a pas pu etre exécutée en base de données"; 
		}
				return statutUpdate;
		
	}
	public void updateUtilisateur (Utilisateur utilisateur) throws SQLException {
		utilisateurDAO.update(utilisateur);
	}
	

	public void rembourserUtilisateur(int enchereLaPlusHaute, Utilisateur user) {
		utilisateurDAO.rembourserUtilisateur(enchereLaPlusHaute, user);
	}

	public void debiterUtilisateur(int montantEnchere, Utilisateur utilisateurCourant) {
		utilisateurDAO.debiterUtilisateur(montantEnchere, utilisateurCourant);
		
	}

}
