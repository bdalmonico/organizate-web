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

import com.bruno.org.dao.DataException;
import com.bruno.org.model.EmpleadoCriteria;
import com.bruno.org.model.EmpleadoDTO;
import com.bruno.org.model.Results;
import com.bruno.org.service.EmpleadoService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.EmpleadoServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.SessionManager;
import com.bruno.training.web.util.Views;
import com.mysql.cj.result.LongValueFactory;

@WebServlet("/private/UsuarioServlet")
public class UsuarioServletPrivate extends HttpServlet {

	private static SimpleDateFormat FECHA_OF = new SimpleDateFormat("YYYY/MM/dd"); 
	
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
				request.setAttribute(Attributes.RESULTADOS, resultados.getPage());
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
			targetView = Views.LOGOUT;
			forwardOrRedirect = false;
		} else if (Actions.REGISTRAR.equalsIgnoreCase(action)) {

			EmpleadoDTO empleado = new EmpleadoDTO();
			String nombre = request.getParameter(Parameters.NOMBRE);
			String apellido = request.getParameter(Parameters.APELLIDO);
			String email = request.getParameter(Parameters.EMAIL);
			String contrasena = request.getParameter(Parameters.CONTRASENA);
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

		}

		RouterUtils.route(request, response, forwardOrRedirect, targetView);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
