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
import com.bruno.org.model.Results;
import com.bruno.org.model.TareaCriteria;
import com.bruno.org.model.TareaDTO;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.TareaService;
import com.bruno.org.service.impl.TareaServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.Views;


@WebServlet("/private/TareaServlet")
public class TareaServlet extends HttpServlet {
	
	private static SimpleDateFormat FECHA_OF = new SimpleDateFormat("dd/MM/YYYY");
	private Logger logger = LogManager.getLogger(TareaServlet.class);
	private TareaService tareaService = null;

	public TareaServlet() {
		super();
		tareaService = new TareaServiceImpl();

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

			String descripcion = request.getParameter("descripcion");
			if(descripcion==null || descripcion.isEmpty()){
				criteria.setDescripcion(null);
			} else {
				criteria.setDescripcion(descripcion);
			}

			String proyectoIdStr = request.getParameter("proyectoId");
			if(proyectoIdStr==null || proyectoIdStr.isEmpty()){
				criteria.setProyectoId(null);
			} else {
				Long proyectoId = Long.valueOf(proyectoIdStr);
				criteria.setProyectoId(proyectoId);
			}
			
			String fechaRealInicioStr = request.getParameter("fechaRealInicio");
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
			
			String fechaRealFinStr = request.getParameter("fechaRealFin");
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

			String fechaEstimadaFinStr = request.getParameter("fechaEstimadaFin");
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
			
			String fechaEstimadaInicioStr = request.getParameter("fechaEstimadaInicio");
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
				Results<TareaDTO>resultados = tareaService.findByCriteria(criteria, 1, 20);			
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
				String idStr = request.getParameter("id");
				Long id = Long.valueOf(idStr);
				
				TareaDTO tarea =tareaService.findById(id);
				request.setAttribute("tarea", tarea);
				
				targetView = Views.TAREA_RESULTS;
				forwardOrRedirect = true;
				
			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			} 
			
		} else if ("create".equalsIgnoreCase(action)) {
			try {
				TareaDTO tarea = new TareaDTO();
				String nombre = request.getParameter("nombre");
				String descripcion = request.getParameter("descripcion");

				tarea.setNombre(nombre);
				tarea.setDescripcion(descripcion);

				tareaService.registrar(tarea);
//				Long id = tareaService.create(tarea);

				targetView  = Views.TAREA_CREAR;
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
