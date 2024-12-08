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
import com.bruno.org.model.ImputacionCriteria;
import com.bruno.org.model.ImputacionDTO;
import com.bruno.org.model.Results;
import com.bruno.org.service.ImputacionService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.ImputacionServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.Views;

@WebServlet("/private/ImputacionServlet")
public class ImputacionServlet extends HttpServlet {

	private static SimpleDateFormat FECHA_OF = new SimpleDateFormat("yyyy-MM-dd");
	private Logger logger = LogManager.getLogger(ImputacionServlet.class);
	private ImputacionService imputacionService = null;

	public ImputacionServlet() {
		super();
		imputacionService = new ImputacionServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = false;

		if (Actions.SEARCHALL.equalsIgnoreCase(action)) {

			ImputacionCriteria criteria = new ImputacionCriteria();

			String proyectoIdStr = request.getParameter(Parameters.PROYECTOID);
			if (proyectoIdStr == null || proyectoIdStr.isEmpty()) {
				criteria.setProyectoId(null);
			} else {
				Long proyectoId = Long.valueOf(proyectoIdStr);
				criteria.setProyectoId(proyectoId);
			}

			try {
				double resultados = imputacionService.findByTotalByCriteria(criteria);

				request.setAttribute(Attributes.RESULTADOS, resultados);

				targetView = Views.IMPUTACION_SEARCHALL;
				forwardOrRedirect = true;

			} catch (OrganizateException pe) {
				logger.error(pe.getMessage(), pe);
			} catch (ServiceException e) {
				e.printStackTrace();
			}

		} else if (Actions.SEARCH.equalsIgnoreCase(action)) {

			ImputacionCriteria criteria = new ImputacionCriteria();

			String idStr = request.getParameter(Parameters.ID);
			if (idStr == null || idStr.isEmpty()) {
				criteria.setId(null);
			} else {
				Long id = Long.valueOf(idStr);
				criteria.setId(id);
			}

			String proyectoIdStr = request.getParameter(Parameters.PROYECTOID);
			if (proyectoIdStr == null || proyectoIdStr.isEmpty()) {
				criteria.setProyectoId(null);
			} else {
				Long proyectoId = Long.valueOf(proyectoIdStr);
				criteria.setProyectoId(proyectoId);
			}

			String tareaIdStr = request.getParameter(Parameters.TAREAID);
			if (tareaIdStr == null || tareaIdStr.isEmpty()) {
				criteria.setTareaId(null);
			} else {
				Long tareaId = Long.valueOf(tareaIdStr);
				criteria.setTareaId(tareaId);
			}

			String empleadoIdStr = request.getParameter(Parameters.EMPLEADOID);
			if (empleadoIdStr == null || empleadoIdStr.isEmpty()) {
				criteria.setEmpleadoId(null);
			} else {
				Long empleadoId = Long.valueOf(empleadoIdStr);
				criteria.setEmpleadoId(empleadoId);
			}

			String fechaHoraStr = request.getParameter(Parameters.FECHAHORA);
			if (fechaHoraStr == null || fechaHoraStr.isEmpty()) {
				criteria.setFechaHora(null);
			} else {
				Date fechaHora = null;
				try {
					fechaHora = FECHA_OF.parse(fechaHoraStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				criteria.setFechaHora(fechaHora);
			}

			String comentario = request.getParameter(Parameters.COMENTARIO);
			criteria.setComentario(comentario);

			try {
				int PAGE_SIZE = 5; /* prefs usuario o default cfg ConfiugrationPar... */
				int BROWSABLE_PAGE_COUNT = 10;

				String newPageStr = request.getParameter("page");
				int newPage = Strings.isEmpty(newPageStr) ? 1 : Integer.valueOf(newPageStr);

				Results<ImputacionDTO> resultados = imputacionService.findByCriteria(criteria,
						(newPage - 1) * PAGE_SIZE + 1, PAGE_SIZE);
				logger.info("Encontrados " + resultados.getTotal() + " horas imputadas");

				request.setAttribute("resultados", resultados);
				//
				// Paging attributes for view rendering
				//

				// TODO: A URLUtils... con encode ...
				StringBuilder urlBuilder = new StringBuilder();
				urlBuilder.append("/private/ImputacionServlet?"); // request.getURI()
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

				targetView = Views.IMPUTACION_SEARCH;
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
				ImputacionDTO imputacion = imputacionService.findById(id);
				request.setAttribute(Attributes.IMPUTACION, imputacion);

				targetView = Views.IMPUTACION_DETAIL;
				forwardOrRedirect = true;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}

		} else if (Actions.CREATE.equalsIgnoreCase(action)) {
			try {
				ImputacionDTO imputacion = new ImputacionDTO();

				String proyectoIdStr = request.getParameter(Parameters.PROYECTOID);
				if (proyectoIdStr == null || proyectoIdStr.isEmpty()) {
					imputacion.setProyectoId(null);
				} else {
					Long proyectoId = Long.valueOf(proyectoIdStr);
					imputacion.setProyectoId(proyectoId);
				}

				String tareaIdStr = request.getParameter(Parameters.TAREAID);
				if (tareaIdStr == null || tareaIdStr.isEmpty()) {
					imputacion.setTareaId(null);
				} else {
					Long tareaId = Long.valueOf(tareaIdStr);
					imputacion.setTareaId(tareaId);
				}

				String empleadoIdStr = request.getParameter(Parameters.EMPLEADOID);
				if (empleadoIdStr == null || empleadoIdStr.isEmpty()) {
					imputacion.setEmpleadoId(null);
				} else {
					Long empleadoId = Long.valueOf(empleadoIdStr);
					imputacion.setEmpleadoId(empleadoId);
				}

				String horasImputadasStr = request.getParameter(Parameters.HORASIMPUTADAS);
				if (horasImputadasStr == null || horasImputadasStr.isEmpty()) {
					imputacion.setHorasImputadas(null);
				} else {
					Double horasImputadas = Double.valueOf(horasImputadasStr);
					imputacion.setHorasImputadas(horasImputadas);
				}

				String fechaHoraStr = request.getParameter(Parameters.FECHAHORA);
				if (fechaHoraStr == null || fechaHoraStr.isEmpty()) {
					imputacion.setFechaHora(null);
				} else {
					Date fechaHora = null;
					try {
						fechaHora = FECHA_OF.parse(fechaHoraStr);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					imputacion.setFechaHora(fechaHora);
				}

				String comentario = request.getParameter(Parameters.COMENTARIO);
				imputacion.setComentario(comentario);

				imputacionService.imputar(imputacion);

				targetView = Views.IMPUTACION_CREAR;
				forwardOrRedirect = true;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}

		} else if (Actions.DELETE.equalsIgnoreCase(action)) {
			try {
				String idStr = request.getParameter(Parameters.ID);
				Long id = Long.valueOf(idStr);

				imputacionService.delete(id);

				targetView = Views.IMPUTACION_DELETE;
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
