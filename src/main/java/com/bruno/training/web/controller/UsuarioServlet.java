package com.bruno.training.web.controller;

import java.io.IOException;

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
		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = true;

		logger.info("action" + action);

		if (Actions.LOGIN.equalsIgnoreCase(action)) {

			String empleadoIdStr = request.getParameter(Parameters.ID);
			String password = request.getParameter(Parameters.PASSWORD);

			Long empleadoId = Long.valueOf(empleadoIdStr); // id empleado em long

			try {
				EmpleadoDTO empleado = empleadoService.autenticar(empleadoId, password);

				if (empleado != null) {
					SessionManager.setAttribute(request, Attributes.EMPLEADO, empleado);
					String rememberMeStr = request.getParameter("remember-user");
					Boolean rememberMe = rememberMeStr != null;
					if (rememberMe) {
						CookieManager.setCookie(response, request.getContextPath(), "user", empleado.getEmail(),
								30 * 24 * 60 * 60);
					} else {
						CookieManager.removeCookie(response, request.getContextPath(), "user");
					}
					targetView = Views.HOME;
					forwardOrRedirect = false;
				}

			} catch (DataException | ServiceException e) {
				logger.error(e.getMessage(), e);
			}
		}
		RouterUtils.route(request, response, forwardOrRedirect, targetView);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
