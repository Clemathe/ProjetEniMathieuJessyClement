package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
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
		System.out.println("doget noArtcle :"+ noArticle);
		try {
			ArticleVendu article= AvManager.getArticleVendu(noArticle);
			System.out.println("serveletEnchere : " + article);
			
			request.setAttribute("article", article);
			request.setAttribute("noArticle", noArticle);
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
	
		UtilisateurManager uManager = new UtilisateurManager();
		EnchereManager eManager = new EnchereManager();
		
		boolean sessionUtilisateur = request.getSession().getAttribute("utilisateurCourant") != null;
		boolean encherir = request.getParameter("encherir") != null;
		
		if(sessionUtilisateur) {
			if(encherir) {
			try {
				
				int montant = Integer.parseInt(request.getParameter("montant"));
				int noArticle = Integer.parseInt(request.getParameter("noArticle"));
				
				System.out.println("servlet enchere noArticle"+ noArticle);
				
				Utilisateur utilisateurCourant = (Utilisateur) request.getSession().getAttribute("utilisateurCourant");
				String enchereMessage = eManager.Encherir(montant, utilisateurCourant, noArticle );
				request.setAttribute("enchereMessage", enchereMessage);
				
				//Rechargement de la session utilisateur pour mettre a jour le solde
//				if ( enchereMessage.equals("Enchère enregistrée")) {
//					utilisateurCourant = uManager.getUtilisateurPourSession(utilisateurCourant.getPseudo(), utilisateurCourant.getMotDePasse());
//					request.getSession().setAttribute("utilisateurCourant", utilisateurCourant);
//					request.changeSessionId();
//				}
				doGet(request, response);
			}catch (Exception e) {
				
				doGet(request, response);			
			}
			
			}else {
				response.sendRedirect(request.getContextPath()+"/encheres");
			}

		}else {
		response.sendRedirect(request.getContextPath()+"/connexion");
		}
	}
}