<%@ page import="com.bruno.training.web.util.*"%>



<nav class="horizontal-menu">
	<aside class="sidebar">
		<div class="user-profile">
			<img src="/HelloWorldWeb/imgs/logo.webp" width="100" height="60" />
		</div>
		<nav class="menu">
			<ul>
				<li>
					<h2><fmt:message key="employee" bundle="${messages}"/></h2>
					<a href="/HelloWorldWeb<%=Views.EMPLEADO_SEARCH%>"><fmt:message key="search_employee" bundle="${messages}"/></a>
					</br>
					<a href="/HelloWorldWeb<%=Views.EMPLEADO_INSERT%>"><fmt:message key="create_employee" bundle="${messages}"/></a>
				</li>
				<li>
					<h2><fmt:message key="project" bundle="${messages}"/></h2>
					<a href="/HelloWorldWeb<%=Views.PROYECTO_SEARCH%>"><fmt:message key="search_project" bundle="${messages}"/></a>
					</br>
					<a href="/HelloWorldWeb<%=Views.PROYECTO_CREAR%>"><fmt:message key="create_project" bundle="${messages}" /></a>
				</li>
				<li>
					<h2><fmt:message key="task" bundle="${messages}" /></h2>
					<a href="/HelloWorldWeb<%=Views.TAREA_SEARCH%>"><fmt:message key="search_task" bundle="${messages}" /></a>
					</br>
					<a href="/HelloWorldWeb<%=Views.TAREA_CREAR%>"><fmt:message key="create_task" bundle="${messages}" /></a>
				</li>
				
				<li><a href="#settings">Settings</a></li>
			</ul>
		</nav>
	</aside>
	<ul>
		<li><h3>
				<fmt:message key="employee" bundle="${messages}" />
			</h3>
			<ul class="submenu">
				<li><a href="/HelloWorldWeb<%=Views.EMPLEADO_SEARCH%>"><fmt:message
							key="search_employee" bundle="${messages}" /></a></li>
				<li><a href="/HelloWorldWeb<%=Views.EMPLEADO_INSERT%>"><fmt:message
							key="create_employee" bundle="${messages}" /></a></li>
			</ul></li>
		<li><h3>
				<fmt:message key="project" bundle="${messages}" />
			</h3>
			<ul class="submenu">
				<li><a href="/HelloWorldWeb<%=Views.PROYECTO_SEARCH%>"><fmt:message
							key="search_project" bundle="${messages}" /></a></li>
				<li><a href="/HelloWorldWeb<%=Views.PROYECTO_CREAR%>"><fmt:message
							key="create_project" bundle="${messages}" /></a></li>
			</ul></li>
		<li><h3>
				<fmt:message key="task" bundle="${messages}" />
			</h3>
			<ul class="submenu">
				<li><a href="/HelloWorldWeb<%=Views.TAREA_SEARCH%>"><fmt:message
							key="search_task" bundle="${messages}" /></a></li>
				<li><a href="/HelloWorldWeb<%=Views.TAREA_CREAR%>"><fmt:message
							key="create_task" bundle="${messages}" /></a></li>
			</ul></li>
	</ul>
</nav>