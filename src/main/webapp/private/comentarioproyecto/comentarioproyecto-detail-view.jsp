<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="container">
<fmt:message key="project_comment_results" bundle="${messages}"/>
	<%
	ComentarioProyectoDTO cp = (ComentarioProyectoDTO) request.getAttribute(Attributes.COMENTARIOPROYECTO);
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
