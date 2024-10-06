<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.org.model.*"%>
<%@ page import="com.bruno.training.web.util.*"%>

<% 	
	EmpleadoDTO empleado = (EmpleadoDTO) SessionManager.getAttribute(request, Attributes.EMPLEADO);
	if (empleado==null) {
		%><a href="/OrganizateWeb/user/login.jsp">Autenticarse</a><%
	} else {
		%>
			<p><%=empleado.getNombre()%></p>
			<a href="/OrganizateWeb/UsuarioServlet?action=logout">Salir</a>
		<%		
	}
%>
