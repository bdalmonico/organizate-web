<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div class="container" style="margin:30px">
	<h3><fmt:message key="id2p" bundle="${messages}"/> <c:out value="${cliente.id}" /> </h3>
	<h3><fmt:message key="name2p" bundle="${messages}"/> <c:out value="${cliente.nombre}" /></h3>
	<h3><fmt:message key="phone2p" bundle="${messages}"/><c:out value="${cliente.telefone}" /></h3>
	<h3><fmt:message key="email2p" bundle="${messages}"/>  <c:out value="${cliente.email}" /></h3>
	<h3><fmt:message key="nif_cif2p" bundle="${messages}"/> <c:out value="${cliente.nifCif}" /></h3>
	<h3><fmt:message key="status_id2p" bundle="${messages}"/>
		<c:choose>
			<c:when test="${cliente.estadoId == 1}"><fmt:message key="state_open" bundle="${messages}"/></c:when>
			<c:when test="${cliente.estadoId == 2}"><fmt:message key="state_conclude" bundle="${messages}"/></c:when>
			<c:when test="${cliente.estadoId == 3}"><fmt:message key="state_working" bundle="${messages}"/></c:when>
			<c:when test="${cliente.estadoId == 4}"><fmt:message key="state_aproved" bundle="${messages}"/></c:when>
			<c:when test="${cliente.estadoId == 5}"><fmt:message key="state_prototype" bundle="${messages}"/></c:when>
			<c:when test="${cliente.estadoId == 6}"><fmt:message key="state_active" bundle="${messages}"/></c:when>
			<c:when test="${cliente.estadoId == 7}"><fmt:message key="state_inactive" bundle="${messages}"/></c:when>
			<c:when test="${cliente.estadoId == 8}"><fmt:message key="state_development" bundle="${messages}"/></c:when>
			<c:otherwise>Unknown Role</c:otherwise>
		</c:choose>
	</h3>
	<h4><a href="<%=request.getContextPath()%>/private/ClienteServlet?action=delete&id=${cliente.id}"><fmt:message key="delete_client" bundle="${messages}"/> ${client.id}</a></h4>
	<a href="<%=request.getContextPath()%><%=Views.CLIENTE_UPDATE%>"><fmt:message key="update_client" bundle="${messages}" /></a>
</div>
<%@include file="/common/footer.jsp"%>