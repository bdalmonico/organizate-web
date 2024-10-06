<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

	<form action="/OrganizateWeb/UsuarioServlet" method="post">
	
	<input type="hidden" name="<%=Parameters.ACTION%>" value="<%=Actions.LOGIN%>">
	
	<label>id:</label>
	<input type="number" name="<%=Parameters.ID %>" placeholder="id usuario"></input>
	<label>Contrasena:</label>
	<input type="password" name="<%=Parameters.PASSWORD%>"></input>
	<input type="submit" value="buscar" ></input>
	<input type="reset" value="limpiar"></input>
	</form>
<%@include file="/common/footer.jsp"%>
