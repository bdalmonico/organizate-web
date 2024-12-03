package com.bruno.training.web.controller;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bruno.org.service.FileService;
import com.bruno.org.service.impl.FileServiceImpl;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.Views;


@WebServlet("/private/FileUploadServlet")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1, //1 MB
		maxFileSize = 1024 * 1024 * 10, //10MB
		maxRequestSize = 1024 * 1024 * 100 // 100MB
		)

public class FileUploadServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(FileUploadServlet.class);
	private static final String BASE_PROFILEIMAGE_PATH = "base.profile.image.path";
	private FileService fileService = null;
	
    public FileUploadServlet() {
        super();
        fileService = new FileServiceImpl();
    }
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String targetView = null;
		
		String empleadoIdStr = request.getParameter(Parameters.ID);
		Long empleadoId = Long.valueOf(empleadoIdStr);
		

		
		String tmpPath = System.getProperty("java.io.tmpdir")+"/"+empleadoIdStr+"-"+System.currentTimeMillis();
		
		
		
		Part partFile = request.getPart("file");
		 
	 
		for(Part part : request.getParts()) {
			logger.info("Saving file to "+tmpPath);
			part.write(tmpPath);
		}
		
		fileService.saveEmpleadoProfileImage(empleadoId, new File(tmpPath));
		
		targetView = Views.EMPLEADO_DETAIL;
		RouterUtils.route(request, response, false, targetView);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
