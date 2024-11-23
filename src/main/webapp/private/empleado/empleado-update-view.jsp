<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp" %>
	<div>
		<form action="<%=request.getContextPath()%>/private/UsuarioServlet" method="post">
	
			<h3><fmt:message key="update_employee" bundle="${messages}"/></h3>
			
			<input type="hidden" name="<%=Parameters.ACTION %>" value="A<%=Actions.UPDATE%>" />
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
	
			
			<input type="submit" value="<fmt:message key="update" bundle="${messages}"/>" />
		</form>
	</div>	
<%@include file="/common/footer.jsp" %>