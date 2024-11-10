<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp" %>
	<div>
		<form action="/HelloWorldWeb/private/TareaServlet" method="post">	
		
				
			<h3 class="titulo"><fmt:message key="search_task" bundle="${messages}"/></h3>
			
			<input type="hidden" name="<%=Parameters.ACTION %>" value="<%=Actions.SEARCH%>"/>
			
			<label><fmt:message key="name2p" bundle="${messages}"/></label> 
			<input type="text" name="<%=Parameters.NOMBRE %>" placeholder="Ejemplo: tarea" />
						
			<label><fmt:message key="description2p" bundle="${messages}"/></label>	
			<input type="text" name="<%=Parameters.DESCRIPCION %>" placeholder="Ejemplo: descripcion" />
			
			<label><fmt:message key="start_date2p" bundle="${messages}"/></label>	
			<input type="text" name="<%=Parameters.FECHAESTIMADAINICIO %>" placeholder="Ejemplo: fecha estimada inicio" />
			
			<label><fmt:message key="end_date2p" bundle="${messages}"/></label>	
			<input type="text" name="<%=Parameters.FECHAESTIMADAFIN %>" placeholder="Ejemplo: fecha estimada fin" />
			
			<label><fmt:message key="real_start_date2p" bundle="${messages}"/></label>	
			<input type="text" name="<%=Parameters.FECHAREALINICIO %>" placeholder="Ejemplo: fecha real inicio" />
			
			<label><fmt:message key="real_end_date2p" bundle="${messages}"/></label>	
			<input type="text" name="<%=Parameters.FECHAREALFIN%>" placeholder="Ejemplo: fecha real fin" />
			
			<label><fmt:message key="taks_id2p" bundle="${messages}"/></label>	
			<input type="text" name="<%=Parameters.ID%>" placeholder="Ejemplo: id de la tarea" />
			
			<label><fmt:message key="project_id2p" bundle="${messages}"/></label>	
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