<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp" %>
	<div>
		<form action="<%=request.getContextPath()%>/private/EmpleadoTareaServlet" method="post">		
			<h3 class="titulo"><fmt:message key="delete_employeetaskrelation" bundle="${messages}"/></h3>
			
			<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.DELETE%>"/>
			
			<label><fmt:message key="employee_id2p" bundle="${messages}"/></label> 
			<input type="number" name="<%=Parameters.EMPLEADOID %>" placeholder="Ejemplo: id" />
			
			<label><fmt:message key="task_id2p" bundle="${messages}"/></label> 
			<input type="number" name="<%=Parameters.TAREAID %>" placeholder="Ejemplo: id" />
			
			<input type="submit" value="<fmt:message key="delete" bundle="${messages}"/>" />
		</form>
	</div>	
<%@include file="/common/footer.jsp" %>