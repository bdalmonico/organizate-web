<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div>
	<form action="<%=request.getContextPath()%>/private/ProyectoServlet"
		method="post">


		<h3 class="titulo">
			<fmt:message key="search_project" bundle="${messages}" />
		</h3>

		<input type="hidden" name="<%=Parameters.ACTION%>"value="<%=Actions.SEARCH%>" /> 
		
		<label><fmt:message key="id2p" bundle="${messages}" /></label> 
		
		<input type="number" name="<%=Parameters.ID%>" placeholder="Ejemplo: id del proyecto" /> 
		
		<label><fmt:message key="name2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.NOMBRE%>" placeholder="Ejemplo: Proyecto" /> 
		
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
		
		<label><fmt:message key="client_id2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.CLIENTEID%>" placeholder="Ejemplo: id del cliente" /> 
		
		<label><fmt:message key="status_id2p" bundle="${messages}"/></label>	
		<input type="number" name="<%=Parameters.ESTADOID%>" placeholder="Ejemplo: id del estado" />

		
		<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
	</form>


	<div id="resultados">
		<c:choose>
			<c:when test="${not empty resultados.page}">
				<ul>
					<c:forEach var="p" items="${resultados.page}">
						<li><a
							href="<%=request.getContextPath()%>/private/ProyectoServlet?action=detail&id=${p.id}">
								<c:out value="${p.nombre}" />
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


	<%@include file="/common/footer.jsp"%>