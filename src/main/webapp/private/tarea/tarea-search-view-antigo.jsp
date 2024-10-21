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
						
			<label>Descipcion:</label>	
			<input type="text" name="<%=Parameters.DESCRIPCION %>" placeholder="Ejemplo: descripcion" />
			
			<label>Fecha Estimada inicio:</label>	
			<input type="text" name="<%=Parameters.FECHAESTIMADAINICIO %>" placeholder="Ejemplo: fecha estimada inicio" />
			
			<label>Fecha Estimada fin:</label>	
			<input type="text" name="<%=Parameters.FECHAESTIMADAFIN %>" placeholder="Ejemplo: fecha estimada fin" />
			
			<label>Fecha real inicio:</label>	
			<input type="text" name="<%=Parameters.FECHAREALINICIO %>" placeholder="Ejemplo: fecha real inicio" />
			
			<label>Fecha real fin:</label>	
			<input type="text" name="<%=Parameters.FECHAREALFIN%>" placeholder="Ejemplo: fecha real fin" />
			
			<label>Id de la tarea:</label>	
			<input type="text" name="<%=Parameters.ID%>" placeholder="Ejemplo: id de la tarea" />
			
			<label>Id del proyecto:</label>	
			<input type="text" name="<%=Parameters.PROYECTOID%>" placeholder="Ejemplo: id del proyecto" />
			
			

			 
			 
			<input type="submit" value="Buscar" />
		</form>
		<div id="resultados">
		<ul>
			<%
			Results<TareaDTO> resultados = (Results<TareaDTO>) request.getAttribute(Attributes.RESULTADOS);
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
			} else {
				%> <p>No se encontraram resultados.</p>
				<%
			}
			%>
		
		
		
		</ul>
		</div>
	</div>
	
	
<%@include file="/common/footer.jsp" %>