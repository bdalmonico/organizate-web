package com.bruno.training.web.controller;

import java.io.IOException;

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

/**
 * Servlet para peticione sosbre tareas
 */
//@SuppressWarnings("serial")
@WebServlet("/TareaServlet")
public class TareaServlet extends HttpServlet {
	
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
			// Otros parametros de busqueda ...

			TareaCriteria criteria = new TareaCriteria();
			criteria.setNombre(nombre);

			try {
				Results<TareaDTO>resultados = tareaService.findByCriteria(criteria, 1, 20);			
				logger.info("Encontrados "+resultados.getTotal()+" tareas");

				request.setAttribute("resultados", resultados);			
				
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
			
		}
		RouterUtils.route(request, response, forwardOrRedirect, targetView);

	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
