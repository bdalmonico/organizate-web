package com.bruno.training.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Filtro de autenticación.
 */
// Configurado en el web.xml para controlar el orden de ejecución de los filtros.
// @WebFilter("/*")
public class LogFilter extends HttpFilter implements Filter {
	private static Logger logger = LogManager.getLogger(LogFilter.class);
    
    public LogFilter() {
        super();
    }

	public void init(FilterConfig fConfig) throws ServletException {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		StringBuffer url = new StringBuffer();	
		url.append(httpRequest.getRequestURL());
		logger.info("--> Request "+url+" from "+httpRequest.getRemoteHost());
		
		
		Map<String, String[]> parameters = httpRequest.getParameterMap();
		chain.doFilter(request, response);
		
		logger.info("Request "+url+" from "+httpRequest.getRemoteAddr()+" --> ");
	}
	
	
    public void destroy() {
	}



}