<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div>
	<form action="${pageContext.request.contextPath}/private/ClienteServlet" method="post">

		<h3 class="titulo">
			<fmt:message key="search_client" bundle="${messages}" />
		</h3>

		<input type="hidden" name="<%=Parameters.ACTION%>"value="<%=Actions.SEARCH%>" /> 
		
		<label><fmt:message key="name2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.NOMBRE %>" placeholder="Ejemplo: clientes" value="${nombre}" /> 
		
		<label><fmt:message key="nif_cif2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.NIFCIF %>"/>
		
		<label><fmt:message key="email2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.EMAIL%>" />		
		
		<label><fmt:message key="phone2p" bundle="${messages}"/></label>
		<input type="text" name="<%=Parameters.TELEFONE %>" />
		
		<label><fmt:message key="status_id2p" bundle="${messages}"/></label> 
		<input type="number" name="<%=Parameters.ESTADOID %>"  />
		
		<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
	</form>


	<div id="resultados">
		<c:choose>
			<c:when test="${not empty resultados.page}">
				<ul>
					<c:forEach var="c" items="${resultados.page}">
						<li>
							<a href="${pageContext.request.contextPath}/private/ClienteServlet?action=detail&id=${c.id}">
								<c:out value="${c.nombre}" /></a>
								<br/>
							<span> <fmt:message key="phone2p" bundle="${messages}"/> <c:out value="${c.telefone}" /> | 
							<fmt:message key="email2p" bundle="${messages}"/>  <c:out value="${c.email}" /> | 
							<fmt:message key="nif_cif2p" bundle="${messages}"/> <c:out value="${c.nifCif}" /> | 
							<fmt:message key="status_id2p" bundle="${messages}"/>
				            <c:choose>
				                <c:when test="${c.estadoId == 1}"><fmt:message key="state_open" bundle="${messages}"/></c:when>
				                <c:when test="${c.estadoId == 2}"><fmt:message key="state_conclude" bundle="${messages}"/></c:when>
				                <c:when test="${c.estadoId == 3}"><fmt:message key="state_working" bundle="${messages}"/></c:when>
				                <c:when test="${c.estadoId == 4}"><fmt:message key="state_aproved" bundle="${messages}"/></c:when>
				                <c:when test="${c.estadoId == 5}"><fmt:message key="state_prototype" bundle="${messages}"/></c:when>
				                <c:when test="${c.estadoId == 6}"><fmt:message key="state_active" bundle="${messages}"/></c:when>
				                <c:when test="${c.estadoId == 7}"><fmt:message key="state_inactive" bundle="${messages}"/></c:when>
				                <c:when test="${c.estadoId == 8}"><fmt:message key="state_development" bundle="${messages}"/></c:when>
				                <c:otherwise>Unknown Role</c:otherwise>
				            </c:choose>
							</span>
						</li>
						<br/>
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