<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="container">
	<%
	EmpleadoDTO emple = (EmpleadoDTO) request.getAttribute(Attributes.EMPLEADO);
	%>

	<p>
		<b>ID:</b>
		<%=emple.getId()%><br>
	</p>
	<p>
		<b>Nombre:</b> <%=emple.getNombre()%>
	</p>
	<p>
		<b>Apellidos:</b> <%=emple.getApellido()%>
	</p>
	<p>
		<b>Correo Electr�nico:</b> <%=emple.getEmail()%>
	</p>
	<p>
		<b>Contrase�a:</b> <%=emple.getContrasena()%>
	</p>
	<p>
		<b>Fecha de Alta:</b> <%=emple.getFechaAlta()%>
	</p>
	<p>
		<b>Rol ID:</b> <%=emple.getRolId()%>
	</p>


</div>


<%@include file="/common/footer.jsp"%>
