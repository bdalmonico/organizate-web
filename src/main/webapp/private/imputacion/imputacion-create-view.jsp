<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<form action="<%=request.getContextPath()%>/private/ImputacionServlet" method="post">
	
	<h3><fmt:message key="hour_imputation" bundle="${messages}"/></h3>
	
	<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.CREATE%>" />
	
	<label><fmt:message key="employee_id2p" bundle="${messages}"/></label>
	<input type="number" name="<%=Parameters.EMPLEADOID%>"/>
	
	<label><fmt:message key="task_id2p" bundle="${messages}"/></label> 
	<input type="number" name="<%=Parameters.TAREAID %>"  />
	
	<label><fmt:message key="project_id2p" bundle="${messages}"/></label> 
	<input type="number" name="<%=Parameters.PROYECTOID %>"  />
	
	<label><fmt:message key="hours_2p" bundle="${messages}"/></label> 
	<input type="text" name="<%=Parameters.HORASIMPUTADAS %>" />
	
	<label><fmt:message key="publish_date2p" bundle="${messages}"/></label> 
	<input type="date" name="<%=Parameters.FECHAHORA%>"/>
	
	<label><fmt:message key="comment_2p" bundle="${messages}"/></label> 
	<input type="text" name="<%=Parameters.COMENTARIO%>" />
	
	<input type="submit" value="<fmt:message key="create" bundle="${messages}"/>" />
</form>

<%@include file="/common/footer.jsp" %>