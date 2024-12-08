<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div style="margin:30px">
	<form action="<%=request.getContextPath()%>/private/ClienteServlet" method="post">
		
		<h3><fmt:message key="create_client" bundle="${messages}"/></h3>
		
		<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.CREATE%>" />
		
		<label><fmt:message key="name2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.NOMBRE %>" />
		
		<label><fmt:message key="nif_cif2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.NIFCIF %>"/>
		
		<label><fmt:message key="email2p" bundle="${messages}"/></label> 
		<input type="text" name="<%=Parameters.EMAIL%>" />
		
		<label><fmt:message key="phone2p" bundle="${messages}"/></label>
		<input type="text" name="<%=Parameters.TELEFONE %>" />
		
		
		<label><fmt:message key="status_id2p" bundle="${messages}"/></label> 
		<input type="number" name="<%=Parameters.ESTADOID %>"  />
		
		<input type="submit" value="<fmt:message key="create" bundle="${messages}"/>" />
	</form>
</div>
<%@include file="/common/footer.jsp"%>
