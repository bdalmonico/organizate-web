//package com.bruno.training.web.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//
//import config.ConfigurationParametersManager;
//import com.bruno.training.web.util.SessionManager;
//
////@WebFilter("/*")
//public class LanguageFilter extends HttpFilter implements Filter {
//private static String[] SUPPORTED_LOCALES = ConfigurationParametersManager.getParameterValue("locale.support").split(",");
//	
//	
//    public LanguageFilter() {
//        super();
//    }
//    
//	public void init(FilterConfig fConfig) throws ServletException {
//	}
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//
//
//		// El locale en el que se renderiza se decidirá de acuerdo al siguiente orden:
//		// 1) Tiene cookie?
//		// Idiomas de usuario
//	
//		// 2) HEADER
//		String[] languages = httpRequest.getHeader("Accept-Language").split(",");
//	
//		// Encaje entre lo que pide el usuario y lo que soporta la web.
//		// 3) ELSE: Si no hay cookie, ni header, O BIEN EL TIENE EN LA COOKIE O HEADER NO ESTA ESTA SOPORTADO = > DEFECTO
//		
//		//SessionManager.setAttribute(httpRequest, "locale", new Locale(language));
//	}
//
//	
//	public void destroy() {
//	}
//
//}
//CODIGO ANTIGO ACIMA
package com.bruno.training.web.filter;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import config.ConfigurationParametersManager;
import com.bruno.training.web.util.SessionManager;
import com.bruno.training.web.util.CookieManager;


public class LanguageFilter extends HttpFilter implements Filter {
    private static String[] SUPPORTED_LOCALES = ConfigurationParametersManager.getParameterValue("locale.support").split(",");
    private static String DEFAULT_LOCALE = ConfigurationParametersManager.getParameterValue("locale.default");

    public LanguageFilter() {
        super();
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Tentar obter o idioma do cookie
        String localeFromCookie = CookieManager.getValue(httpRequest, "locale");
        Locale locale = null;
        if (localeFromCookie != null) {
            locale = Locale.forLanguageTag(localeFromCookie);
        }

        // Tentar obter o idioma da sessão se não estiver no cookie
        if (locale == null) {
            locale = (Locale) SessionManager.getAttribute(httpRequest, "locale");
        }

        if (locale == null) {
            // Tentar obter o idioma do cabeçalho
            String[] languages = httpRequest.getHeader("Accept-Language").split(",");
            if (languages.length > 0) {
                locale = Locale.forLanguageTag(languages[0]);
            }

            // Verificar se o idioma é suportado
            boolean supported = false;
            for (String supportedLocale : SUPPORTED_LOCALES) {
                if (locale.toString().equals(supportedLocale)) {
                    supported = true;
                    break;
                }
            }

            // Se não for suportado, usar o idioma padrão
            if (!supported) {
                locale = Locale.forLanguageTag(DEFAULT_LOCALE);
            }

            // Armazenar o idioma na sessão
            SessionManager.setAttribute(httpRequest, "locale", locale);
        }

        // Continuar a cadeia de filtros
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
