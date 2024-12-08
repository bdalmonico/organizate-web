package com.bruno.training.web.controller;

import java.io.IOException;
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
import com.bruno.org.model.EmpleadoTareaDTO;
import com.bruno.org.model.Results;
import com.bruno.org.service.EmpleadoTareaService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.EmpleadoTareaServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.Views;

@WebServlet("/private/EmpleadoTareaServlet")
public class EmpleadoTareaServlet extends HttpServlet {

	private Logger logger = LogManager.getLogger(EmpleadoTareaServlet.class);
	private EmpleadoTareaService empleadoTareaService = null;

	public EmpleadoTareaServlet() {
		super();
		empleadoTareaService = new EmpleadoTareaServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = false;

		if (Actions.SEARCH.equalsIgnoreCase(action)) {

			EmpleadoTareaDTO empleadoTarea = new EmpleadoTareaDTO();

			String tareaIdStr = request.getParameter(Parameters.TAREAID);

			Long tareaId = Long.valueOf(tareaIdStr);

			if (tareaIdStr == null || tareaIdStr.isEmpty()) {
				empleadoTarea.setTareaId(null);
			} else {

				empleadoTarea.setTareaId(tareaId);
			}
			String empleadoIdStr = request.getParameter(Parameters.EMPLEADOID);

			Long empleadoId = Long.valueOf(empleadoIdStr);

			if (empleadoIdStr == null || empleadoIdStr.isEmpty()) {
				empleadoTarea.setEmpleadoId(null);
			} else {

				empleadoTarea.setEmpleadoId(empleadoId);
			}

			try {
				EmpleadoTareaDTO resultados = empleadoTareaService.findById(empleadoId, tareaId);

				request.setAttribute(Attributes.RESULTADOS, resultados);

				targetView = Views.EMPLEADOTAREA_SEARCH;
				forwardOrRedirect = true;

			} catch (OrganizateException pe) {
				logger.error(pe.getMessage(), pe);
			} catch (ServiceException e) {
				e.printStackTrace();
			}

		} else if (Actions.DELETE.equalsIgnoreCase(action)) {
			try {
				String empleadoIdStr = request.getParameter(Parameters.EMPLEADOID);
				Long empleadoId = Long.valueOf(empleadoIdStr);
				String tareaIdStr = request.getParameter(Parameters.TAREAID);
				Long tareaId = Long.valueOf(tareaIdStr);
				empleadoTareaService.delete(empleadoId, tareaId);

				targetView = Views.EMPLEADOTAREA_DELETE;
				forwardOrRedirect = true;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}

		} else if (Actions.SEARCHTAREAS.equalsIgnoreCase(action)) {

			EmpleadoTareaDTO empleadoTarea = new EmpleadoTareaDTO();

			String empleadoIdStr = request.getParameter(Parameters.EMPLEADOID);

			Long empleadoId = Long.valueOf(empleadoIdStr);

			if (empleadoIdStr == null || empleadoIdStr.isEmpty()) {
				empleadoTarea.setEmpleadoId(null);
			} else {

				empleadoTarea.setEmpleadoId(empleadoId);
			}

			try {
				int PAGE_SIZE = 3; /* prefs usuario o default cfg ConfiugrationPar... */
				int BROWSABLE_PAGE_COUNT = 10;

				String newPageStr = request.getParameter("page");
				int newPage = Strings.isEmpty(newPageStr) ? 1 : Integer.valueOf(newPageStr);

				Results<EmpleadoTareaDTO> resultados = empleadoTareaService.findByEmpleado(empleadoId,
						(newPage - 1) * PAGE_SIZE + 1, PAGE_SIZE);
				logger.info("Encontrados " + resultados.getTotal() + " tareas de empleado");

				request.setAttribute("resultados", resultados);
				//
				// Paging attributes for view rendering
				//

				// TODO: A URLUtils... con encode ...
				StringBuilder urlBuilder = new StringBuilder();
				urlBuilder.append("/private/EmpleadoTareaServlet?"); // request.getURI()
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

				targetView = Views.EMPLEADOTAREA_SEARCHTAREA;
				forwardOrRedirect = true;

			} catch (OrganizateException pe) {
				logger.error(pe.getMessage(), pe);
			} catch (ServiceException e) {
				e.printStackTrace();
			}

		} else if (Actions.SEARCHEMPLEADOS.equalsIgnoreCase(action)) {

			EmpleadoTareaDTO empleadoTarea = new EmpleadoTareaDTO();

			String tareaIdStr = request.getParameter(Parameters.TAREAID);

			Long tareaId = Long.valueOf(tareaIdStr);

			if (tareaIdStr == null || tareaIdStr.isEmpty()) {
				empleadoTarea.setTareaId(null);
			} else {

				empleadoTarea.setEmpleadoId(tareaId);
			}

			try {
				int PAGE_SIZE = 3; /* prefs usuario o default cfg ConfiugrationPar... */
				int BROWSABLE_PAGE_COUNT = 10;

				String newPageStr = request.getParameter("page");
				int newPage = Strings.isEmpty(newPageStr) ? 1 : Integer.valueOf(newPageStr);

				Results<EmpleadoTareaDTO> resultados = empleadoTareaService.findByTarea(tareaId,
						(newPage - 1) * PAGE_SIZE + 1, PAGE_SIZE);
				logger.info("Encontrados " + resultados.getTotal() + " empleados de tarea");

				request.setAttribute("resultados", resultados);
				//
				// Paging attributes for view rendering
				//

				// TODO: A URLUtils... con encode ...
				StringBuilder urlBuilder = new StringBuilder();
				urlBuilder.append("/private/EmpleadoTareaServlet?"); // request.getURI()
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

				targetView = Views.EMPLEADOTAREA_SEARCHEMPLEADO;
				forwardOrRedirect = true;

			} catch (OrganizateException pe) {
				logger.error(pe.getMessage(), pe);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else if (Actions.CREATE.equalsIgnoreCase(action)) {

			EmpleadoTareaDTO empleadoTarea = new EmpleadoTareaDTO();
			String empleadoIdStr = request.getParameter(Parameters.EMPLEADOID);
			String tareaIdStr = request.getParameter(Parameters.TAREAID);

			Long empleadoId = null;
			if (empleadoIdStr != null && !empleadoIdStr.isEmpty()) {
				empleadoId = Long.valueOf(empleadoIdStr);
				empleadoTarea.setEmpleadoId(empleadoId);
			} else {
				logger.warn("empleado o tarea não fornecido.");
			}

			Long tareaId = null;
			if (tareaIdStr != null && !tareaIdStr.isEmpty()) {
				tareaId = Long.valueOf(tareaIdStr);
				empleadoTarea.setTareaId(tareaId);
			} else {
				logger.warn("empleado o tarea não fornecido.");
			}
			try {
				empleadoTareaService.create(empleadoTarea);
			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}
			targetView = Views.TAREA;
			forwardOrRedirect = false;

		}
		RouterUtils.route(request, response, forwardOrRedirect, targetView);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
