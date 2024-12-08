<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div>
    <form action="<%=request.getContextPath()%>/private/ImputacionServlet" method="post">
        <h3 class="titulo">
            <fmt:message key="search_imputationbyproject" bundle="${messages}" />
        </h3>
        <input type="hidden" name="<%=Parameters.ACTION%>" value="<%=Actions.SEARCHALL%>" />
        <label><fmt:message key="project_id2p" bundle="${messages}" /></label> 
		<input type="number" name="<%=Parameters.PROYECTOID %>"  value="${proyectoId}" /> 
		
        <input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
    </form>
    <div id="resultados">
        <%
            Double totalHoras = (Double) request.getAttribute(Attributes.RESULTADOS);
            if (totalHoras != null) {
        %>
	        <p>
	            <strong>Total de horas imputadas no projeto:</strong> <%= String.format("%.2f", totalHoras) %>
	        </p>
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
