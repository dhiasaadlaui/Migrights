package com.ftdes.migrights.filters;

import com.ftdes.migrights.managedBean.LoginController;
import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebFilter(filterName = "loginCheckFilter", urlPatterns = { "/faces/article/*","/faces/message/*",
    "/faces/faq/*","/faces/contenumedia/*","/faces/immigrant/*","/faces/localistation/*",
    "/faces/statistiques/*","/faces/supportsensibilisation/*","/faces/reclamation/*" })
public class LoginFilter implements Filter {
        
    @Inject
    private  LoginController loginctrl;
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
                
		
		String loginURL = request.getContextPath() + "/faces/Login.xhtml";

		boolean loggedIn = loginctrl != null && loginctrl.getIsLoged();
		boolean loginRequest = request.getRequestURI().equals(loginURL);
		boolean resourceRequest = request.getRequestURI()
				.startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);
                
System.out.println("loginctrl msg" + loginctrl.getEmail());
		if (loggedIn || loginRequest || resourceRequest || request.getRequestURI().matches(".*(css|jpg|png|gif|js)")) {
			

			chain.doFilter(request, response);
		} else {
			response.sendRedirect(loginURL);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}