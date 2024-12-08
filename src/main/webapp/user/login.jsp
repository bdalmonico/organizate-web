<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

	<form action="<%=request.getContextPath()%>/UsuarioServlet" method="post">
		<h3><fmt:message key="user_authentication" bundle="${messages}"/></h3>
		<input type="hidden" name="<%=Parameters.ACTION%>" value="<%=Actions.LOGIN%>">
		<label><fmt:message key="user2p" bundle="${messages}"/></label>		
		<input type="number" name="<%=Parameters.ID %>" placeholder="id usuario" required value="<%=CookieManager.getValue(request, "empleado")%>"/>
		<label><fmt:message key="password2p" bundle="${messages}"/></label>
		<input type="password" name="<%=Parameters.CONTRASENA%>" placeholder="contrase�a" value="abc123." required/>
		<input type="checkbox" name="remember-user" checked>Recordar usuario</input>
		<input type="submit" value="Entrar" ></input>
		<input type="reset" value="limpiar"></input>
	</form>

<%@include file="/common/footer.jsp"%>
