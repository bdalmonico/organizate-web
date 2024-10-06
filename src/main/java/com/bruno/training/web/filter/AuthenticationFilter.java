package com.bruno.training.web.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.SessionManager;
import com.bruno.training.web.util.Views;

// Al tener varios filtros, la anotacion no garantiza el orden.
// @WebFilter("/*")
public class AuthenticationFilter extends HttpFilter implements Filter {
    public AuthenticationFilter() {
        super();
    }
    
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	
		if (SessionManager.getAttribute(httpRequest, "empleado")==null) {
			// Usuario no autenticado
			RouterUtils.route(httpRequest, (HttpServletResponse) response, true, Views.LOGIN);
		} else {			
			// Usuario autenticado, continua la ejecución
			chain.doFilter(request, response);
		}
		
	}

	
	public void destroy() {
	}


}
