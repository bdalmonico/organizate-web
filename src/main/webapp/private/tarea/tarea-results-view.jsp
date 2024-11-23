<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
	
	
	<ul>
		<h2><fmt:message key="name2p" bundle="${messages}"/>${tarea.nombre}</h2>
		<h4><fmt:message key="description2p" bundle="${messages}"/>${tarea.descripcion}</h3>
		<h5><fmt:message key="real_start_date2p" bundle="${messages}"/>${tarea.fechaRealInicio}  ---> Fecha real de fin: ${tarea.fechaRealFin}</h5>
		<h5><fmt:message key="real_end_date2p" bundle="${messages}"/>: ${tarea.fechaEstimadaInicio} ---> Fecha estimada de fin: ${tarea.fechaEstimadaFin}</h5>
		<h4><a href="<%=request.getContextPath()%>/private/ProyectoServlet?action=detail&id=${tarea.proyectoId}"><fmt:message key="project_id2p" bundle="${messages}"/>${tarea.proyectoId}</a></h3>
		<h6><a href="<%=request.getContextPath()%>/private/TareaServlet?action=delete&id=${tarea.id}"><fmt:message key="delete_task" bundle="${messages}"/> ${tarea.id}</a></h6>
		
	</ul>
	
	
	<p>___________________________________</p>
	<c:forEach var="c" items="${comentarios.page}">
		<div>
			<p><c:out value="${c.comentario}"/></p>
			
			<p><c:out value="${c.fechaHora}"/></p>
			<p>__</p>
		</div>
	</c:forEach>
	<p>___________________________________</p>
<%@include file="/common/footer.jsp" %>