<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div>
	<form action="<%=request.getContextPath()%>/private/EmpleadoTareaServlet" method="post">
		<h3 class="titulo">
			<fmt:message key="sarch_employee_tasks" bundle="${messages}" />
		</h3>
		<input type="hidden" name="<%=Parameters.ACTION%>"value="<%=Actions.SEARCHTAREAS%>" /> 
		
		<label><fmt:message key="name2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.EMPLEADOID %>" placeholder="Ejemplo: id de empleado" value="${empleadoId}" /> 
		<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
	</form>
	<div id="resultados">
		<c:choose>
			<c:when test="${not empty resultados.page}">
				<ul>
					<c:forEach var="c" items="${resultados.page}">
						<li><a
							href="<%=request.getContextPath()%>/private/EmpleadoTareaServlet?action=detail&empleadoId=${c.empleadoId}&tareaId=${c.tareaId}">
								EmpleadoID: <c:out value="${c.empleadoId}" />
								TareaID: <c:out value="${c.tareaId}" />
						</a></li>
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