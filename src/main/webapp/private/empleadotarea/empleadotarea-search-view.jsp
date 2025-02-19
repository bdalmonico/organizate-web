<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div>
	<form action="<%=request.getContextPath()%>/private/EmpleadoTareaServlet" method="post">
		<h3 class="titulo">
			Buscar relación entre empleado y tarea
		</h3>
		<input type="hidden" name="<%=Parameters.ACTION%>"value="<%=Actions.SEARCH%>" /> 
		<label><fmt:message key="name2p" bundle="${messages}" /></label> 
		<input type="text" name="<%=Parameters.EMPLEADOID %>" placeholder="Ejemplo: id de empleado" value="${empleadoId}" /> 
		<input type="text" name="<%=Parameters.TAREAID %>" placeholder="Ejemplo: id de tarea" value="${tareaId}" /> 
		<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
	</form>
	<div id="resultados">
       <% EmpleadoTareaDTO resultados = (EmpleadoTareaDTO) request.getAttribute(Attributes.RESULTADOS);
           if (resultados != null) {
       %>
           <a href="<%=request.getContextPath()%>/private/EmpleadoTareaServlet?action=detail&empleadoId=<%=resultados.getEmpleadoId()%>&tareaId=<%=resultados.getTareaId()%>">
               <%=resultados.getEmpleadoId()%> - <%=resultados.getTareaId()%>
           </a>
       <%
           } else {
       %>
	       <h1>
	           <fmt:message key="without_results" bundle="${messages}" />
	       </h1>
       <%
           }
       %>
   </div>
</div>
<%@include file="/common/footer.jsp"%>