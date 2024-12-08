<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
 
<div style="margin:30px">
	<h2><fmt:message key="name2p" bundle="${messages}"/>${tarea.nombre}</h2>
	<h4><fmt:message key="description2p" bundle="${messages}"/>${tarea.descripcion}</h4>
	<h5><fmt:message key="real_start_date2p" bundle="${messages}"/>${tarea.fechaRealInicio}  ---> Fecha real de fin: ${tarea.fechaRealFin}</h5>
	<h5><fmt:message key="real_end_date2p" bundle="${messages}"/>: ${tarea.fechaEstimadaInicio} ---> Fecha estimada de fin: ${tarea.fechaEstimadaFin}</h5>
	
	<p>
		<b><fmt:message key="state" bundle="${messages}"/></b> 
		
		<c:choose>
		 <c:when test="${tarea.estadoId == 1}"><fmt:message key="state_open" bundle="${messages}"/></c:when>
		 <c:when test="${tarea.estadoId == 2}"><fmt:message key="state_conclude" bundle="${messages}"/></c:when>
		 <c:when test="${tarea.estadoId == 3}"><fmt:message key="state_working" bundle="${messages}"/></c:when>
		 <c:when test="${tarea.estadoId == 4}"><fmt:message key="state_aproved" bundle="${messages}"/></c:when>
		 <c:when test="${tarea.estadoId == 5}"><fmt:message key="state_prototype" bundle="${messages}"/></c:when>
		 <c:when test="${tarea.estadoId == 6}"><fmt:message key="state_active" bundle="${messages}"/></c:when>
		 <c:when test="${tarea.estadoId == 7}"><fmt:message key="state_inactive" bundle="${messages}"/></c:when>
		 <c:when test="${tarea.estadoId == 8}"><fmt:message key="state_development" bundle="${messages}"/></c:when>
		 <c:otherwise>Unknown Status</c:otherwise>
		</c:choose>
	</p>
	
	<h4>
		<a href="<%=request.getContextPath()%>/private/ProyectoServlet?action=detail&id=${tarea.proyectoId}">
			<fmt:message key="project" bundle="${messages}"/>
		</a>
	</h4>
	
	<h4>
		<a href="<%=request.getContextPath()%>/private/TareaServlet?action=delete&id=${tarea.id}">
			<fmt:message key="delete_task" bundle="${messages}"/>
			 ${tarea.id}
		 </a>
	 </h4>
	 
	<a href="<%=request.getContextPath()%><%=Views.TAREA_UPDATE%>"><fmt:message key="update_task" bundle="${messages}" /></a>
	
	<hr>
	
	<div id="resultados">
		
		<% Double totalHoras = (Double) request.getAttribute(Attributes.RESULTADOS);
			if (totalHoras != null) {
		%>
				<p>
				    <strong>Total de horas imputadas na tarefa:</strong> <%= String.format("%.2f", totalHoras) %>
				</p>
		<%
			} else {
		%>
				<h1>
				    <fmt:message key="without_results" bundle="${messages}" />
				</h1>
		<%
			}
		%>
		
	</div>
	
	<hr>
	
	<form action="<%=request.getContextPath()%>/private/ImputacionServlet" method="post">
	
		<h3><fmt:message key="hour_imputation" bundle="${messages}"/></h3>
		
		<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.CREATE%>" />
		
		<label><fmt:message key="employee_id2p" bundle="${messages}"/></label>
		<input type="number" name="<%=Parameters.EMPLEADOID%>" value="${sessionScope.empleado.id}"/>
		
		<label><fmt:message key="task_id2p" bundle="${messages}"/></label> 
		<input type="number" name="<%=Parameters.TAREAID %>" value="${tarea.id}" />
		
		<label><fmt:message key="project_id2p" bundle="${messages}"/></label> 
		<input type="number" name="<%=Parameters.PROYECTOID %>" value="${tarea.proyectoId}" />
		
		<br>
		
		<label><fmt:message key="hours_2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.HORASIMPUTADAS %>" />
		
		<label><fmt:message key="publish_date2p" bundle="${messages}"/></label> 
		<input type="date" name="<%=Parameters.FECHAHORA%>"/>
		
		<label><fmt:message key="comment_2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.COMENTARIO%>" />
		
		<input type="submit" value="<fmt:message key="create" bundle="${messages}"/>" />
	</form>
	
	<hr>
		
	<h3><fmt:message key="employees" bundle="${messages}"/></h3>
	
	<c:forEach var="c" items="${empleados.page}">
		<span>
			<fmt:message key="id2p" bundle="${messages}"/>
			<c:out value="${c.empleadoId}"/> 
		</span>
		<span>
			<a href="<%=request.getContextPath()%>/private/EmpleadoTareaServlet?action=delete&empleadoId=${c.empleadoId}&tareaId=${c.tareaId}">
				<fmt:message key="remove" bundle="${messages}"/>
			</a> 
			|
		</span>
		
	</c:forEach>
	<hr> 
	
	
	<form action="<%=request.getContextPath()%>/private/EmpleadoTareaServlet" method="post">
	
		<h3>Atribuir empleado</h3>
		
		<input type="hidden" name="<%=Parameters.ACTION%>" value="<%=Actions.CREATE%>" />
		
		<label><fmt:message key="employee_id2p" bundle="${messages}"/></label>
		<input type="number" name="<%=Parameters.EMPLEADOID%>"/>
		
		<label><fmt:message key="task_id2p" bundle="${messages}"/></label> 
		<input type="number" name="<%=Parameters.TAREAID %>" value="${tarea.id}" />
		
		<input type="submit" value="<fmt:message key="create" bundle="${messages}"/>" />
		
	</form>
	<hr>
	
		
	<form action="<%=request.getContextPath()%>/private/ComentarioTareaServlet" method="post">
	
		<h3><fmt:message key="create_task_comment" bundle="${messages}"/></h3>
		
		<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.CREATE%>" />
		
		<label><fmt:message key="comment_2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.COMENTARIO %>" />
		
		
		<label><fmt:message key="employee_id2p" bundle="${messages}"/></label>
		<input type="number" name="<%=Parameters.EMPLEADOID%>" value="${sessionScope.empleado.id}"/>
		
		<label><fmt:message key="task_id2p" bundle="${messages}"/></label> 
		<input type="number" name="<%=Parameters.TAREAID %>" value="${tarea.id}" />
		
		<label><fmt:message key="publish_date2p" bundle="${messages}"/></label> 
		<input type="date" name="<%=Parameters.FECHAPUBLICACION %>"/>
		
		<input type="submit" value="<fmt:message key="create" bundle="${messages}"/>" />
	</form>
	
	<hr>
	
	<h3><fmt:message key="comments" bundle="${messages}"/></h3>
	
	<c:forEach var="c" items="${comentarios.page}">
		<div>
			<p>Comment ID:<c:out value="${c.id}"/></p>
			<p><c:out value="${c.comentario}"/></p>
			<p><c:out value="${c.fechaHora}"/></p>
			<a href="<%=request.getContextPath()%><%=Views.COMENTARIOPROYECTO_UPDATE%>">
				<fmt:message key="update_comment" bundle="${messages}"/>
			</a>
			<h4>
				<a href="<%=request.getContextPath()%>/private/ComentarioTareaServlet?action=delete&id=${c.id}">
					<fmt:message key="delete_comment" bundle="${messages}"/> 
					${c.id}
				</a>
			</h4>
			<hr>
		</div>
	</c:forEach>
	
</div>
<%@include file="/common/footer.jsp" %>