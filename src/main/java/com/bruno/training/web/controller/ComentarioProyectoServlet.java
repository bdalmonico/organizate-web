package com.bruno.training.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import com.bruno.OrganizateException;
import com.bruno.org.dao.DataException;
import com.bruno.org.model.ComentarioProyectoDTO;
import com.bruno.org.model.Results;
import com.bruno.org.service.ComentarioProyectoService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.ComentarioProyectoServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.Views;

@WebServlet("/private/ComentarioProyectoServlet")
public class ComentarioProyectoServlet extends HttpServlet {

	private static SimpleDateFormat FECHA_OF = new SimpleDateFormat("yyyy-MM-dd");
	private Logger logger = LogManager.getLogger(ComentarioProyectoServlet.class);
	private ComentarioProyectoService comentarioProyectoService = null;

	public ComentarioProyectoServlet() {
		super();
		comentarioProyectoService = new ComentarioProyectoServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = false;

		if (Actions.SEARCH.equalsIgnoreCase(action)) {

			ComentarioProyectoDTO comentarioProyecto = new ComentarioProyectoDTO();

			String proyectoIdStr = request.getParameter(Parameters.PROYECTOID);

			Long proyectoId = Long.valueOf(proyectoIdStr);

			if (proyectoIdStr == null || proyectoIdStr.isEmpty()) {
				comentarioProyecto.setProyectoId(null);
			} else {

				comentarioProyecto.setProyectoId(proyectoId);
			}

			try {
				int PAGE_SIZE = 5; /* prefs usuario o default cfg ConfiugrationPar... */
				int BROWSABLE_PAGE_COUNT = 10;

				String newPageStr = request.getParameter("page");
				int newPage = Strings.isEmpty(newPageStr) ? 1 : Integer.valueOf(newPageStr);
				Results<ComentarioProyectoDTO> resultados = comentarioProyectoService.findByProyecto(proyectoId,
						(newPage - 1) * PAGE_SIZE + 1, PAGE_SIZE);
				logger.info("Encontrados " + resultados.getTotal() + " comentarios");

				request.setAttribute("resultados", resultados);
				//
				// Paging attributes for view rendering
				//

				// TODO: A URLUtils... con encode ...
				StringBuilder urlBuilder = new StringBuilder();
				urlBuilder.append("/private/ComentarioProyectoServlet?"); // request.getURI()
				Map<String, String[]> parametersMap = request.getParameterMap();
				Set<String> parameterNames = parametersMap.keySet();
				String parameterValue = null;
				for (String parameterName : parameterNames) {
					if (!"page".equalsIgnoreCase(parameterName)) {
						urlBuilder.append(parameterName).append("=").append(request.getParameter(parameterName));
						urlBuilder.append("&");
					}
				}
				String baseURL = urlBuilder.toString();
				request.setAttribute("baseURL", baseURL);

				request.setAttribute("currentPage", Integer.valueOf(newPage));

				// TODO: BUG
				int fromPage = newPage - BROWSABLE_PAGE_COUNT / 2;
				if (fromPage < 1)
					fromPage = 1;
				request.setAttribute("fromPage", Integer.valueOf(fromPage));

				int lastPage = (resultados.getTotal() / PAGE_SIZE) + 1;
				request.setAttribute("lastPage", Integer.valueOf(lastPage));

				// TODO: BUG
				int toPage = newPage + BROWSABLE_PAGE_COUNT / 2;
				if (toPage > lastPage)
					toPage = lastPage;
				request.setAttribute("toPage", Integer.valueOf(toPage));

				targetView = Views.COMENTARIOPROYECTO_SEARCH;
				forwardOrRedirect = true;

			} catch (OrganizateException pe) {
				logger.error(pe.getMessage(), pe);
			} catch (ServiceException e) {
				e.printStackTrace();
			}

		} else if (Actions.DETAIL.equalsIgnoreCase(action)) {
			try {
				String idStr = request.getParameter(Parameters.ID);
				Long id = Long.valueOf(idStr);
				ComentarioProyectoDTO comentarioProyecto = comentarioProyectoService.findById(id);
				request.setAttribute(Attributes.COMENTARIOPROYECTO, comentarioProyecto);

				targetView = Views.COMENTARIOPROYECTO_DETAIL;
				forwardOrRedirect = true;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}

		} else if (Actions.CREATE.equalsIgnoreCase(action)) {
			ComentarioProyectoDTO comentarioProyecto = new ComentarioProyectoDTO();

			String proyectoIdStr = request.getParameter(Parameters.PROYECTOID);

			String empleadoIdStr = request.getParameter(Parameters.EMPLEADOID);

			String fechaPublicacionStr = request.getParameter(Parameters.FECHAPUBLICACION);

			String comentario = request.getParameter(Parameters.COMENTARIO);

			Long proyectoId = null;

			if (proyectoIdStr != null && !proyectoIdStr.isEmpty()) {
				proyectoId = Long.valueOf(proyectoIdStr);
				comentarioProyecto.setProyectoId(proyectoId);
			} else {
				logger.warn(" ID proyecto não fornecido.");
			}

			Long empleadoId = null;

			if (empleadoIdStr != null && !empleadoIdStr.isEmpty()) {
				empleadoId = Long.valueOf(empleadoIdStr);
				comentarioProyecto.setEmpleadoId(empleadoId);

			} else {
				logger.warn("ID  empleadonão fornecido.");
			}

			if (fechaPublicacionStr == null || fechaPublicacionStr.isEmpty()) {
				comentarioProyecto.setFechaHora(null);
			} else {
				Date fechaPublicacion = null;
				try {
					fechaPublicacion = FECHA_OF.parse(fechaPublicacionStr);
					comentarioProyecto.setFechaHora(fechaPublicacion);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			comentarioProyecto.setComentario(comentario);

			try {
				comentarioProyectoService.comentar(comentarioProyecto);
			} catch (DataException | ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			targetView = Views.COMENTARIOPROYECTO_CREAR;
			forwardOrRedirect = true;

		} else if (Actions.UPDATE.equalsIgnoreCase(action)) {
			try {
				ComentarioProyectoDTO comentarioProyecto = new ComentarioProyectoDTO();

				String comentarioIdStr = request.getParameter(Parameters.ID);

				String proyectoIdStr = request.getParameter(Parameters.PROYECTOID);

				String empleadoIdStr = request.getParameter(Parameters.EMPLEADOID);

				String fechaPublicacionStr = request.getParameter(Parameters.FECHAPUBLICACION);

				String comentario = request.getParameter(Parameters.COMENTARIO);

				Long comentarioId = null;

				if (comentarioIdStr != null && !comentarioIdStr.isEmpty()) {
					comentarioId = Long.valueOf(comentarioIdStr);
					comentarioProyecto.setId(comentarioId);
				} else {
					logger.warn("ID não fornecido.");
				}

				Long proyectoId = null;

				proyectoId = Long.valueOf(proyectoIdStr);

				if (proyectoIdStr != null && !proyectoIdStr.isEmpty()) {

					comentarioProyecto.setProyectoId(proyectoId);
				} else {
					logger.warn("ID não fornecido.");
				}

				Long empleadoId = null;
				empleadoId = Long.valueOf(empleadoIdStr);

				if (empleadoIdStr != null && !empleadoIdStr.isEmpty()) {

					comentarioProyecto.setEmpleadoId(empleadoId);
				} else {
					logger.warn("ID não fornecido.");
				}

				if (fechaPublicacionStr == null || fechaPublicacionStr.isEmpty()) {
					comentarioProyecto.setFechaHora(null);
				} else {
					Date fechaPublicacion = null;
					try {
						fechaPublicacion = FECHA_OF.parse(fechaPublicacionStr);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					comentarioProyecto.setFechaHora(fechaPublicacion);
				}

				comentarioProyecto.setComentario(comentario);

				comentarioProyectoService.update(comentarioProyecto);

				targetView = Views.COMENTARIOPROYECTO_UPDATE;
				forwardOrRedirect = false;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}

		} else if (Actions.DELETE.equalsIgnoreCase(action)) {
			try {
				String idStr = request.getParameter(Parameters.ID);
				Long id = Long.valueOf(idStr);

				comentarioProyectoService.delete(id);

				targetView = Views.COMENTARIOPROYECTO_DELETE;
				forwardOrRedirect = true;

			} catch (OrganizateException | ServiceException pe) {
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
