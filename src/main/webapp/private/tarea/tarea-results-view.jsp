<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
	
	<% TareaDTO tarea = (TareaDTO) request.getAttribute(Attributes.TAREA);%>
	<ul>
		<h2><fmt:message key="name2p" bundle="${messages}"/> <%=tarea.getNombre() %></h2>
		<h4><fmt:message key="description2p" bundle="${messages}"/> <%=tarea.getDescripcion()%></h3>
		<h5><fmt:message key="real_start_date2p" bundle="${messages}"/> <%=tarea.getFechaRealInicio()%> ---> Fecha real de fin: <%=tarea.getFechaRealFin()%></h5>
		<h5><fmt:message key="real_end_date2p" bundle="${messages}"/>: <%=tarea.getFechaEstimadaInicio()%> ---> Fecha estimada de fin: <%=tarea.getFechaEstimadaFin()%></h5>
		<h4><a href="/HelloWorldWeb/private/ProyectoServlet?action=detail&id=${tarea.proyectoId}"><fmt:message key="project_id2p" bundle="${messages}"/> <%=tarea.getProyectoId()%></a></h3>
	</ul>
	
	<p>___________________________________</p>
	

<%@include file="/common/footer.jsp" %>