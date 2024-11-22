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

import com.bruno.OrganizateException;
import com.bruno.org.model.ComentarioTareaDTO;
import com.bruno.org.model.Results;
import com.bruno.org.model.TareaCriteria;
import com.bruno.org.model.TareaDTO;
import com.bruno.org.service.ComentarioTareaService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.TareaService;
import com.bruno.org.service.impl.ComentarioTareaServiceImpl;
import com.bruno.org.service.impl.TareaServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.Views;


@WebServlet("/private/TareaServlet")
public class TareaServlet extends HttpServlet {
	
	private static SimpleDateFormat FECHA_OF = new SimpleDateFormat("yyyy-MM-dd");
	private Logger logger = LogManager.getLogger(TareaServlet.class);
	private TareaService tareaService = null;
	private ComentarioTareaService comentario = null;

	
	public TareaServlet() {
		super();
		tareaService = new TareaServiceImpl();
		comentario = new ComentarioTareaServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = false;
		
		
		if (Actions.SEARCH.equalsIgnoreCase(action)) {
			String nombre = request.getParameter(Parameters.NOMBRE);
			TareaCriteria criteria = new TareaCriteria();
			criteria.setNombre(nombre);

			String idStr = request.getParameter("id");
			if(idStr==null || idStr.isEmpty()){
				criteria.setId(null);
			} else {
				Long id = Long.valueOf(idStr);
				criteria.setId(id);
			}

			String descripcion = request.getParameter(Parameters.DESCRIPCION);
			if(descripcion==null || descripcion.isEmpty()){
				criteria.setDescripcion(null);
			} else {
				criteria.setDescripcion(descripcion);
			}

			String proyectoIdStr = request.getParameter(Parameters.PROYECTOID);
			if(proyectoIdStr==null || proyectoIdStr.isEmpty()){
				criteria.setProyectoId(null);
			} else {
				Long proyectoId = Long.valueOf(proyectoIdStr);
				criteria.setProyectoId(proyectoId);
			}
			
			String fechaRealInicioStr = request.getParameter(Parameters.FECHAREALINICIO);
			if(fechaRealInicioStr==null || fechaRealInicioStr.isEmpty()){
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
			if(fechaRealFinStr==null || fechaRealFinStr.isEmpty()){
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
			if(fechaEstimadaFinStr==null || fechaEstimadaFinStr.isEmpty()){
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
			if(fechaEstimadaInicioStr==null || fechaEstimadaInicioStr.isEmpty()){
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
				Results<TareaDTO>resultados = tareaService.findByCriteria(criteria, 1, Integer.MAX_VALUE);			
				logger.info("Encontrados "+resultados.getTotal()+" tareas");

//				request.setAttribute("resultados", resultados);
				request.setAttribute("resultados", resultados.getPage());	
				
				targetView = Views.TAREA_SEARCH;
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
				TareaDTO tarea =tareaService.findById(id);
				request.setAttribute(Attributes.TAREA, tarea);
				
				Results<ComentarioTareaDTO> comentarios = comentario.findByTarea(id, 1, 20);
				request.setAttribute(Attributes.COMENTARIOS, comentarios);
				
				targetView = Views.TAREA_RESULTS;
				forwardOrRedirect = true;
				
			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			} 
			
		} else if (Actions.REGISTRAR.equalsIgnoreCase(action)) {
			try {
				TareaDTO tarea = new TareaDTO();
				String nombre = request.getParameter(Parameters.NOMBRE);
				String descripcion = request.getParameter(Parameters.DESCRIPCION);
				String fechaRealInicioStr = request.getParameter(Parameters.FECHAREALINICIO);
				String fechaRealFinStr  = request.getParameter(Parameters.FECHAREALFIN);
				String fechaEstimadaInicioStr = request.getParameter(Parameters.FECHAESTIMADAINICIO);
				String fechaEstimadaFinStr = request.getParameter(Parameters.FECHAESTIMADAFIN);
				String estadoIdStr = request.getParameter(Parameters.ESTADOID);
				Long estadoId = null;
				if (estadoIdStr != null && !estadoIdStr.isEmpty()) {
				    estadoId = Long.valueOf(estadoIdStr);
				} else {
				    logger.warn("Estado ID não fornecido.");
				    // Trate o caso onde estadoId é necessário, mas não foi fornecido
				}

				String proyectoIdStr = request.getParameter(Parameters.PROYECTOID);
				Long proyectoId = null;
				if (proyectoIdStr != null && !proyectoIdStr.isEmpty()) {
				    proyectoId = Long.valueOf(proyectoIdStr);
				} else {
				    logger.warn("Projeto ID não fornecido.");
				    // Trate o caso onde proyectoId é necessário, mas não foi fornecido
				}

				Date fechaRealInicio = null;
				Date fechaRealFin = null;
				Date fechaEstimadaInicio= null;
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
				
				
				tarea.setNombre(nombre);
				tarea.setDescripcion(descripcion);
				tarea.setFechaRealInicio(fechaRealInicio);
				tarea.setFechaRealFin(fechaRealFin);
				tarea.setFechaEstimadaInicio(fechaEstimadaInicio);
				tarea.setFechaEstimadaFin(fechaEstimadaFin);
				tarea.setEstadoId(estadoId);
				tarea.setProyectoId(proyectoId);

				tareaService.registrar(tarea);
//				Long id = tareaService.create(tarea);

				targetView  = Views.TAREA_CREAR;
				forwardOrRedirect = false;



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
