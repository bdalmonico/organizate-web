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
	
			if ("search".equalsIgnoreCase(action)) {
				EmpleadoCriteria criteria = new UsuarioCriteria();
				
				String email = request.getParameter("email");
				if(email == null || email.isEmpty()){
					criteria.setEmail(null);
				} else {
					criteria.setEmail(email);
				}

				String idStr = request.getParameter("id");
				if(idStr == null || idStr.isEmpty()){
					criteria.setId(null);
				} else {
					Long id = Long.valueOf(idStr);
					criteria.setId(id);
				}

				String nombre = request.getParameter("nombre");
				if(nombre == null || nombre.isEmpty()){
					criteria.setNombre(null);
				} else {
					criteria.setNombre(nombre);
				}

				String rol = request.getParameter("rol");
				if(rol==null||rol.isEmpty()){
					criteria.setRol(null);
				} else {
					criteria.setRol(rol);
				}

				try {
					Results<EmpleadoDTO> resultados = empleadoService.findByCriteria(criteir, 1,10);
					request.setAttribute("resultados", resultados);
					targetView = Views.EMPLEADO_SEARCH;
					forwardOrRedirect = false;
				} catch (DataException | ServiceException e) {
				logger.error(e.getMessage(), e);
			} else if ("detail".equalsIgnoreCase(action)) {
				try{
					String idStr = request.getParameter("id");
					Long id = Long.valueOf(idStr);
					EmpleadoDTO empleado = empleadoService.findById(id);
					request.setAttribute("empleado", empleado);
					targetView = Views.EMPLEADO_DETAIL;
					forwardOrRedirect = false;
				} catch (DataException | ServiceException e) {
				logger.error(e.getMessage(), e);
		    	}

			} else if (Actions.LOGOUT.equalsIgnoreCase(action)) {
				SessionManager.removeAttribute(request, "empleado");
				targetView = Views.HOME;
				forwardOrRedirect = false;	
			} else if ("anadir".equalsIgnoreCase(action)) {
				try {
					EmpleadoDTO empleado = new EmpleadoDTO();
					String nombre = request.getParameter("nombre");
					String email = request.getParameter("email");
					String rol = request.getParameter("rol");
					String password = request.getParameter("password");

					empleado.setNombre(nombre);
					empleado.setEmail(email);
					empleado.setRol(rol);
					empleado.setPassword(password);
					Long id = empleadoService.insert(empleado);
					targetView = Views.EMPLEADO_INSERT;
					forwardOrRedirect = false;


				} catch (DataException | ServiceException e) {
				logger.error(e.getMessage(), e);
			    }

			}


			}			
			RouterUtils.route(request, response, forwardOrRedirect, targetView);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
