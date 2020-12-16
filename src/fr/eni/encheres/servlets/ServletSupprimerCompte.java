package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.MD5Utils;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletSupprimerCompte
 */
@WebServlet("/SupprimerCompte")
public class ServletSupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSupprimerCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateurSession = new Utilisateur();
		utilisateurSession = (Utilisateur) request.getSession(true).getAttribute("utilisateurCourant");
		String prenom = (String)utilisateurSession.getPrenom();
		String attention = "Attention"; 
		String suite = "vous êtes sur le point de supprimer votre compte utilisateur, cette action est irréversible"; 
		request.setAttribute("prenom",prenom);
		request.setAttribute("attention", attention);
		request.setAttribute("suite", suite);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageProfil.jsp");
		rd.forward(request, response);
		
		/*else (supprimer != null && !supprimer.isEmpty()) {
		
		 String no_utilsateur = request.getParameter("no_utilisateur");
	 * if(supprimer){ /* afficher message êtes vous sûr de vouloir supprimer votre
	 * profil ? String alerteSupression =
	 * " êtes vous sûr de vouloir supprimer votre profil ? "
	 * request.setAttribute("messageUtilisateur", alerteSupression);;
		response.getWriter().append("Served at: ").append(request.getContextPath())
		 * RequestDispatcher rd =
		 * request.getRequestDispatcher("WEB-INF/PageModifierProfil.jsp");
		 * rd.forward(request, response);
		 * String hashPassword2 = MD5Utils.digest(motDePasseSession);
		 */;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// if hidden vide 
	
		
		// confirmation = suppression
		
		Utilisateur utilisateurSession = new Utilisateur();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		utilisateurSession = (Utilisateur) request.getSession(true).getAttribute("utilisateurCourant");
		int no_utilisateur = (int)utilisateurSession.getNoUtilisateur();
		try {
			utilisateurManager.supprimer(no_utilisateur);
		} catch (SQLException e) {
			// message erreur à renvoyer utilisateur
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageAccueilNonConnecte.jsp");
		rd.forward(request, response);
			
	}

}
