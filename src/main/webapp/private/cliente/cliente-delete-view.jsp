<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp" %>
	<div>
		<form action="<%=request.getContextPath()%>/private/ClienteServlet" method="post">		
			<h3 class="titulo"><fmt:message key="delete_client" bundle="${messages}"/></h3>
			
			<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.DELETE%>"/>
			
			<label><fmt:message key="id2p" bundle="${messages}"/></label> 
			<input type="text" name="<%=Parameters.ID %>" placeholder="Ejemplo: id" />
			
			<input type="submit" value="<fmt:message key="delete" bundle="${messages}"/>" />
		</form>
	</div>	
<%@include file="/common/footer.jsp" %>