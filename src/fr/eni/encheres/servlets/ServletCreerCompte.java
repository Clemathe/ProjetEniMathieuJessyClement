package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletCreerCompte
 */
@WebServlet("/CreerCompte")
public class ServletCreerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doG servletCreerCompte");
		
		request.setAttribute("pseudo", "pseudo");
		request.setAttribute("prenom", "prenom");
		request.setAttribute("nom", "nom");
		request.setAttribute("email", "email");
		request.setAttribute("telephone", "telephone");
		request.setAttribute("rue", "rue");
		request.setAttribute("codePostal", "codePostal");
		request.setAttribute("ville", "ville");
		request.setAttribute("motDePasse1", "motDePasse1");
		request.setAttribute("motDePasse", "motDePasse");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageCreerCompte.jsp"); 
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost servletCreerCompte");
		//Lecture des parametres 
		
		request.setCharacterEncoding("UTF-8");
		
		// int noUtilisateur = 0; 
		 String pseudo = null; 
		 //String nom = null; 
		 //String prenom = null; 
		// String email = null; 
		 //String telephone = null; 
		 //String rue = null; 
		 //String codePostal = null; 
		 //String ville = null; 
		 //String motDePasse = null; 
		 //int credit = 0; 
		 List<String> MessageErreur=new ArrayList<>();
		 Utilisateur utilisateur = new Utilisateur(); 
		 System.out.println("1");
		 // lecture et vérification pseudo
		 
		 try {	
			 System.out.println("2");
			pseudo = request.getParameter(pseudo); 
			if (pseudo != null && pseudo.matches("\\p{Alnum}") ) {
				utilisateur.setPseudo(pseudo);
				System.out.println("pseudo entré : " + pseudo);			
			} else MessageErreur.add("erreur de saisie du pseudonyme : null ou caratères interdit"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 System.out.println("fin"); 
		
	}
	
	private void creerCompte(HttpServletRequest request, HttpServletResponse response) {
		// lecture et vérification des paramètres et  
		
		
	}

}
