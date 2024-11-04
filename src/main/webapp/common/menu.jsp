<%@ page import="com.bruno.training.web.util.*"%>

<div class="navbar">
	<nav><a href="/HelloWorldWeb">Inicio</a></nav>
	<nav><a href="/HelloWorldWeb<%=Views.PROYECTO_SEARCH%>">Proyectos</a></nav>
	<nav><a href="/HelloWorldWeb<%=Views.TAREA_SEARCH%>">Tareas</a></nav>
	<nav><a href="/HelloWorldWeb<%=Views.EMPLEADO_SEARCH%>">Empleados</a></nav>
	<nav><a href="/HelloWorldWeb">Horas imputadas</a></nav>
</div>
<div class="navbar">
	<nav><a href="/HelloWorldWeb<%=Views.PROYECTO_CREAR%>">Crear Proyectos</a></nav>
	<nav><a href="/HelloWorldWeb<%=Views.TAREA_CREAR%>">Crear Tareas</a></nav>
	<nav><a href="/HelloWorldWeb<%=Views.EMPLEADO_INSERT%>">Crear Empleados</a></nav>
	<nav><a href="/HelloWorldWeb">Horas imputadas</a></nav>
</div>