<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

	<form action="/HelloWorldWeb/UsuarioServlet" method="post">
		<input type="hidden" name="<%=Parameters.ACTION%>" value="<%=Actions.LOGIN%>">
		<label>id:</label>
		<input type="number" name="<%=Parameters.ID %>" placeholder="id usuario" required value="<%=CookieManager.getValue(request, "empleado")%>"/>
		<label>Contrasena:</label>
		<input type="password" name="<%=Parameters.PASSWORD%>" placeholder="contraseña" value="abc123." required/>
		<input type="checkbox" name="remember-user" checked>Recordar usuario</input>
		<input type="submit" value="Entrar" ></input>
		<input type="reset" value="limpiar"></input>
	</form>
<%@include file="/common/footer.jsp"%>
