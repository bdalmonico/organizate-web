<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<form action="/HelloWorldWeb/private/UsuarioServlet" method="post">
	
	<h3><fmt:message key="create_employee" bundle="${messages}"/></h3>
	<input type="hidden" name="action" value="registrar" />
	<label><fmt:message key="name2p" bundle="${messages}"/></label> 
	<input type="text" name="nombre" />
	<label><fmt:message key="lastname2p" bundle="${messages}"/></label> 
	<input type="text" name="apellido"/>
	<label><fmt:message key="email2p" bundle="${messages}"/></label> 
	<input type="text" name="email" />
	<label><fmt:message key="password2p" bundle="${messages}"/></label>
	<input type="text" name="contrasena" />
	<label><fmt:message key="role2p" bundle="${messages}"/></label> 
	<input type="text" name="rolId"  />
	<label><fmt:message key="registration_date2p" bundle="${messages}"/></label> 
	<input type="date" name="fechaAlta" />
	<input type="submit" value="<fmt:message key="create" bundle="${messages}"/>" />
</form>

<%@include file="/common/footer.jsp"%>
