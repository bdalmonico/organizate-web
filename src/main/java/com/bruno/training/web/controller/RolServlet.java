package com.bruno.training.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bruno.OrganizateException;
import com.bruno.org.dao.DataException;
import com.bruno.org.model.RolDTO;
import com.bruno.org.service.RolService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.RolServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.Views;

@WebServlet("/private/RolServlet")
public class RolServlet extends HttpServlet {

	private Logger logger = LogManager.getLogger(RolServlet.class);
	private RolService rolService = null;

	public RolServlet() {
		super();
		rolService = new RolServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = false;

		if (Actions.SEARCH.equalsIgnoreCase(action)) {

			RolDTO rol = new RolDTO();

			String rolIdStr = request.getParameter(Parameters.ROLID);

			if (rolIdStr == null || rolIdStr.isEmpty()) {
				rol.setId(null);
			} else {
				Long rolId = Long.valueOf(rolIdStr);
				rol.setId(rolId);
			}

			try {
				RolDTO resultados = rolService.findById(rol.getId());
				logger.info("Encontrados " + resultados.getNombre() + " comentarios");

				request.setAttribute(Attributes.RESULTADOS, resultados);

				targetView = Views.ROL_SEARCH;
				forwardOrRedirect = true;

			} catch (OrganizateException pe) {
				logger.error(pe.getMessage(), pe);
			} catch (ServiceException e) {
				e.printStackTrace();
			}

		} else if (Actions.DETAIL.equalsIgnoreCase(action)) {
			try {
				String idStr = request.getParameter(Parameters.ROLID);
				if (idStr != null && !idStr.isEmpty()) {
					Long id = Long.valueOf(idStr);
					RolDTO rol = rolService.findById(id);
					request.setAttribute(Attributes.ROL, rol);

					targetView = Views.ROL_DETAIL;
					forwardOrRedirect = true;
				} else {
					logger.error("ID is null or empty");
				}

			} catch (OrganizateException pe) {
				logger.error(pe.getMessage(), pe);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else if (Actions.ALL.equalsIgnoreCase(action)) {
			List<RolDTO> resultados;
			try {
				resultados = rolService.findAll();
				if (resultados != null && !resultados.isEmpty()) {
					request.setAttribute(Attributes.RESULTADOS, resultados);
				}
				targetView = Views.ROL_SEARCHALL;
				forwardOrRedirect = true;
			} catch (DataException e) {
				logger.error(e.getMessage(), e);
			}
		} else if (Actions.DETAILAll.equalsIgnoreCase(action)) {
			try {

				String idStr = request.getParameter(Parameters.ROLID);
				Long id = Long.valueOf(idStr);
				RolDTO rol = (RolDTO) rolService.findAll();
				request.setAttribute(Attributes.ROL, rol);
				targetView = Views.EMPLEADO_DETAIL;
				forwardOrRedirect = true;
			} catch (DataException e) {
				logger.error(e.getMessage(), e);
			}

		}

//		} else if (Actions.CREATE.equalsIgnoreCase(action)) {
//			ComentarioTareaDTO comentarioTarea = new ComentarioTareaDTO();
//
//			String tareaIdStr = request.getParameter(Parameters.TAREAID);
//
//			String empleadoIdStr = request.getParameter(Parameters.EMPLEADOID);
//
//			String fechaPublicacionStr = request.getParameter(Parameters.FECHAPUBLICACION);
//
//			String comentario = request.getParameter(Parameters.COMENTARIO);
//
//			Long tareaId = null;
//			
//
//			if (tareaIdStr != null && !tareaIdStr.isEmpty()) {
//				tareaId = Long.valueOf(tareaIdStr);
//				comentarioTarea.setTareaId(tareaId);
//			} else {
//				logger.warn(" ID tarea não fornecido.");
//			}
//			
//
//			Long empleadoId = null;
//			
//			if (empleadoIdStr != null && !empleadoIdStr.isEmpty()) {
//				empleadoId = Long.valueOf(empleadoIdStr);
//				comentarioTarea.setEmpleadoId(empleadoId);
//				
//			} else {
//				logger.warn("ID  empleadonão fornecido.");
//			}
//			
//
//			if (fechaPublicacionStr == null || fechaPublicacionStr.isEmpty()) {
//				comentarioTarea.setFechaHora(null);
//			} else {
//				Date fechaPublicacion = null;
//				try {
////					fechaPublicacion = FECHA_OF.parse(fechaPublicacionStr);
//					comentarioTarea.setFechaHora(fechaPublicacion);
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//
//			comentarioTarea.setComentario(comentario);
//			
//			try {
////				comentarioTareaService.comentar(comentarioTarea);
//			} catch (DataException | ServiceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			targetView = Views.COMENTARIOTAREA_CREAR;
//			forwardOrRedirect = true;
//			
//		} else if (Actions.UPDATE.equalsIgnoreCase(action)) {
//			try {
//				ComentarioTareaDTO comentarioTarea = new ComentarioTareaDTO();
//				
//				String comentarioIdStr = request.getParameter(Parameters.ID);
//
//				String tareaIdStr = request.getParameter(Parameters.TAREAID);
//
//				String empleadoIdStr = request.getParameter(Parameters.EMPLEADOID);
//
//				String fechaPublicacionStr = request.getParameter(Parameters.FECHAPUBLICACION);
//
//				String comentario = request.getParameter(Parameters.COMENTARIO);
//
//
//				Long comentarioId = null;
//
//				
//
//				if (comentarioIdStr != null && !comentarioIdStr.isEmpty()) {
//					comentarioId = Long.valueOf(comentarioIdStr);
//					comentarioTarea.setId(comentarioId);
//				} else {
//					logger.warn("ID não fornecido.");
//				}
//				
//				
//				Long tareaId = null;
//
//				tareaId = Long.valueOf(tareaIdStr);
//
//				if (tareaIdStr != null && !tareaIdStr.isEmpty()) {
//
//					comentarioTarea.setTareaId(tareaId);
//				} else {
//					logger.warn("ID não fornecido.");
//				}
//
//				Long empleadoId = null;
//				empleadoId = Long.valueOf(empleadoIdStr);
//
//				if (empleadoIdStr != null && !empleadoIdStr.isEmpty()) {
//
//					comentarioTarea.setEmpleadoId(empleadoId);
//				} else {
//					logger.warn("ID não fornecido.");
//				}
//
//				if (fechaPublicacionStr == null || fechaPublicacionStr.isEmpty()) {
//					comentarioTarea.setFechaHora(null);
//				} else {
//					Date fechaPublicacion = null;
//					try {
////						fechaPublicacion = FECHA_OF.parse(fechaPublicacionStr);
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					comentarioTarea.setFechaHora(fechaPublicacion);
//				}
//
//				comentarioTarea.setComentario(comentario);
//
////				comentarioTareaService.update(comentarioTarea);
//
//				targetView = Views.COMENTARIOTAREA_UPDATE;
//				forwardOrRedirect = false;
//
//			} catch (OrganizateException | ServiceException pe) {
//				logger.error(pe.getMessage(), pe);
//			}
//
//		} else if (Actions.DELETE.equalsIgnoreCase(action)) {
//			try {
//				String idStr = request.getParameter(Parameters.ID);
//				Long id = Long.valueOf(idStr);
//
////				comentarioTareaService.delete(id);
//
//				targetView = Views.COMENTARIOTAREA_DELETE;
//				forwardOrRedirect = true;
//
//			} catch (OrganizateException | ServiceException pe) {
//				logger.error(pe.getMessage(), pe);
//			}
//
//		}
		RouterUtils.route(request, response, forwardOrRedirect, targetView);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
