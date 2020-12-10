package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bo.ArticleVendu;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageAccueilNonConnecte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVenduManager enchereEnCours = new ArticleVenduManager();
		String categorie =(String)request.getParameter("categories");
		String nomArticlePartiel = (String)request.getParameter("nomArticlePartiel");
		System.out.println(categorie.toString()+" "+ nomArticlePartiel.toString());
		
		request.setAttribute("enchereEnCours", enchereEnCours.getEnchereEnCours(categorie, nomArticlePartiel));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageAccueilNonConnecte.jsp");
		rd.forward(request, response);
		
		
		
		
	}

}
