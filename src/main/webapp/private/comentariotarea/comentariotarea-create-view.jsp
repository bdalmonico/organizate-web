<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<form action="<%=request.getContextPath()%>/private/ComentarioTareaServlet" method="post">
	
	<h3><fmt:message key="create_comment2p" bundle="${messages}"/></h3>
	
	<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.CREATE%>" />
	
	<label><fmt:message key="comment_2p" bundle="${messages}"/></label> 
	<input type="text" name="<%=Parameters.COMENTARIO %>" />
	
	
	<label><fmt:message key="employee_id2p" bundle="${messages}"/></label>
	<input type="number" name="<%=Parameters.EMPLEADOID%>"/>
	
	<label><fmt:message key="task_id2p" bundle="${messages}"/></label> 
	<input type="number" name="<%=Parameters.TAREAID %>"  />
	
	<label><fmt:message key="publish_date2p" bundle="${messages}"/></label> 
	<input type="date" name="<%=Parameters.FECHAPUBLICACION %>"/>
	
	<input type="submit" value="<fmt:message key="create" bundle="${messages}"/>" />
</form>

<%@include file="/common/footer.jsp" %>