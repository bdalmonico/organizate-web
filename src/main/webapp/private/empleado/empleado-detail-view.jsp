<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="container">
	<%
	EmpleadoDTO emple = (EmpleadoDTO) request.getAttribute(Attributes.EMPLEADO);
	%>

	
	
	
	<p>
		<b>Nombre: <%=emple.getNombre()%>
		</b>
	</p>

</div>


<%@include file="/common/footer.jsp"%>
