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
import com.bruno.org.model.ProyectoCriteria;
import com.bruno.org.model.ProyectoDTO;
import com.bruno.org.model.Results;
import com.bruno.org.model.TareaDTO;
import com.bruno.org.service.ComentarioProyectoService;
import com.bruno.org.service.ProyectoService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.ComentarioProyectoServiceImpl;
import com.bruno.org.service.impl.ProyectoServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.Views;

@WebServlet("/private/ProyectoServlet")
public class ProyectoServlet extends HttpServlet {

	private static SimpleDateFormat FECHA_OF = new SimpleDateFormat("yyyy-MM-dd");
	private Logger logger = LogManager.getLogger(ProyectoServlet.class);
	private ProyectoService proyectoService = null;
	private ComentarioProyectoService comentario = null;

	public ProyectoServlet() {
		super();
		proyectoService = new ProyectoServiceImpl();
		comentario = new ComentarioProyectoServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = false;

		if (Actions.SEARCH.equalsIgnoreCase(action)) {
			String nombre = request.getParameter(Parameters.NOMBRE);
			ProyectoCriteria criteria = new ProyectoCriteria();
			criteria.setNombre(nombre);

			String idStr = request.getParameter(Parameters.ID);
			if (idStr == null || idStr.isEmpty()) {
				criteria.setId(null);
			} else {
				Long id = Long.valueOf(idStr);
				criteria.setId(id);
			}

			String descripcion = request.getParameter(Parameters.DESCRIPCION);
			if (descripcion == null || descripcion.isEmpty()) {
				criteria.setDescripcion(null);
			} else {
				criteria.setDescripcion(descripcion);
			}

			String fechaRealInicioStr = request.getParameter(Parameters.FECHAREALINICIO);
			if (fechaRealInicioStr == null || fechaRealInicioStr.isEmpty()) {
				criteria.setFechaRealInicio(null);
			} else {
				Date fechaRealInicio = null;
				try {
					fechaRealInicio = FECHA_OF.parse(fechaRealInicioStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				criteria.setFechaRealInicio(fechaRealInicio);
			}

			String fechaRealFinStr = request.getParameter(Parameters.FECHAREALFIN);
			if (fechaRealFinStr == null || fechaRealFinStr.isEmpty()) {
				criteria.setFechaRealFin(null);
			} else {
				Date fechaRealFin = null;
				try {
					fechaRealFin = FECHA_OF.parse(fechaRealFinStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				criteria.setFechaRealFin(fechaRealFin);
			}

			String fechaEstimadaFinStr = request.getParameter(Parameters.FECHAESTIMADAFIN);
			if (fechaEstimadaFinStr == null || fechaEstimadaFinStr.isEmpty()) {
				criteria.setFechaEstimadaFin(null);
			} else {
				Date fechaEstimadaFin = null;
				try {
					fechaEstimadaFin = FECHA_OF.parse(fechaEstimadaFinStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				criteria.setFechaEstimadaFin(fechaEstimadaFin);
			}

			String fechaEstimadaInicioStr = request.getParameter(Parameters.FECHAESTIMADAINICIO);
			if (fechaEstimadaInicioStr == null || fechaEstimadaInicioStr.isEmpty()) {
				criteria.setFechaEstimadaInicio(null);
			} else {
				Date fechaEstimadaInicio = null;
				try {
					fechaEstimadaInicio = FECHA_OF.parse(fechaEstimadaInicioStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				criteria.setFechaEstimadaInicio(fechaEstimadaInicio);
			}

			try {
				int PAGE_SIZE = 3; /* prefs usuario o default cfg ConfiugrationPar... */
				int BROWSABLE_PAGE_COUNT = 10;

				String newPageStr = request.getParameter("page");
				int newPage = Strings.isEmpty(newPageStr) ? 1 : Integer.valueOf(newPageStr);

				Results<ProyectoDTO> resultados = proyectoService.findByCriteria(criteria,
						(newPage - 1) * PAGE_SIZE + 1, PAGE_SIZE);
				logger.info("Encontrados " + resultados.getTotal() + " proyectos");

				request.setAttribute("resultados", resultados);
				//
				// Paging attributes for view rendering
				//

				// TODO: A URLUtils... con encode ...
				StringBuilder urlBuilder = new StringBuilder();
				urlBuilder.append("/private/ProyectoServlet?"); // request.getURI()
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

				targetView = Views.PROYECTO_SEARCH;
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
				ProyectoDTO proyecto = proyectoService.findById(id);
				request.setAttribute(Attributes.PROYECTO, proyecto);

				targetView = Views.PROYECTO_RESULTS;
				forwardOrRedirect = true;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}

		} else if (Actions.CREATE.equalsIgnoreCase(action)) {
			try {
				ProyectoDTO proyecto = new ProyectoDTO();
				String nombre = request.getParameter(Parameters.NOMBRE);
				String descripcion = request.getParameter(Parameters.DESCRIPCION);
				String fechaRealInicioStr = request.getParameter(Parameters.FECHAREALINICIO);
				String fechaRealFinStr = request.getParameter(Parameters.FECHAREALFIN);
				String fechaEstimadaInicioStr = request.getParameter(Parameters.FECHAESTIMADAINICIO);
				String fechaEstimadaFinStr = request.getParameter(Parameters.FECHAESTIMADAFIN);
				String estadoIdStr = request.getParameter(Parameters.ESTADOID);
				String clienteIdStr = request.getParameter(Parameters.CLIENTEID);
				String importeStr = request.getParameter(Parameters.IMPORTE);
				Long estadoId = null;
				if (estadoIdStr != null && !estadoIdStr.isEmpty()) {
					estadoId = Long.valueOf(estadoIdStr);
				} else {
					logger.warn("Estado ID não fornecido.");
					// Trate o caso onde estadoId é necessário, mas não foi fornecido
				}
				
				Long clienteId = null;
				if (clienteIdStr != null && !clienteIdStr.isEmpty()) {
					clienteId = Long.valueOf(clienteIdStr);
				} else {
					logger.warn("Projeto ID não fornecido.");
					// Trate o caso onde proyectoId é necessário, mas não foi fornecido
				}
				
				Double importe = null;
				if (importeStr != null && !importeStr.isEmpty()) {
					importe = Double.valueOf(importeStr);
				} else {
					logger.warn("Projeto ID não fornecido.");
					// Trate o caso onde proyectoId é necessário, mas não foi fornecido
				}
				
				
				Date fechaRealInicio = null;
				Date fechaRealFin = null;
				Date fechaEstimadaInicio = null;
				Date fechaEstimadaFin = null;
				try {
					fechaRealInicio = FECHA_OF.parse(fechaRealInicioStr);
					fechaRealFin = FECHA_OF.parse(fechaRealFinStr);
					fechaEstimadaInicio = FECHA_OF.parse(fechaEstimadaInicioStr);
					fechaEstimadaFin = FECHA_OF.parse(fechaEstimadaFinStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				proyecto.setNombre(nombre);
				proyecto.setDescripcion(descripcion);
				proyecto.setFechaRealInicio(fechaRealInicio);
				proyecto.setFechaRealFin(fechaRealFin);
				proyecto.setFechaEstimadaInicio(fechaEstimadaInicio);
				proyecto.setFechaEstimadaFin(fechaEstimadaFin);
				proyecto.setImporte(importe);
				proyecto.setClienteId(clienteId);
				proyecto.setEstadoId(estadoId);
				

				proyectoService.registrar(proyecto);

				targetView = Views.PROYECTO_CREAR;
				forwardOrRedirect = true;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}
		} else if (Actions.UPDATE.equalsIgnoreCase(action)) {
			try {
				ProyectoDTO proyecto = new ProyectoDTO();
				String idStr = request.getParameter(Parameters.ID); 
				String nombre = request.getParameter(Parameters.NOMBRE);
				String descripcion = request.getParameter(Parameters.DESCRIPCION);
				String fechaRealInicioStr = request.getParameter(Parameters.FECHAREALINICIO);
				String fechaRealFinStr = request.getParameter(Parameters.FECHAREALFIN);
				String fechaEstimadaInicioStr = request.getParameter(Parameters.FECHAESTIMADAINICIO);
				String fechaEstimadaFinStr = request.getParameter(Parameters.FECHAESTIMADAFIN);
				String estadoIdStr = request.getParameter(Parameters.ESTADOID);
				String clienteIdStr = request.getParameter(Parameters.CLIENTEID);
				String importeStr = request.getParameter(Parameters.IMPORTE);
				
				Long id = null;
				if (idStr != null && !idStr.isEmpty()) {
					id = Long.valueOf(idStr);
				} else {
					logger.warn("Estado ID não fornecido.");
					// Trate o caso onde estadoId é necessário, mas não foi fornecido
				}
				
				Long estadoId = null;
				if (estadoIdStr != null && !estadoIdStr.isEmpty()) {
					estadoId = Long.valueOf(estadoIdStr);
				} else {
					logger.warn("Estado ID não fornecido.");
					// Trate o caso onde estadoId é necessário, mas não foi fornecido
				}
				
				Long clienteId = null;
				if (clienteIdStr != null && !clienteIdStr.isEmpty()) {
					clienteId = Long.valueOf(clienteIdStr);
				} else {
					logger.warn("Projeto ID não fornecido.");
					// Trate o caso onde proyectoId é necessário, mas não foi fornecido
				}
				
				Double importe = null;
				if (importeStr != null && !importeStr.isEmpty()) {
					importe = Double.valueOf(importeStr);
				} else {
					logger.warn("Projeto ID não fornecido.");
					// Trate o caso onde proyectoId é necessário, mas não foi fornecido
				}

				
				Date fechaRealInicio = null;
				Date fechaRealFin = null;
				Date fechaEstimadaInicio = null;
				Date fechaEstimadaFin = null;
				try {
					fechaRealInicio = FECHA_OF.parse(fechaRealInicioStr);
					fechaRealFin = FECHA_OF.parse(fechaRealFinStr);
					fechaEstimadaInicio = FECHA_OF.parse(fechaEstimadaInicioStr);
					fechaEstimadaFin = FECHA_OF.parse(fechaEstimadaFinStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				proyecto.setId(id);
				proyecto.setNombre(nombre);
				proyecto.setDescripcion(descripcion);
				proyecto.setFechaRealInicio(fechaRealInicio);
				proyecto.setFechaRealFin(fechaRealFin);
				proyecto.setFechaEstimadaInicio(fechaEstimadaInicio);
				proyecto.setFechaEstimadaFin(fechaEstimadaFin);
				proyecto.setEstadoId(estadoId);
				proyecto.setImporte(importe);
				proyecto.setClienteId(clienteId);

				proyectoService.update(proyecto);

				targetView = Views.PROYECTO_UPDATE;
				forwardOrRedirect = false;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}

		} else if (Actions.DELETE.equalsIgnoreCase(action)) {
			try {
				String idStr = request.getParameter(Parameters.ID);
				Long id = Long.valueOf(idStr);
				
			
				proyectoService.delete(id);

				targetView = Views.PROYECTO_DELETE;
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
