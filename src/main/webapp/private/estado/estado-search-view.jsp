<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div>
    <form action="<%=request.getContextPath()%>/private/EstadoServlet" method="post">
        <h3 class="titulo">
            <fmt:message key="search_state" bundle="${messages}" />
        </h3>
        <input type="hidden" name="<%=Parameters.ACTION%>" value="<%=Actions.SEARCH%>" />
        <label><fmt:message key="id2p" bundle="${messages}" /></label>
        <input type="number" name="<%=Parameters.ESTADOID %>" value="${estadoId}" />
        <input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
    </form>
    <div id="resultados">
        <%
            EstadoDTO resultados = (EstadoDTO) request.getAttribute(Attributes.RESULTADOS);
            if (resultados != null) {
        %>
        <p>
     
                <%=resultados.getId()%> - <%=resultados.getNombre()%>
            
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
