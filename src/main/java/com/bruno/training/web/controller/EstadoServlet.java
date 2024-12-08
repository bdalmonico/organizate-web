package com.bruno.training.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bruno.OrganizateException;
import com.bruno.org.dao.DataException;
import com.bruno.org.model.EstadoDTO;
import com.bruno.org.service.EstadoService;
import com.bruno.org.service.ServiceException;
import com.bruno.org.service.impl.EstadoServiceImpl;
import com.bruno.training.web.util.Actions;
import com.bruno.training.web.util.Attributes;
import com.bruno.training.web.util.Parameters;
import com.bruno.training.web.util.RouterUtils;
import com.bruno.training.web.util.SessionManager;
import com.bruno.training.web.util.Views;

@WebServlet("/private/EstadoServlet")
public class EstadoServlet extends HttpServlet {

	private Logger logger = LogManager.getLogger(EstadoServlet.class);
	private EstadoService estadoService = null;

	public EstadoServlet() {
		super();
		estadoService = new EstadoServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter(Parameters.ACTION);
		String targetView = null;
		boolean forwardOrRedirect = false;

		// Obter o locale da sessão como Locale, e se não existir, usar o padrão
		Locale localeFromSession = (Locale) SessionManager.getAttribute(request, Attributes.LOCALE);
		if (localeFromSession == null) {
			localeFromSession = Locale.ENGLISH; // Defina um valor padrão, por exemplo, Locale.ENGLISH
		}

		// Se o parâmetro locale for fornecido na URL, use-o
		String localeParam = request.getParameter("locale");
		if (localeParam != null && !localeParam.isEmpty()) {
			// Cria um novo Locale a partir da string recebida
			String[] localeParts = localeParam.split("_");
			if (localeParts.length == 1) {
				localeFromSession = new Locale(localeParts[0]);
			} else if (localeParts.length == 2) {
				localeFromSession = new Locale(localeParts[0], localeParts[1]);
			}
			// Atualiza o locale na sessão
			SessionManager.setAttribute(request, Attributes.LOCALE, localeFromSession);
		}

		// Agora, o localeFromSession é um objeto Locale que pode ser utilizado
		// corretamente
		if (Actions.SEARCH.equalsIgnoreCase(action)) {
			EstadoDTO estado = new EstadoDTO();
			String estadoIdStr = request.getParameter(Parameters.ESTADOID);
			if (estadoIdStr == null || estadoIdStr.isEmpty()) {
				estado.setId(null);
			} else {
				Long estadoId = Long.valueOf(estadoIdStr);
				estado.setId(estadoId);
			}

			try {
				// Passando o locale como uma String, com localeFromSession.getLanguage()
				EstadoDTO resultados = estadoService.findById(estado.getId(), localeFromSession.getLanguage());
				logger.info("Encontrados " + resultados.getNombre() + " comentarios");

				request.setAttribute(Attributes.RESULTADOS, resultados);
				targetView = Views.ESTADO_SEARCH;
				forwardOrRedirect = true;

			} catch (OrganizateException pe) {
				logger.error(pe.getMessage(), pe);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else if (Actions.ALL.equalsIgnoreCase(action)) {
			List<EstadoDTO> resultados;
			try {
				resultados = estadoService.findAll(localeFromSession.getLanguage());
				if (resultados != null && !resultados.isEmpty()) {
					request.setAttribute(Attributes.RESULTADOS, resultados);
				}
				targetView = Views.ESTADO_SEARCHALL;
				forwardOrRedirect = true;
			} catch (DataException | ServiceException e) {
				logger.error(e.getMessage(), e);
			}
		} else if (Actions.DETAILAll.equalsIgnoreCase(action)) {
			try {

				String idStr = request.getParameter(Parameters.ESTADOID);
				Long id = Long.valueOf(idStr);
				EstadoDTO estado = (EstadoDTO) estadoService.findAll(localeFromSession.getLanguage());
				request.setAttribute(Attributes.ESTADO, estado);
				targetView = Views.ESTADO_DETAIL;
				forwardOrRedirect = true;
			} catch (DataException | ServiceException e) {
				logger.error(e.getMessage(), e);
			}

		}
		RouterUtils.route(request, response, forwardOrRedirect, targetView);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
