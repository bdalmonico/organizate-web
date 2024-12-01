<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="container">
	<%
	ImputacionDTO cp = (ImputacionDTO) request.getAttribute(Attributes.IMPUTACION);
	%>

	<p>
		<b><fmt:message key="comment_id2p" bundle="${messages}"/></b>
		<%=cp.getId()%><br>
	</p>
	<p>
		<b><fmt:message key="comment_2p" bundle="${messages}"/></b> <%=cp.getComentario()%>
	</p>

	<p>
		<b><fmt:message key="employee_id2p" bundle="${messages}"/></b> <%=cp.getEmpleadoId()%>
	</p>
	
</div>


<%@include file="/common/footer.jsp"%>
