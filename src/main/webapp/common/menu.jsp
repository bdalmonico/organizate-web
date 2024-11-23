<%@ page import="com.bruno.training.web.util.*"%>



	<aside class="sidebar">
		<div class="user-profile">
			<img src="<%=request.getContextPath()%>/imgs/logo.webp" width="100" height="60" />
		</div>
		<nav class="menu">
			<ul>
				<li>
					<h2><fmt:message key="employee" bundle="${messages}"/></h2>
					<a href="<%=request.getContextPath()%><%=Views.EMPLEADO_SEARCH%>"><fmt:message key="search_employee" bundle="${messages}"/></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.EMPLEADO_INSERT%>"><fmt:message key="create_employee" bundle="${messages}"/></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.EMPLEADO_UPDATE%>"><fmt:message key="update_employee" bundle="${messages}"/></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.EMPLEADO_DELETE%>"><fmt:message key="delete_employee" bundle="${messages}" /></a>
				</li>
				<li>
					<h2><fmt:message key="project" bundle="${messages}"/></h2>
					<a href="<%=request.getContextPath()%><%=Views.PROYECTO_SEARCH%>"><fmt:message key="search_project" bundle="${messages}"/></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.PROYECTO_CREAR%>"><fmt:message key="create_project" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.PROYECTO_UPDATE%>"><fmt:message key="update_project" bundle="${messages}"/></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.PROYECTO_DELETE%>"><fmt:message key="delete_project" bundle="${messages}" /></a>
				</li>
				<li>
					<h2><fmt:message key="task" bundle="${messages}" /></h2>
					<a href="<%=request.getContextPath()%><%=Views.TAREA_SEARCH%>"><fmt:message key="search_task" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.TAREA_CREAR%>"><fmt:message key="create_task" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.TAREA_UPDATE%>"><fmt:message key="update_task" bundle="${messages}"/></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.TAREA_DELETE%>"><fmt:message key="delete_task" bundle="${messages}" /></a>
				</li>
				
				<li><a href="#settings">Settings</a></li>
			</ul>
		</nav>
	</aside>
