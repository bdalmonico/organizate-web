<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>
<div>
    <form action="<%=request.getContextPath()%>/private/EstadoServlet" method="post">
        <h3 class="titulo">
            <fmt:message key="searchall_state" bundle="${messages}" />
        </h3>
        <input type="hidden" name="<%=Parameters.ACTION%>" value="<%=Actions.ALL%>" />
        <input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
    </form>
    <div id="resultados">
        <c:choose>
            <c:when test="${not empty resultados}">
                <ul>
                    <c:forEach var="estado" items="${resultados}">
                        <li>
                            <c:out value="${estado.id}" /> - <c:out value="${estado.nombre}" />
                            
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <h1>
                    <fmt:message key="without_results" bundle="${messages}" />
                </h1>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="/common/footer.jsp"%>
