<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<form action="<%=request.getContextPath()%>/private/UsuarioServlet" method="post">
	<h3><fmt:message key="search_employee" bundle="${messages}"/></h3>
	<input type="hidden" name="action" value="search" />
	<label><fmt:message key="id2p" bundle="${messages}"/></label>
	<input type="number" name="id" /> 
	<label><fmt:message key="name2p" bundle="${messages}"/></label> 
	<input type="text" name="nombre" />
	<label><fmt:message key="role2p" bundle="${messages}"/></label>
	<input type="text" name="rolId" />
	<label><fmt:message key="email2p" bundle="${messages}"/></label> 
	<input type="text" name="email" />
	<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
</form>


<div class="results-container">
	<!--  getPage() ? -->
	<c:choose>
		<c:when test="${not empty resultados}">
			<h3><fmt:message key="results" bundle="${messages}"/></h3>
			<ul>
				<c:forEach var="emp" items="${resultados}">
					<li>
						<b>ID: <c:out value="${emp.id}"/>:<b/>
						<a href="<%=request.getContextPath()%>/private/UsuarioServlet?action=detail&id=${emp.id}">
							<c:out value="${emp.nombre}" />
							<c:out value="${emp.apellido}" />
						</a>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p><fmt:message key="search_more" bundle="${messages}"/></p>
		</c:otherwise>
	</c:choose>
</div>

<%@include file="/common/footer.jsp"%>
