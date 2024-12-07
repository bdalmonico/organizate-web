<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp" %>
	<div>
				
	<h3 class="titulo"><fmt:message key="update_task_comment" bundle="${messages}"/></h3>
	<form action="<%=request.getContextPath()%>/private/ComentarioTareaServlet" method="post">
	
	<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.UPDATE%>" />
	
	<label><fmt:message key="id2p" bundle="${messages}"/></label> 
	<input type="number" name="<%=Parameters.ID %>" placeholder="Ejemplo: id" />
	
	<label><fmt:message key="employee_id2p" bundle="${messages}"/></label> 
	<input type="number" name="<%=Parameters.EMPLEADOID %>"  />
						
	<label><fmt:message key="task_id2p" bundle="${messages}"/></label> 
	<input type="number" name="<%=Parameters.TAREAID %>"/>
	
	<label><fmt:message key="publish_date2p" bundle="${messages}"/></label> 
	<input type="date" name="<%=Parameters.FECHAPUBLICACION%>" />
	
	<label><fmt:message key="comment2p" bundle="${messages}"/></label> 
	<input type="text" name="<%=Parameters.COMENTARIO %>" />
	
			
			
			<input type="submit" value="<fmt:message key="update" bundle="${messages}"/>" />
		</form>
	</div>	
<%@include file="/common/footer.jsp" %>