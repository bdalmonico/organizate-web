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
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.SessionManager;
import com.bruno.training.web.util.Views;

@WebServlet("/private/UsuarioServlet")
public class UsuarioServletPrivate extends HttpServlet {

	private static Logger logger = LogManager.getLogger(UsuarioServletPrivate.class);

	private EmpleadoService empleadoService = null;

	public UsuarioServletPrivate() {
		super();
		empleadoService = new EmpleadoServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String action = request.getParameter(Parameters.ACTION);
			String targetView = null;
			boolean forwardOrRedirect = true;
	
			 if (Actions.LOGOUT.equalsIgnoreCase(action)) {
				SessionManager.removeAttribute(request, "empleado");
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
