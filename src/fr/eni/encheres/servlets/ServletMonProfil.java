package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletMonProfil
 */
@WebServlet("/MonProfil")
public class ServletMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//
		Utilisateur utilisateur = new Utilisateur(); 
		utilisateur = (Utilisateur) request.getSession(true).getAttribute("utilisateurCourant");
		
		if(utilisateur == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/connexion"); 
			rd.forward(request, response);
			System.out.println("renvoyer vers page se connecter");
		} else
			
		request.setAttribute("utilisateur", utilisateur);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageMonProfil.jsp"); 
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DoPost Servlet MonProfil");
		doGet(request, response);
	}

}
