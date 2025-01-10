package com.bruno.training.web.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bruno.org.model.ProyectoCriteria;
import com.bruno.org.model.ProyectoDTO;
import com.bruno.org.model.Results;
import com.bruno.org.service.ProyectoService;
import com.bruno.org.service.impl.ProyectoServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class ProyectoWS
 */
@WebServlet("/proyecto")
public class ProyectoWS extends HttpServlet {
	
	private static Logger logger = LogManager.getLogger(ProyectoWS.class);
	
	private static Gson gson= new Gson();
	
	private ProyectoService service =null;
    
	public ProyectoWS() {
        super();
        service = new ProyectoServiceImpl();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String methodStr = request.getParameter("method");
		
		String nombreStr = request.getParameter("nombre");
		
		try {
			
		ProyectoDTO proyecto = new ProyectoDTO();
		
		ProyectoCriteria criteria= new ProyectoCriteria();
		
		criteria.setNombre(nombreStr);
				
		Results<ProyectoDTO> results = service.findByCriteria(criteria, 1, 10);
		
		String resultsJSON = gson.toJson(results);
		
		response.setContentType("application/json");
		
		
		
		response.getOutputStream().write(resultsJSON.getBytes());
		response.getOutputStream().flush();
		
		
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		} 
		response.getOutputStream().print("<h1>Hola</h1>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
