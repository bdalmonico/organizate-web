<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="container">
    <%
        RolDTO rol = (RolDTO) request.getAttribute(Attributes.ROL);
        if (rol != null) {
    %>
    <p>
        <b><fmt:message key="comment_id2p" bundle="${messages}"/></b>
        <%=rol.getId()%><br>
    </p>
    <p>
        <b><fmt:message key="comment_2p" bundle="${messages}"/></b> <%=rol.getNombre()%>
    </p>
    <%
        } else {
    %>
    <p>
        <fmt:message key="without_results" bundle="${messages}"/>
    </p>
    <%
        }
    %>
</div>

<%@include file="/common/footer.jsp"%>
