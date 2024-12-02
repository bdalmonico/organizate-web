<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="container">
	<%
	EmpleadoDTO emp = (EmpleadoDTO) request.getAttribute(Attributes.EMPLEADO);
	%>
	
	<div id="image-container">
       <div style="border:1px solid black" width="50px" height="100px">
        <img alt="profile image" src="<%=request.getContextPath()%>/private/ImageServlet?action=profileImage&id=${sessionScope.empleado.id}&imageName=emp1.jpg" />
        </div>
	    <form action="<%=request.getContextPath()%>/private/FileUploadServlet" method="post" enctype="multipart/form-data">
	        <input type="hidden" name="id" value="${sessionScope.empleado.id}">
	        <input type="file" name="file">
	        <input type="submit" value="Subir Foto">
	    </form>
	    
	    </div>
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
