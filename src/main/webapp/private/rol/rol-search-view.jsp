<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div>
    <form action="<%=request.getContextPath()%>/private/RolServlet" method="post">
        <h3 class="titulo">
            <fmt:message key="search_client" bundle="${messages}" />
        </h3>
        <input type="hidden" name="<%=Parameters.ACTION%>" value="<%=Actions.SEARCH%>" />
        <label><fmt:message key="name2p" bundle="${messages}" /></label>
        <input type="text" name="<%=Parameters.ROLID %>" placeholder="Ejemplo: id de tarea" value="${rolId}" />
        <input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
    </form>
    <div id="resultados">
        <%
            RolDTO resultados = (RolDTO) request.getAttribute(Attributes.RESULTADOS);
            if (resultados != null) {
        %>
        <li>
            <a href="<%=request.getContextPath()%>/private/RolServlet?action=detail&id=<%=resultados.getId()%>">
                <%=resultados.getId()%> - <%=resultados.getNombre()%>
            </a>
        </li>
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
