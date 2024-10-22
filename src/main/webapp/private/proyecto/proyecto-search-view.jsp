<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp" %>
	<div>
		<form action="/HelloWorldWeb/private/ProyectoServlet" method="post">	
		
				
			<h3 class="titulo">Buscar proyectos</h3>
			
			<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.SEARCH%>"/>
			
			<label>Nombre:</label> 
			<input type="text" name="<%=Parameters.NOMBRE %>" placeholder="Ejemplo: proyectos" />
						
			 
			<input type="submit" value="Buscar" />
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
				<h1>no se encontraron resultados</h1>
			</c:otherwise>
		</c:choose>
		
		
		
	</div>
	
	
<%@include file="/common/footer.jsp" %>