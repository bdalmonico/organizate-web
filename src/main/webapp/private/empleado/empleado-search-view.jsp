<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div style="margin:30px">
<form action="${pageContext.request.contextPath}/private/UsuarioServlet" method="post">
	<h3><fmt:message key="search_employee" bundle="${messages}"/></h3>
	
	<input type="hidden" name="action" value="search" />
	
	<label><fmt:message key="id2p" bundle="${messages}"/></label>
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.ID)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="number" name="id" /> 
	
	
	<label><fmt:message key="name2p" bundle="${messages}"/></label>
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.NOMBRE)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="text" name="nombre" />
	
	<label><fmt:message key="lastname2p" bundle="${messages}"/></label>
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.APELLIDO)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="text" name="apellido" />
	
	<label><fmt:message key="role2p" bundle="${messages}"/></label>
    <c:forEach var="error" items="${errors.getFieldErrors(Parameters.ROLID)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
    <select id="<%=Parameters.ROLID%>" name="<%=Parameters.ROLID%>" style="text-align: center">
		<option value=""> - </option>
		<option value="3"><fmt:message key="${programmer}" bundle="${messages}" /></option>
		<option value="1"><fmt:message key="${director}" bundle="${messages}" /></option>
		<option value="2"><fmt:message key="${administrator}" bundle="${messages}" /></option>
		<option value="4"><fmt:message key="${manager}" bundle="${messages}" /></option>
	</select>
	
	<label><fmt:message key="email2p" bundle="${messages}"/></label> 
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.EMAIL)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="text" name="email" />
	
	<label><fmt:message key="registration_date2p" bundle="${messages}"/></label>
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.FECHAALTA)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="date" name="fechaAlta" />
	
	
	<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
</form>


<div class="results-container">
	<c:choose>
		<c:when test="${not empty resultados}">
			<h3><fmt:message key="results" bundle="${messages}"/></h3>
			<ul>
				<c:forEach var="emp" items="${resultados}">
					<li>
						<b><fmt:message key="id2p" bundle="${messages}"/><c:out value="${emp.id}"/>:<b/>
						<a href="${pageContext.request.contextPath}/private/UsuarioServlet?action=detail&id=${emp.id}">
							<c:out value="${emp.nombre}" /> <c:out value="${emp.apellido}" />
						</a>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p><fmt:message key="without_results" bundle="${messages}"/></p>
		</c:otherwise>
	</c:choose>
</div>
</div>
	<div>
		<button class="createbtn" onclick="window.location='<%=request.getContextPath()%><%=Views.EMPLEADO_INSERT%>'">
				<fmt:message key="create_employee" bundle="${messages}" />
		</button>
	</div>
<%@include file="/common/footer.jsp"%>
