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
import com.bruno.org.model.ClienteCriteria;
import com.bruno.org.model.ClienteDTO;
import com.bruno.org.model.Results;
import com.bruno.org.service.ClienteService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.ClienteServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.Views;

@WebServlet("/private/ClienteServlet")
public class ClienteServlet extends HttpServlet {

	private static SimpleDateFormat FECHA_OF = new SimpleDateFormat("yyyy-MM-dd");
	private Logger logger = LogManager.getLogger(ClienteServlet.class);
	private ClienteService clienteService = null;

	public ClienteServlet() {
		super();
		clienteService = new ClienteServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = false;

		if (Actions.SEARCH.equalsIgnoreCase(action)) {

			String idStr = request.getParameter(Parameters.ID);

			String nombre = request.getParameter(Parameters.NOMBRE);

			String email = request.getParameter(Parameters.EMAIL);

			String estadoIdStr = request.getParameter(Parameters.ESTADOID);

			String nifCif = request.getParameter(Parameters.NIFCIF);

			String telefono = request.getParameter(Parameters.TELEFONO);
			
			ClienteCriteria criteria = new ClienteCriteria();

			if (idStr == null || idStr.isEmpty()) {
				criteria.setId(null);
			} else {
				Long id = Long.valueOf(idStr);
				criteria.setId(id);
			}

			if (estadoIdStr == null || estadoIdStr.isEmpty()) {
				criteria.setId(null);
			} else {
				Long estadoId = Long.valueOf(estadoIdStr);
				criteria.setEstadoId(estadoId);
			}

			criteria.setNombre(nombre);
			criteria.setEmail(email);
			criteria.setNifCif(nifCif);
			criteria.setTelefone(telefono);

			try {

				Results<ClienteDTO> resultados = clienteService.findByCriteria(criteria, 1, 20);
//				logger.info("Encontrados " + resultados.getTotal() + " clientes");

				request.setAttribute(Attributes.RESULTADOS, resultados);

				targetView = Views.CLIENTE_SEARCH;
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
				ClienteDTO cliente = clienteService.findById(id);
				request.setAttribute(Attributes.CLIENTE, cliente);

				targetView = Views.CLIENTE_RESULTS;
				forwardOrRedirect = true;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}

		} else if (Actions.CREATE.equalsIgnoreCase(action)) {
			try {
				ClienteDTO cliente = new ClienteDTO();

				String nombre = request.getParameter(Parameters.NOMBRE);

				String email = request.getParameter(Parameters.EMAIL);

				String estadoIdStr = request.getParameter(Parameters.ESTADOID);

				String nifCif = request.getParameter(Parameters.NIFCIF);

				String telefono = request.getParameter(Parameters.TELEFONO);

				cliente.setNombre(nombre);

				cliente.setEmail(email);

				Long estadoId = null;
				if (estadoIdStr != null && !estadoIdStr.isEmpty()) {
					estadoId = Long.valueOf(estadoIdStr);
					cliente.setEstadoId(estadoId);
				} else {
					logger.warn("Cliente ID não fornecido.");
					// Trate o caso onde clienteId é necessário, mas não foi fornecido
				}

				cliente.setNifCif(nifCif);

				cliente.setTelefone(telefono);

				cliente.setEstadoId(estadoId);

				clienteService.registrar(cliente);

				targetView = Views.CLIENTE_CREAR;
				forwardOrRedirect = true;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}
		} else if (Actions.UPDATE.equalsIgnoreCase(action)) {
			try {
				ClienteDTO cliente = new ClienteDTO();

				String idStr = request.getParameter(Parameters.ID);

				String nombre = request.getParameter(Parameters.NOMBRE);

				String email = request.getParameter(Parameters.EMAIL);

				String estadoIdStr = request.getParameter(Parameters.ESTADOID);

				String nifCif = request.getParameter(Parameters.NIFCIF);

				String telefono = request.getParameter(Parameters.TELEFONO);

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
				cliente.setId(id);
				cliente.setNombre(nombre);
				cliente.setEstadoId(estadoId);
				cliente.setEmail(email);
				cliente.setNifCif(nifCif);
				cliente.setTelefone(telefono);

				clienteService.update(cliente);

				targetView = Views.CLIENTE_UPDATE;
				forwardOrRedirect = false;

			} catch (OrganizateException | ServiceException pe) {
				logger.error(pe.getMessage(), pe);
			}

		} else if (Actions.DELETE.equalsIgnoreCase(action)) {
			try {
				String idStr = request.getParameter(Parameters.ID);
				Long id = Long.valueOf(idStr);

				clienteService.delete(id);

				targetView = Views.CLIENTE_DELETE;
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
