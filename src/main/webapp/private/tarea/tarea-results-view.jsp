<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
	
	<% TareaDTO tarea = (TareaDTO) request.getAttribute(Attributes.TAREA);%>
	<ul>
		<h2>Nombre de la tarea: <%=tarea.getNombre() %></h2>
		<h4>Descripcion de la tarea: <%=tarea.getDescripcion()%></h3>
		<h5>Fecha real de inicio: <%=tarea.getFechaRealInicio()%></h5>
		<h5>Fecha real de fin: <%=tarea.getFechaRealFin()%></h5>
		<h5>Fecha estimada de inicio: <%=tarea.getFechaEstimadaInicio()%></h5>
		<h5>Fecha estimada de fin: <%=tarea.getFechaEstimadaFin()%></h5>
	</ul>
	
	<p>___________________________________</p>
	

<%@include file="/common/footer.jsp" %>