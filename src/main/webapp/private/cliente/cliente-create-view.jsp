<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<form action="<%=request.getContextPath()%>/private/ClienteServlet" method="post">
	
	<h3><fmt:message key="create_client" bundle="${messages}"/></h3>
	
	<input type="hidden" name="action" value="registrar" />
	<label><fmt:message key="name2p" bundle="${messages}"/></label> 
	<input type="text" name="nombre" />
	
	<label><fmt:message key="nif_cif2p" bundle="${messages}"/></label> 
	<input type="text" name="nifCif"/>
	
	<label><fmt:message key="email2p" bundle="${messages}"/></label> 
	<input type="text" name="email" />
	
	
	<label><fmt:message key="phone2p" bundle="${messages}"/></label>
	<input type="text" name="telefono" />
	
	
	<label><fmt:message key="status_id2p" bundle="${messages}"/></label> 
	<input type="number" name="estadoId"  />
	
	<input type="submit" value="<fmt:message key="create" bundle="${messages}"/>" />
</form>

<%@include file="/common/footer.jsp"%>
