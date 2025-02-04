<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp" %>
	<div>
		<form action="<%=request.getContextPath()%>/private/ProyectoServlet" method="post">		
			<h3 class="titulo"><fmt:message key="update_project" bundle="${messages}"/></h3>
			
			<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.UPDATE%>"/>
			
			<label><fmt:message key="id2p" bundle="${messages}"/></label> 
			<input type="text" name="<%=Parameters.ID %>" placeholder="Ejemplo: id" />
			
			<label><fmt:message key="name2p" bundle="${messages}"/></label> 
			<input type="text" name="<%=Parameters.NOMBRE %>" placeholder="Ejemplo: tarea" />
						
			<label><fmt:message key="description2p" bundle="${messages}"/></label>	
			<input type="text" name="<%=Parameters.DESCRIPCION %>" placeholder="Ejemplo: descripcion" />
			
			<label><fmt:message key="start_date2p" bundle="${messages}"/></label>	
			<input type="date" name="<%=Parameters.FECHAESTIMADAINICIO %>" placeholder="Ejemplo: fecha estimada inicio" />
			
			<label><fmt:message key="end_date2p" bundle="${messages}"/></label>	
			<input type="date" name="<%=Parameters.FECHAESTIMADAFIN %>" placeholder="Ejemplo: fecha estimada fin" />
			
			<label><fmt:message key="real_start_date2p" bundle="${messages}"/></label>	
			<input type="date" name="<%=Parameters.FECHAREALINICIO %>" placeholder="Ejemplo: fecha real inicio" />
			
			<label><fmt:message key="real_end_date2p" bundle="${messages}"/></label>	
			<input type="date" name="<%=Parameters.FECHAREALFIN%>" placeholder="Ejemplo: fecha real fin" />
			
			<label><fmt:message key="status_id2p" bundle="${messages}"/></label>	
			<input type="number" name="<%=Parameters.ESTADOID%>" placeholder="Ejemplo: id del estado" />
			
			<label><fmt:message key="client_id2p" bundle="${messages}"/></label>	<br/>
			<input type="number" name="<%=Parameters.CLIENTEID%>" placeholder="Ejemplo: id del cliente" />
			<br/>
			<label><fmt:message key="budget2p" bundle="${messages}"/></label>	<br/>
			<input type="text" name="<%=Parameters.IMPORTE%>" placeholder="Ejemplo: importe" />
			<br/>
			<input type="submit" value="<fmt:message key="update" bundle="${messages}"/>" />
		</form>
	</div>	
<%@include file="/common/footer.jsp" %>