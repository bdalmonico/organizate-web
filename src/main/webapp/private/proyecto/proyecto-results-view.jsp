<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
	
	<% ProyectoDTO proyecto = (ProyectoDTO) request.getAttribute(Attributes.PROYECTO);%>
	<ul>
		<h2>Nombre del proyecto: <%=proyecto.getNombre() %></h2>
		<h3>Descripcion del proyecto: <%=proyecto.getDescripcion()%></h3>
		<h4>Fecha estimada de inicio: <%=proyecto.getFechaEstimadaInicio()%></h4>
		<h4>Fecha estimada de fin: <%=proyecto.getFechaEstimadaFin()%></h4>
	<p>___________________________________</p>
	

<%@include file="/common/footer.jsp" %>