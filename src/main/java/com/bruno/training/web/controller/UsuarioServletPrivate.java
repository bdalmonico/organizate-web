package com.bruno.training.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import com.bruno.org.dao.DataException;
import com.bruno.org.model.EmpleadoCriteria;
import com.bruno.org.model.EmpleadoDTO;
import com.bruno.org.model.Results;
import com.bruno.org.service.EmpleadoService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.EmpleadoServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.ErrorCodes;
import com.bruno.training.web.util.Errors;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.SessionManager;
import com.bruno.training.web.util.Views;

@WebServlet("/private/UsuarioServlet")
public class UsuarioServletPrivate extends HttpServlet {

	private static SimpleDateFormat FECHA_OF = new SimpleDateFormat("yyyy-MM-dd");

	private static Logger logger = LogManager.getLogger(UsuarioServletPrivate.class);

	private EmpleadoService empleadoService = null;

	public UsuarioServletPrivate() {
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

		if (Actions.SEARCH.equalsIgnoreCase(action)) {
			EmpleadoCriteria criteria = new EmpleadoCriteria();

			String idStr = request.getParameter(Parameters.ID);
			Long id = (idStr == null || idStr.isEmpty() ? null : Long.valueOf(idStr));
			criteria.setId(id);

			String nombre = request.getParameter(Parameters.NOMBRE);
			criteria.setNombre((nombre == null || nombre.isEmpty()) ? null : nombre);

			String rol = request.getParameter(Parameters.ROLID);
			Integer rolId = (rol == null || rol.isEmpty()) ? null : Integer.valueOf(rol);
			criteria.setRolId(rolId);

			String email = request.getParameter(Parameters.EMAIL);
			criteria.setEmail((email == null || email.isEmpty()) ? null : email);

			try {
				Results<EmpleadoDTO> resultados = empleadoService.findByCriteria(criteria, 1, 10);
				if (resultados.getPage().size() > 0) {
					request.setAttribute(Attributes.RESULTADOS, resultados.getPage());
				}
				targetView = Views.EMPLEADO_SEARCH;
				forwardOrRedirect = true;
			} catch (DataException | ServiceException e) {
				logger.error(e.getMessage(), e);
			}
		} else if (Actions.DETAIL.equalsIgnoreCase(action)) {
			try {

				String idStr = request.getParameter(Parameters.ID);
				Long id = Long.valueOf(idStr);
				EmpleadoDTO empleado = empleadoService.findById(id);
				request.setAttribute(Attributes.EMPLEADO, empleado);
				targetView = Views.EMPLEADO_DETAIL;
				forwardOrRedirect = true;
			} catch (DataException | ServiceException e) {
				logger.error(e.getMessage(), e);
			}

		} else if (Actions.LOGOUT.equalsIgnoreCase(action)) {
			SessionManager.removeAttribute(request, Attributes.EMPLEADO);
			targetView = Views.HOME;
			forwardOrRedirect = false;
		} else if (Actions.REGISTRAR.equalsIgnoreCase(action)) {

			EmpleadoDTO empleado = new EmpleadoDTO();
			String nombre = request.getParameter(Parameters.NOMBRE);
			String apellido = request.getParameter(Parameters.APELLIDO);
			String email = request.getParameter(Parameters.EMAIL);
			errors.addFieldError("correo", ErrorCodes.INVALID_EMAIL);

			String contrasena = request.getParameter(Parameters.CONTRASENA);
			if (!Strings.isBlank(contrasena)) {
				contrasena = contrasena.trim();
				if (contrasena.length() >= 6 && contrasena.length() <= 12) {
					// Expresion regular: Para MAY/min/numers...
				} else {
					errors.addFieldError("contrasena", ErrorCodes.INVALID_PASSWORD_LENGTH);
				}
			} else {
				errors.addFieldError("contrasena", ErrorCodes.MANDATORY_FIELD);
			}
			String fechaAltaStr = request.getParameter(Parameters.FECHAALTA);

			String rol = request.getParameter(Parameters.ROLID);
			Integer rolId = Integer.valueOf(rol);

			Date fechaAlta = null;
			try {
				fechaAlta = FECHA_OF.parse(fechaAltaStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!errors.hasErrors()) {
				empleado.setNombre(nombre);
				empleado.setApellido(apellido);
				empleado.setEmail(email);
				empleado.setContrasena(contrasena);
				empleado.setRolId(rolId);
				empleado.setFechaAlta(fechaAlta);

				try {
					empleadoService.registrar(empleado);
				} catch (DataException e) {
					e.printStackTrace();
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				targetView = Views.EMPLEADO_INSERT;
				forwardOrRedirect = true;
			} else {
				forwardOrRedirect = true;
				targetView = Views.EMPLEADO_INSERT;
			}

		} else if ("viewProfile".equalsIgnoreCase(action)) {
			try {
				String idStr = request.getParameter("id");
				Long id = Long.valueOf(idStr);

				EmpleadoDTO u = empleadoService.findById(id);

				request.setAttribute("u", u);

				targetView = Views.EMPLEADO_DETAIL;
				forwardOrRedirect = true;
			} catch (DataException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}
		} else if (Actions.DELETE.equalsIgnoreCase(action)) {

			try {
				String idStr = request.getParameter("id");
				Long id = Long.valueOf(idStr);
				empleadoService.delete(id);
				targetView = Views.EMPLEADO_DELETE;
				forwardOrRedirect = true;

			} catch (DataException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}
		} else if (Actions.UPDATE.equalsIgnoreCase(action)) {
			try {
				EmpleadoDTO empleado = new EmpleadoDTO();
				String idStr = request.getParameter(Parameters.ID); 
				String nombre = request.getParameter(Parameters.NOMBRE);
				String apellido = request.getParameter(Parameters.APELLIDO);
				String email = request.getParameter(Parameters.EMAIL);
				String fechaAltaStr = request.getParameter(Parameters.FECHAALTA);
				String rolIdStr = request.getParameter(Parameters.ROLID);
				
				Long id = null;
				if (idStr != null && !idStr.isEmpty()) {
					id = Long.valueOf(idStr);
				} else {
					logger.warn("Estado ID não fornecido.");
					// Trate o caso onde estadoId é necessário, mas não foi fornecido
				}
				
				Integer rolId = null;
				if (rolIdStr != null && !rolIdStr.isEmpty()) {
					rolId = Integer.valueOf(rolIdStr);
				} else {
					logger.warn("Estado ID não fornecido.");
					// Trate o caso onde estadoId é necessário, mas não foi fornecido
				}

				Date fechaAlta= null;
				
				try {
					fechaAlta = FECHA_OF.parse(fechaAltaStr);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				empleado.setId(id);
				empleado.setNombre(nombre);
				empleado.setApellido(targetView);
				empleado.setEmail(targetView);
				empleado.setFechaAlta(fechaAlta);
				empleado.setRolId(rolId);
				empleadoService.update(empleado);

				targetView = Views.TAREA_UPDATE;
				forwardOrRedirect = false;

			} catch (DataException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}
		}

		RouterUtils.route(request, response, forwardOrRedirect, targetView);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
