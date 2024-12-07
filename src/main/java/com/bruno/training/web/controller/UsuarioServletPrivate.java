package com.bruno.training.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import com.bruno.org.dao.DataException;
import com.bruno.org.model.EmpleadoCriteria;
import com.bruno.org.model.EmpleadoDTO;
import com.bruno.org.model.Results;
import com.bruno.org.service.EmpleadoService;
import com.bruno.org.service.FileService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.EmpleadoServiceImpl;
import com.bruno.org.service.impl.FileServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.ErrorCodes;
import com.bruno.training.web.util.Errors;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.SessionManager;
import com.bruno.training.web.util.ValidationUtils;
import com.bruno.training.web.util.Views;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 100 // 100MB
)
@WebServlet("/private/UsuarioServlet")
public class UsuarioServletPrivate extends HttpServlet {

	private static SimpleDateFormat FECHA_OF = new SimpleDateFormat("yyyy-MM-dd");

	private static Logger logger = LogManager.getLogger(UsuarioServletPrivate.class);

	private EmpleadoService empleadoService = null;
	private FileService fileService = null;

	public UsuarioServletPrivate() {
		super();
		empleadoService = new EmpleadoServiceImpl();

		fileService = new FileServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Errors errors = new Errors();
		request.setAttribute(Attributes.ERRORS, errors);
		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = false;

		if (Actions.SEARCH.equalsIgnoreCase(action)) {
			EmpleadoCriteria criteria = new EmpleadoCriteria();

			String idStr = request.getParameter(Parameters.ID);
			if (idStr == null || idStr.isEmpty()) {
				criteria.setId(null);
			} else {
				ValidationUtils.validateNumericFieldNotMandatory(idStr, Parameters.ID,
						ErrorCodes.INVALID_NUMERIC_FORMAT, errors);
				if (!errors.hasErrors()) {
					criteria.setId(Long.valueOf(idStr));
				}
			}

			String nombre = request.getParameter(Parameters.NOMBRE);
			if (nombre == null || nombre.isEmpty()) {
				criteria.setNombre(null);
			} else {
				ValidationUtils.validateNombreNotMandatory(nombre, errors);
				if (!errors.hasErrors()) {
					criteria.setNombre(nombre.trim());
				}
			}

			String rolStr = request.getParameter(Parameters.ROLID);
			if (rolStr == null || rolStr.isEmpty()) {
				criteria.setRolId(null);
			} else {
				ValidationUtils.validateNumericFieldNotMandatory(rolStr, Parameters.ROLID,
						ErrorCodes.INVALID_NUMERIC_FORMAT, errors);
				if (!errors.hasErrors()) {
					criteria.setRolId(Integer.valueOf(rolStr));
				}
			}

			String email = request.getParameter(Parameters.EMAIL);
			if (email == null || email.isEmpty()) {
				criteria.setEmail(null);
			} else {
				ValidationUtils.validateCorreoNotMandatory(email, errors);
				if (!errors.hasErrors()) {
					criteria.setEmail(email.trim());
				}
			}
			
			String fechaAltaStr = request.getParameter(Parameters.FECHAALTA);
			if (fechaAltaStr == null || fechaAltaStr.isEmpty()) {
				criteria.setFechaAlta(null);
			} else {
				ValidationUtils.validateCorreoNotMandatory(email, errors);
				if (!errors.hasErrors()) {
					try {
						criteria.setFechaAlta(FECHA_OF.parse(fechaAltaStr));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			

			if (errors.hasErrors()) {
				request.setAttribute("errors", errors);
				targetView = Views.EMPLEADO_SEARCH;
				forwardOrRedirect = true;
			} else {
				// Ejecutar la búsqueda
				try {
					Results<EmpleadoDTO> resultados = empleadoService.findByCriteria(criteria, 1, 10);
					request.setAttribute(Attributes.RESULTADOS, resultados.getPage());
				} catch (DataException | ServiceException e) {
					logger.error(e.getMessage(), e);
				}
				targetView = Views.EMPLEADO_SEARCH;
				forwardOrRedirect = true;
			}

		} else if (Actions.DETAIL.equalsIgnoreCase(action)) {
			try {

				String idStr = request.getParameter(Parameters.ID);
				Long id = Long.valueOf(idStr);
				EmpleadoDTO emp = empleadoService.findById(id);
				request.setAttribute(Attributes.EMPLEADO, emp);
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
			try {
				EmpleadoDTO empleado = new EmpleadoDTO();

				String nombre = request.getParameter(Parameters.NOMBRE);
				String apellido = request.getParameter(Parameters.APELLIDO);
				String email = request.getParameter(Parameters.EMAIL);
				String contrasena = request.getParameter(Parameters.CONTRASENA);
				String fechaAltaStr = request.getParameter(Parameters.FECHAALTA);
				String rolIdStr = request.getParameter(Parameters.ROLID);

				// Si no hay errores, registrar al usuario
				if (!errors.hasErrors()) {
					empleado.setNombre(nombre);
					empleado.setApellido(apellido);
					empleado.setEmail(email);
					empleado.setContrasena(contrasena);
					empleado.setRolId(Integer.valueOf(rolIdStr));
					empleado.setFechaAlta(FECHA_OF.parse(fechaAltaStr));
					

					try {
						empleadoService.registrar(empleado);
						targetView = Views.LOGIN;
						forwardOrRedirect = false;

					} catch (Exception e) {
						logger.error("Error al registrar el usuario o al manejar la imagen de perfil", e);
						targetView = Views.EMPLEADO_INSERT;
						forwardOrRedirect = true;
					}

				} else {
					request.setAttribute(Attributes.ERRORS, errors);
					forwardOrRedirect = true;
					targetView = Views.EMPLEADO_INSERT;
				}
			} catch (Exception e) {
				logger.error("Error procesando la acción INGRESAR: ", e);
			}

//			
//			errors.addFieldError("correo", ErrorCodes.INVALID_EMAIL);
//			if (!Strings.isBlank(contrasena)) {
//				contrasena = contrasena.trim();
//				if (contrasena.length() >= 6 && contrasena.length() <= 12) {
//					// Expresion regular: Para MAY/min/numers...
//				} else {
//					errors.addFieldError("contrasena", ErrorCodes.INVALID_PASSWORD_LENGTH);
//				}
//			} else {
//				errors.addFieldError("contrasena", ErrorCodes.MANDATORY_FIELD);
//			}
//			
//			Integer rolId = Integer.valueOf(rol);
//
//			
//			if (!errors.hasErrors()) {
//				empleado.setNombre(nombre);
//				empleado.setApellido(apellido);
//				empleado.setEmail(email);
//				empleado.setContrasena(contrasena);
//				empleado.setRolId(rolId);
//				empleado.setFechaAlta(fechaAlta);
//
//				try {
//					empleadoService.registrar(empleado);
//				} catch (DataException e) {
//					e.printStackTrace();
//				} catch (ServiceException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				targetView = Views.EMPLEADO_INSERT;
//				forwardOrRedirect = true;
//			} else {
//				forwardOrRedirect = true;
//				targetView = Views.EMPLEADO_INSERT;
//			}

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

				Date fechaAlta = null;

				try {
					fechaAlta = FECHA_OF.parse(fechaAltaStr);

				} catch (ParseException e) {
					e.printStackTrace();
				}
				empleado.setId(id);
				empleado.setNombre(nombre);
				empleado.setApellido(apellido);
				empleado.setEmail(email);
				empleado.setFechaAlta(fechaAlta);
				empleado.setRolId(rolId);
				empleadoService.update(empleado);

				targetView = Views.EMPLEADO_UPDATE;
				forwardOrRedirect = false;

			} catch (DataException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}
		} else if (Actions.UPLOAD_IMAGE.equalsIgnoreCase(action)) {
			try {
				Long empleadoId = Long.valueOf(request.getParameter(Parameters.ID));
				Part filePart = request.getPart("file");

				if (filePart != null && filePart.getSize() > 0) {
					byte[] fileBytes = new byte[(int) filePart.getSize()];
					try (InputStream inputStream = filePart.getInputStream()) {
						inputStream.read(fileBytes);
					}

					// Guardar la imagen usando el servicio
					fileService.uploadEmpleadoProfileImage(empleadoId, fileBytes);
					logger.info("Imagen de perfil subida correctamente para el usuario con ID: {}", empleadoId);
				}

				// Recuperar información actualizada del usuario
				EmpleadoDTO empleado = empleadoService.findById(empleadoId);
				request.setAttribute(Attributes.EMPLEADO, empleado);

				targetView = Views.EMPLEADO_DETAIL;
				forwardOrRedirect = true;

			} catch (Exception e) {
				logger.error("Error al subir la imagen de perfil", e);
			}
		}
		RouterUtils.route(request, response, forwardOrRedirect, targetView);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
