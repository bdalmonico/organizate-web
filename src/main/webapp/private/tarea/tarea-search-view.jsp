<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp" %>
	<div>
		<form action="/HelloWorldWeb/private/TareaServlet" method="post">	
		
				
			<h3 class="titulo">Buscar tareas</h3>
			
			<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.SEARCH%>"/>
			
			<label>Nombre:</label> 
			<input type="text" name="<%=Parameters.NOMBRE %>" placeholder="Ejemplo: tarea" />
						
			
			
			
			
			 
			 
			<input type="submit" value="Buscar" />
		</form>
		<div id="resultados">
		<ul>
			<%
			Results<TareaDTO> resultados = (Results<TareaDTO>) request.getAttribute("resultados");
			if (resultados!=null) {
				for (TareaDTO tarea:resultados.getPage()) {
					%>
						<li>
							<a href="/HelloWorldWeb/private/TareaServlet?action=detail&id=<%=tarea.getId()%>">
								<%=tarea.getNombre()%></a> (<%=tarea.getDescripcion()%>)
						</li>
					<%	
				}
				%>
				<p>Encontrados <%=resultados.getTotal() %> tareas</p>
				<% 
			}
			%>
		</ul>
		</div>
	</div>
	
	
<%@include file="/common/footer.jsp" %>