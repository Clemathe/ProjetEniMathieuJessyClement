package fr.eni.encheres.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltreConnexion
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD},
urlPatterns = {"/ServletAccueil/a"})
public class FiltreConnexion implements Filter {

	
    /**
     * Default constructor. 
     */
    public FiltreConnexion() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		System.out.println(httpRequest.getSession().getAttribute("utilisateurCourant")== null);
		
		if ((httpRequest.getSession().getAttribute("utilisateurCourant")== null)){
			
			RequestDispatcher rd = httpRequest.getRequestDispatcher("/ServletAccueil");
			rd.forward(httpRequest, httpResponse);
//			httpResponse.sendRedirect(httpRequest.getContextPath()+"/ServletAccueil");
			
		}else if(httpRequest.getSession().getAttribute("utilisateurCourant") != null) {
			
			chain.doFilter(request, response);
			
		}else {
			
			httpResponse.sendRedirect("/PageAccueil");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
