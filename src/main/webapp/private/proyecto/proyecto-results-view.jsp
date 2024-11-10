<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
	
	<% ProyectoDTO proyecto = (ProyectoDTO) request.getAttribute(Attributes.PROYECTO);%>
	<ul>
		<h2><fmt:message key="name2p" bundle="${messages}"/> <%=proyecto.getNombre() %></h2>
		<h3><fmt:message key="description2p" bundle="${messages}"/> <%=proyecto.getDescripcion()%></h3>
		<h4><fmt:message key="start_date2p" bundle="${messages}"/> <%=proyecto.getFechaEstimadaInicio()%></h4>
		<h4><fmt:message key="end_date2p" bundle="${messages}"/><%=proyecto.getFechaEstimadaFin()%></h4>
	<p>___________________________________</p>
	

<%@include file="/common/footer.jsp" %>