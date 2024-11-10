package com.bruno.training.web.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bruno.org.dao.DataException;
import com.bruno.org.model.EmpleadoDTO;
import com.bruno.org.service.EmpleadoService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.EmpleadoServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.CookieManager;
import com.bruno.training.web.util.ErrorCodes;
import com.bruno.training.web.util.Errors;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.SessionManager;
import com.bruno.training.web.util.Views;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger(UsuarioServlet.class);
	private EmpleadoService empleadoService = null;

	public UsuarioServlet() {
		super();
		empleadoService = new EmpleadoServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Errors errors = new Errors();
		request.setAttribute("errors"/* AttibuteNames.ERRORS */, errors);

		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = false;

		if (Actions.LOGIN.equalsIgnoreCase(action)) {

			try {
				String empleadoIdStr = request.getParameter(Parameters.ID);
				String password = request.getParameter(Parameters.CONTRASENA);
				Long empleadoId = Long.valueOf(empleadoIdStr); // id empleado em long

				if (!errors.hasErrors()) {

					EmpleadoDTO empleado = empleadoService.autenticar(empleadoId, password);
					logger.info("Usuario: " + empleadoId + " " + empleado.getNombre());

					if (empleado != null) {
						SessionManager.setAttribute(request, Attributes.EMPLEADO, empleado);
						String rememberMeStr = request.getParameter("remember-user");
						Boolean rememberMe = rememberMeStr != null;
						Long id = Long.valueOf(request.getParameter(Parameters.ID));
						String idStr = String.valueOf(empleado.getId());
						if (rememberMe) {
							CookieManager.setCookie(response, request.getContextPath(), "empleado", idStr,
									30 * 24 * 60 * 60);
						} else {
							CookieManager.removeCookie(response, request.getContextPath(), "empleado");
						}
						targetView = Views.HOME;
						forwardOrRedirect = false;
					} else {
						logger.warn("Authentication failed = {} ", empleado);
						errors.addGlobal(ErrorCodes.AUTHENTICATION_FAILED);
						forwardOrRedirect = true;
						targetView = Views.LOGIN;
					}
				} else {
					forwardOrRedirect = false;
					targetView = Views.LOGIN;
				}

			} catch (DataException | ServiceException e) {
				logger.error(e.getMessage(), e);
			}
		} else if ("change-locale".equalsIgnoreCase(action)) {
			String[] newLocaleStr = request.getParameter("locale").split("_");

			// El locale que nos piden está soportado????

			// Si lo está, y si no el de por defecto
			Locale newLocale = new Locale("en");// ConfigurationParametrs.... dfeult;
			if (newLocaleStr.length == 1) {
				newLocale = new Locale(newLocaleStr[0]);
			} else if (newLocaleStr.length == 2) {
				newLocale = new Locale(newLocaleStr[0], newLocaleStr[1]);
			}
			SessionManager.setAttribute(request, "locale", newLocale);
			// Se guarda en una cookie
			// CookieManager. setCookie...
			targetView = Views.HOME;
			forwardOrRedirect = false;
		}
		RouterUtils.route(request, response, forwardOrRedirect, targetView);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
