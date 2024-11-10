<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp" %>
	<div>
		<form action="/HelloWorldWeb/private/ProyectoServlet" method="post">	
		
				
			<h3 class="titulo"><fmt:message key="search_project" bundle="${messages}"/></h3>
			
			<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.SEARCH%>"/>
			
			<label><fmt:message key="name2p" bundle="${messages}"/></label> 
			<input type="text" name="<%=Parameters.NOMBRE %>" placeholder="Ejemplo: proyectos" />
						
			 
			<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
		</form>
		
		
		<div id="resultados">
		<c:choose >
			<c:when test="${not empty resultados}">
				<ul>
					<c:forEach var="p" items="${resultados}">
						<li>
							<a href="/HelloWorldWeb/private/ProyectoServlet?action=detail&id=${p.id}">
								<c:out value="${p.nombre}"/>
							</a>
						</li>
					</c:forEach>
						
				</ul>
			</div>
			</c:when>
			<c:otherwise>
				<h1><fmt:message key="without_results" bundle="${messages}"/>.</h1>
			</c:otherwise>
		</c:choose>
		
		
		
	</div>
	
	
<%@include file="/common/footer.jsp" %>