package com.bruno.training.web.controller;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bruno.org.service.FileService;
import com.bruno.org.service.impl.FileServiceImpl;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;

import config.ConfigurationParametersManager;


@WebServlet("/FileUploadServlet")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1, //1 MB
		maxFileSize = 1024 * 1024 * 10, //10MB
		maxRequestSize = 1024 * 1024 * 100 // 100MB
		)

public class FileUploadServlet extends HttpServlet {
    
	private static final String BASE_PROFILE_IMAGE_PATH = "base.profile.image.path";
	private FileService fileService = null;
	
    public FileUploadServlet() {
        super();
        fileService = new FileServiceImpl();
    }
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clienteIdStr = request.getParameter(Parameters.EMPLEADOID);
		Long clienteId = Long.valueOf(clienteIdStr);
		
		List<File> clienteImages = fileService.getProfileImageByEmpleadoId(clienteId);
		
		String targetView = "/private/user/my-profile.jsp";
		
		Part partFile = request.getPart("file");
		String fileName = "emp1.jpg";
//		ByteArrayOutputStream buffer 
		for(Part part : request.getParts()) {
			part.write(ConfigurationParametersManager.getParameterValue(BASE_PROFILE_IMAGE_PATH)+File.separator+clienteId+File.separator+fileName);
		}
		
		RouterUtils.route(request, response, false, targetView);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
