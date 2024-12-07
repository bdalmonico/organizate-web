package com.bruno.training.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bruno.org.service.FileService;
import com.bruno.org.service.impl.FileServiceImpl;
import com.bruno.training.web.util.Parameters;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {

	private FileService fileService;

	public ImageServlet() {
		super();
		fileService = new FileServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		FileInputStream fileInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		
		if ("profileImage".equalsIgnoreCase(action)) {

			String imageName = request.getParameter("imageName");

			String empleadoIdStr = request.getParameter("id");
			
			if (empleadoIdStr=="") {
				return;
			}
			
			Long empleadoId = Long.valueOf(empleadoIdStr);
			List<File> images = fileService.getProfileImageByEmpleadoId(empleadoId);

			if (images.isEmpty()) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "NO image found for the given empleadoId");
			} else {

				response.setContentType("image/jpg");
				response.setHeader("Content-Disposition", "inline; filename=hola.png");

				try (OutputStream out = response.getOutputStream()) {
					if (images.get(0).getName().equalsIgnoreCase(imageName)) {
						FileInputStream fis = new FileInputStream(images.get(0));
							
						// Use ByteArrayOutputStream to collect the bytes
						byteArrayOutputStream = new ByteArrayOutputStream();

						int byteRead;
						// Read byte by byte from the FileInputStream and write to ByteArrayOutputStream
						while ((byteRead = fis.read()) != -1) {
							byteArrayOutputStream.write(byteRead);
						}

						// Convert the collected bytes to a byte array
						byte[] byteArray = byteArrayOutputStream.toByteArray();

						out.write(byteArray);
						out.flush();
					}

				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

//public class ImageServlet extends HttpServlet {
//
//    private FileService fileService;
//
//    public ImageServlet() {
//        super();
//        fileService = new FileServiceImpl();
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String action = request.getParameter("action");
//
//        if ("profileImage".equalsIgnoreCase(action)) {
//
//            String imageName = request.getParameter("imageName");
//
//            String empleadoIdStr = request.getParameter(Parameters.ID);
//            Long empleadoId = Long.valueOf(empleadoIdStr);
//
//            List<File> images = fileService.getProfileImageByEmpleadoId(empleadoId);
//
//            if (images.isEmpty()) {
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "NO image found for the given empleadoId");
//            } else {
//
//                response.setContentType("image/jpg");
//                response.setHeader("Content-Disposition", "inline; filename=hola.png");
//
//                try (OutputStream out = response.getOutputStream()) {
//                    for (File image : images) {
//                        if (image.getName().equalsIgnoreCase(imageName)) {
//                            try (FileInputStream fis = new FileInputStream(image)) {
//                                // Leitura em chunks
//                                byte[] buffer = new byte[8192];
//                                int bytesRead;
//
//                                // Escreve os bytes no OutputStream
//                                while ((bytesRead = fis.read(buffer)) != -1) {
//                                    out.write(buffer, 0, bytesRead);
//                                }
//
//                                out.flush();
//                            }
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }
//}
