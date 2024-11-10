<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="container">
	<%
	EmpleadoDTO emp = (EmpleadoDTO) request.getAttribute(Attributes.EMPLEADO);
	%>

	<p>
		<b><fmt:message key="id2p" bundle="${messages}"/></b>
		<%=emp.getId()%><br>
	</p>
	<p>
		<b><fmt:message key="name2p" bundle="${messages}"/></b> <%=emp.getNombre()%>
	</p>
	<p>
		<b><fmt:message key="lastname2p" bundle="${messages}"/></b> <%=emp.getApellido()%>
	</p>
	<p>
		<b><fmt:message key="email2p" bundle="${messages}"/></b> <%=emp.getEmail()%>
	</p>
	<p>
		<b><fmt:message key="password2p" bundle="${messages}"/></b> <%=emp.getContrasena()%>
	</p>
	<p>
		<b><fmt:message key="registration_date2p" bundle="${messages}"/></b> <%=emp.getFechaAlta()%>
	</p>
	<p>
		<b><fmt:message key="role2p" bundle="${messages}"/></b> <%=emp.getRolId()%>
	</p>


</div>


<%@include file="/common/footer.jsp"%>
