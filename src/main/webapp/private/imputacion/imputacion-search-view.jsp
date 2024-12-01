<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div>
	<form action="<%=request.getContextPath()%>/private/ImputacionServlet" method="post">


		<h3 class="titulo">
			<fmt:message key="search_client" bundle="${messages}" />
		</h3>

		<input type="hidden" name="<%=Parameters.ACTION%>"value="<%=Actions.SEARCH%>" /> 
		
		<label><fmt:message key="name2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.ID %>" placeholder="Ejemplo: id " value="${id}" /> 
		<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
	</form>


	<div id="resultados">
		<c:choose>
			<c:when test="${not empty resultados.page}">
				<ul>
					<c:forEach var="c" items="${resultados.page}">
						<li><a
							href="<%=request.getContextPath()%>/private/ImputacionServlet?action=detail&id=${c.id}">
								<c:out value="${c.comentario}" />
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