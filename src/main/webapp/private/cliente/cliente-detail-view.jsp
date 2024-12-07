<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="container">
	
<span>
<p><fmt:message key="id2p" bundle="${messages}"/> <c:out value="${cliente.id}" /></p> 
<p><fmt:message key="name2p" bundle="${messages}"/> <c:out value="${cliente.nombre}" /></p>
<p><fmt:message key="phone2p" bundle="${messages}"/> <c:out value="${cliente.telefone}" /></p>
<p><fmt:message key="email2p" bundle="${messages}"/>  <c:out value="${cliente.email}" /></p>
<p><fmt:message key="nif_cif2p" bundle="${messages}"/> <c:out value="${cliente.nifCif}" /></p>
<p>
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
</p>
</span>

</div>


<%@include file="/common/footer.jsp"%>
