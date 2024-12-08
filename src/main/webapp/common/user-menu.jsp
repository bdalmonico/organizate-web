<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.org.model.*"%>
<%@ page import="com.bruno.training.web.util.*"%>

<% 	EmpleadoDTO empleado = (EmpleadoDTO) SessionManager.getAttribute(request, Attributes.EMPLEADO);
	if (empleado==null) {
%>
		<button style="height: 50px; width: 100px; font-size:16px; cursor:pointer;" onclick="window.location='<%=request.getContextPath()%>/user/login.jsp'">
			<fmt:message key="authenticate" bundle="${messages}" />
		</button>
<%
	} else {
%>
		<button style="height: 50px; width: 100px; font-size:16px; border-radius:5px; border:none; cursor:pointer;" onclick="window.location='<%=request.getContextPath()%>/private/UsuarioServlet?action=logout'">
			<fmt:message key="exit" bundle="${messages}" />
		</button>
<%		
	}
%>
