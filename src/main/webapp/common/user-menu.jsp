<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.org.model.*"%>
<%@ page import="com.bruno.training.web.util.*"%>

<% 	
	EmpleadoDTO empleado = (EmpleadoDTO) SessionManager.getAttribute(request, Attributes.EMPLEADO);
	if (empleado==null) {
		%><a href="/HelloWorldWeb/user/login.jsp"><fmt:message
				key="authenticate" bundle="${messages}" /></a><%
	} else {
		%>
			<p><%=empleado.getNombre()%> <%=empleado.getApellido() %></p>
			<p><%=empleado.getEmail()%></p>
			<a href="/HelloWorldWeb/private/UsuarioServlet?action=logout">Salir</a>
		<%		
	}
%>
