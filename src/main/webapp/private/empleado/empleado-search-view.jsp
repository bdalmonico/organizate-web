<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<form action="/HelloWorldWeb/private/UsuarioServlet" method="post">
	<h3>Buscar Usuarios</h3>
	<input type="hidden" name="action" value="search" />
	<label>Id:</label>
	<input type="number" name="id" /> 
	<label>Nombre:</label> 
	<input type="text" name="nombre" />
	<label>Rol:</label>
	<input type="text" name="rolId" />
	<label>Email:</label> 
	<input type="text" name="email" />
	<input type="submit" value="Buscar" />
</form>


<div class="results-container">
	<!--  getPage() ? -->
	<c:choose>
		<c:when test="${not empty resultados}">
			<h3>Empleados de la búsqueda:</h3>
			<ul>
				<c:forEach var="emp" items="${resultados}">
					<li>
						<b>ID: <c:out value="${emp.id}"/>:<b/>
						<a href="/HelloWorldWeb/private/UsuarioServlet?action=detail&id=${emp.id}">
							<c:out value="${emp.nombre}" />
							<c:out value="${emp.apellido}" />
						</a>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No se encontraron resultados.</p>
		</c:otherwise>
	</c:choose>
</div>

<%@include file="/common/footer.jsp"%>
