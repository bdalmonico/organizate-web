<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<form action="/HelloWorldWeb/private/UsuarioServlet" method="post">
	
	<h3>Criar Usuarios</h3>
	<input type="hidden" name="action" value="registrar" />
	<label>Nombre:</label> 
	<input type="text" name="nombre" />
	<label>Apellido:</label> 
	<input type="text" name="apellido" />
	<label>Email:</label> 
	<input type="text" name="email" />
	<label>Contrasena:</label>
	<input type="text" name="contrasena" />
	<label>RolId:</label> 
	<input type="text" name="rolId" />
	<label>Fecha de Alta:</label> 
	<input type="text" name="fechaAlta" />
	<input type="submit" value="Enviar" />
</form>

<%@include file="/common/footer.jsp"%>
