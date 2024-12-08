<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
	<div style="margin:30px">
		<% ProyectoDTO proyecto = (ProyectoDTO) request.getAttribute(Attributes.PROYECTO);%>
		<h2><%=proyecto.getNombre() %></h2>
		<h3><fmt:message key="description2p" bundle="${messages}"/> <%=proyecto.getDescripcion()%></h3>
		<h4><fmt:message key="start_date2p" bundle="${messages}"/> <%=proyecto.getFechaEstimadaInicio()%></h4>
		<h4><fmt:message key="end_date2p" bundle="${messages}"/><%=proyecto.getFechaEstimadaFin()%></h4>
		<h4><a href="<%=request.getContextPath()%>/private/ClienteServlet?action=detail&id=${proyecto.clienteId}"><fmt:message key="client" bundle="${messages}"/></a></h4>
		<h6><a href="<%=request.getContextPath()%>/private/ProyectoServlet?action=delete&id=${proyecto.id}"><fmt:message key="delete_project" bundle="${messages}"/></a></h6>
		<a href="<%=request.getContextPath()%><%=Views.PROYECTO_UPDATE%>"><fmt:message key="update_project" bundle="${messages}" /></a>
		
		<hr>
		
		<div id="resultados">
			<% Double totalHoras = (Double) request.getAttribute(Attributes.RESULTADOS);
				if (totalHoras != null) {
			%>
			<p>
				<strong>Total de horas imputadas no projeto:</strong> <%= String.format("%.2f", totalHoras) %>
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
		   
		<form action="<%=request.getContextPath()%>/private/ComentarioProyectoServlet" method="post">
			<h3><fmt:message key="create_project_comment" bundle="${messages}"/></h3>
			
			<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.CREATE%>" />
			
			<label><fmt:message key="comment_2p" bundle="${messages}"/></label> 
			<input type="text" name="<%=Parameters.COMENTARIO %>" />
			
			<label><fmt:message key="employee_id2p" bundle="${messages}"/></label>
			<input type="number" name="<%=Parameters.EMPLEADOID%>" value="${sessionScope.empleado.id}"/>
			
			<label><fmt:message key="project_id2p" bundle="${messages}"/></label> 
			<input type="number" name="<%=Parameters.PROYECTOID %>" value="${proyecto.id}" />
			
			<label><fmt:message key="publish_date2p" bundle="${messages}"/></label> 
			<input type="date" name="<%=Parameters.FECHAPUBLICACION %>"/>
			
			<input type="submit" value="<fmt:message key="create" bundle="${messages}"/>" />
		</form>
		
		<hr>
		
		<h1><fmt:message key="comment" bundle="${messages}"/></h1>
		
		<c:forEach var="c" items="${comentarios.page}">
			<div>
				<p>
					<c:out value="${c.comentario}"/>
				</p>
				<p>
					<c:out value="${c.fechaHora}"/>
				</p>
				<hr>
			</div>
		</c:forEach>
	</div>
<%@include file="/common/footer.jsp" %>