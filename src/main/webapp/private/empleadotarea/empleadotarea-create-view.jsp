<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<form action="<%=request.getContextPath()%>/private/EmpleadoTareaServlet" method="post">
	<h3><fmt:message key="asign_employee" bundle="${messages}"/></h3>
	
	<input type="hidden" name="<%=Parameters.ACTION%>" value="<%=Actions.CREATE%>" />
	
	<label><fmt:message key="employee_id2p" bundle="${messages}"/></label>
	<input type="number" name="<%=Parameters.EMPLEADOID%>"/>
	
	<label><fmt:message key="task_id2p" bundle="${messages}"/></label> 
	<input type="number" name="<%=Parameters.TAREAID %>"  />
	
	<input type="submit" value="<fmt:message key="create" bundle="${messages}"/>" />
</form>

<%@include file="/common/footer.jsp" %>