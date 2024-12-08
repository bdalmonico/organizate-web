<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div>
	<form action="<%=request.getContextPath()%>/private/ImputacionServlet" method="post">
		<h3 class="titulo">
			<fmt:message key="search_imputation" bundle="${messages}" />
		</h3>

		<input type="hidden" name="<%=Parameters.ACTION%>"value="<%=Actions.SEARCH%>" /> 
		
		<label><fmt:message key="project_id2p" bundle="${messages}" /></label> 
		<input type="number" name="<%=Parameters.PROYECTOID %>"  value="${proyectoId}" /> 
		
		<label><fmt:message key="task_id2p" bundle="${messages}" /></label> 
		<input type="number" name="<%=Parameters.TAREAID %>"  value="${tareaId}" />
		 
		<label><fmt:message key="employee_id2p" bundle="${messages}" /></label> 
		<input type="number" name="<%=Parameters.EMPLEADOID %>"  value="${empleadoId}" /> 
		
		<label><fmt:message key="date_2p" bundle="${messages}" /></label> 
		<input type="date" name="<%=Parameters.FECHAHORA %>"  value="${fechaHora}" />
		
		<label><fmt:message key="comment_2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.COMENTARIO %>"  value="${comentario}" /> 

		<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
	</form>

	<div id="resultados">
		<c:choose>
			<c:when test="${not empty resultados.page}">
				<ul>
					<c:forEach var="c" items="${resultados.page}">
						<li>
							<a href="<%=request.getContextPath()%>/private/ImputacionServlet?action=detail&id=${c.id}"></a>  	
							<p><fmt:message key="id2p" bundle="${messages}"/><c:out value="${c.id}" /></p>
						  	<p><fmt:message key="project_id2p" bundle="${messages}"/><c:out value="${c.proyectoId}" /></p>
							<p><fmt:message key="task_id2p" bundle="${messages}"/><c:out value="${c.tareaId}" /></p>
							<p><fmt:message key="employee_id2p" bundle="${messages}"/><c:out value="${c.empleadoId}" /></p>
							<p><fmt:message key="imputed_hours_2p" bundle="${messages}"/><c:out value="${c.horasImputadas}"/></p>
							<p><fmt:message key="comment_2p" bundle="${messages}"/><c:out value="${c.comentario}"/></p>
						</li>
					</c:forEach>
				</ul>
				<%@include file="/common/paging.jsp"%>
			</c:when>
			<c:otherwise>
				<h1>
					<fmt:message key="without_results" bundle="${messages}" />
					.
				</h1>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<%@include file="/common/footer.jsp"%>