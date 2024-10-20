<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

	<form action="/HelloWorldWeb/private/UsuarioServlet" method="post">
	
	<h3>Buscar empleados</h3>



    <input type="hidden" name="action" value="search" /> 

    <label>Id:</label>
	<input type="number" name="id" /> 
    <label>Nombre:</label> 
    <input type="text" name="nombre" /> 
    <label>Rol:</label>
    <input type="text" name="rol" /> 
    <label>Email:</label> 
    <input type="text" name="email" />
	<input type="submit" value="Buscar" />
</form>

<div class="results-container">
	<h3>Resultados de la búsqueda:</h3>

	<ul>
		<%
		Results<Usuario> resultados = (Results<Usuario>) request.getAttribute(Attributes.RESULTADOS);
		if (resultados != null) {
			for (EmpleadoDTO empleado : resultados.getPage()) {
		%>
		<li><a
			href="/HelloWorldWeb/private/UsuarioServlet?action=detail&id=<%=u.getId()%>">
				<%=empleado.getNombre()%> <%
 }
 %>
				<p>
					Encontrado
					<%=resultados.getTotal()%>
					empleados
				</p> <%
 } else {
 %>
				<p>No se encontraron resultados.</p> <%
 }
 %>
		
	</ul>
</div>








	
	<label>id:</label>
	<input type="number" name="<%=Parameters.ID %>" placeholder="id usuario"></input>
	<label>Contrasena:</label>
	<input type="password" name="<%=Parameters.PASSWORD%>"></input>
	<input type="submit" value="buscar" ></input>
	<input type="reset" value="limpiar"></input>
	</form>

<%@include file="/common/footer.jsp"%>
