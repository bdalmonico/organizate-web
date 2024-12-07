<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp" %>

<div>		
	<h3 class="titulo"><fmt:message key="update_client" bundle="${messages}"/></h3>
	<form action="<%=Parameters.ID %>/private/ClienteServlet" method="post">
	
		<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.UPDATE%>" />
		
		<label><fmt:message key="id2p" bundle="${messages}"/></label> 
		<input type="number" name="<%=Parameters.ID %>" />
		
		<label><fmt:message key="name2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.NOMBRE %>" />
							
		<label><fmt:message key="nif_cif2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.NIFCIF %>"/>
		
		<label><fmt:message key="email2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.EMAIL %>" />
		
		<label><fmt:message key="phone2p" bundle="${messages}"/></label>
		<input type="text" name="<%=Parameters.TELEFONE %>" />
		
		<label><fmt:message key="status_id2p" bundle="${messages}"/></label> 
		<input type="number" name="<%=Parameters.ESTADOID %>"  />
		
		<input type="submit" value="<fmt:message key="update" bundle="${messages}"/>" />
		
	</form>
</div>	

<%@include file="/common/footer.jsp" %>