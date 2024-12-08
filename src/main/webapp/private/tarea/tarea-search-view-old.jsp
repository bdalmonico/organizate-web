<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="column">
	
	<form action="<%=request.getContextPath()%>/private/TareaServlet" method="post">
		<h3 class="titulo">	<fmt:message key="search_task" bundle="${messages}" />	</h3>
		<input type="hidden" name="<%=Parameters.ACTION%>" value="<%=Actions.SEARCH%>" /> 
		<label><fmt:message key="name2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.NOMBRE%>" placeholder="Ejemplo: tarea" /> 
		<label><fmt:message key="description2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.DESCRIPCION%>" placeholder="Ejemplo: descripcion" /> 
		<label><fmt:message key="start_date2p" bundle="${messages}" /></label> 
		<input type="date" name="<%=Parameters.FECHAESTIMADAINICIO%>" placeholder="Ejemplo: fecha estimada inicio" /> 
		<label><fmt:message key="end_date2p" bundle="${messages}" /></label> 
		<input type="date" name="<%=Parameters.FECHAESTIMADAFIN%>" placeholder="Ejemplo: fecha estimada fin" /> 
		<label><fmt:message key="real_start_date2p" bundle="${messages}" /></label> 
		<input type="date" name="<%=Parameters.FECHAREALINICIO%>" placeholder="Ejemplo: fecha real inicio" /> 
		<label><fmt:message	key="real_end_date2p" bundle="${messages}" /></label> 
		<input type="date" name="<%=Parameters.FECHAREALFIN%>" placeholder="Ejemplo: fecha real fin" /> 
		<label><fmt:message key="task_id2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.ID%>" placeholder="Ejemplo: id de la tarea" /> 
		<label><fmt:message key="project_id2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.PROYECTOID%>" placeholder="Ejemplo: id del proyecto" /> 
		<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
	</form>

	<div id="resultados">
		<c:choose>
			<c:when test="${not empty resultados.page}">
				<ul>
					<c:forEach var="t" items="${resultados.page}">
						<li>
							
								<h2>
									<a href="<%=request.getContextPath()%>/private/TareaServlet?action=detail&id=${t.id}">
									<c:out value="${t.nombre}" /> | <c:out value="${t.fechaRealInicio}" /></a>
								</h2>
					
							 
							 <ol>
								<c:out value="${t.descripcion}" />
							</ol>
							<ol>
								<a
									href="<%=request.getContextPath()%>/private/ProyectoServlet?action=detail&id=<c:out value="${t.proyectoId}"/>">Proyecto
									id:<c:out value="${t.proyectoId}" />
	
								</a>
							</ol>
						</li>
						<hr>
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