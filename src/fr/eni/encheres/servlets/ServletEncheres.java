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
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEncheres
 */
@WebServlet(urlPatterns = {"/ServletEncheres", "/encheres"})
public class ServletEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ArticleVenduManager AvManager = new ArticleVenduManager();
		int noArticle = Integer.parseInt(request.getParameter("noArticle")) ;
		try {
			ArticleVendu article= AvManager.getArticleVendu(noArticle);
			System.out.println("serveletEnchere : " + article);
			
			request.setAttribute("article", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageEncherir.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean encherir = request.getParameter("encherir") != null;
		System.out.println(encherir);
		System.out.println(request.getParameter("noArticle"));
		ArticleVenduManager aVManager = new ArticleVenduManager();
		
		if(encherir) {
			int montant = Integer.parseInt(request.getParameter("montant"));
			int noArticle = Integer.parseInt(request.getParameter("noArticle"));
			Utilisateur utilisateurCourant = (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
			String enchereMessage = aVManager.Encherir(montant, utilisateurCourant, noArticle );
			request.setAttribute("enchereMessage", enchereMessage);
			System.out.println(enchereMessage);
			doGet(request, response);
		}
		
	}

}
