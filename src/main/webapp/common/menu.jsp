<%@ page import="com.bruno.training.web.util.*"%>


<aside class="sidebar">
    <div class="user-profile">
        <img alt="profile image" src="${pageContext.request.contextPath}/ImageServlet?action=profileImage&id=${empleado.id}&imageName=g1.jpg" />
        <p style="color:#305f8a; font-size:16px; font-weight: bold;">${sessionScope.empleado.nombre} ${sessionScope.empleado.apellido}</p>
        <p style="color:#3b3b3b;  text-transform: lowercase; font-size:12px; font-weight: bold;">${sessionScope.empleado.email}</p>
        
    </div>
    <nav class="menu">
        <ul>
        	<li>
        	<h2><a href="<%=request.getContextPath()%>/index.jsp">Inicio</a></h2>
        
        	</li>
            <!-- Exibe para Diretores, Gestores  -->
            <c:if test="${sessionScope.empleado.rolId == 1 || sessionScope.empleado.rolId == 4}">
                <li>
                    <h2><fmt:message key="employee" bundle="${messages}"/></h2>
                    <a href="<%=request.getContextPath()%><%=Views.EMPLEADO_SEARCH%>"><fmt:message key="search_employee" bundle="${messages}" /></a>
                    <br />
                    <a href="<%=request.getContextPath()%><%=Views.EMPLEADO_INSERT%>"><fmt:message key="create_employee" bundle="${messages}" /></a>
                    <br />
                    <a href="<%=request.getContextPath()%><%=Views.EMPLEADO_UPDATE%>"><fmt:message key="update_employee" bundle="${messages}" /></a>
                    <br />
                    <a href="<%=request.getContextPath()%><%=Views.EMPLEADO_DELETE%>"><fmt:message key="delete_employee" bundle="${messages}" /></a>
                </li>
            </c:if>
            
            <!-- Programadores podem apenas buscar por projetos-->
            <c:if test="${sessionScope.empleado.rolId == 4 || sessionScope.empleado.rolId == 1 || sessionScope.empleado.rolId == 2 || sessionScope.empleado.rolId == 3}">
                <li>
                
                    <h2><fmt:message key="project" bundle="${messages}" /></h2>
                    <a href="<%=request.getContextPath()%><%=Views.PROYECTO_SEARCH%>"><fmt:message key="search_project" bundle="${messages}" /></a>
                    <br />
                    
                    <c:if test="${sessionScope.empleado.rolId != 3}">
	                    <a href="<%=request.getContextPath()%><%=Views.PROYECTO_CREAR%>"><fmt:message key="create_project" bundle="${messages}" /></a>
	                    <br />
	                    <a href="<%=request.getContextPath()%><%=Views.PROYECTO_UPDATE%>"><fmt:message key="update_project" bundle="${messages}" /></a>
	                    <br />
	                    <a href="<%=request.getContextPath()%><%=Views.PROYECTO_DELETE%>"><fmt:message key="delete_project" bundle="${messages}" /></a>
                    </c:if>
                    
                </li>
                <li>
                
                    <h2><fmt:message key="task" bundle="${messages}" /></h2>
                    <a href="<%=request.getContextPath()%><%=Views.TAREA_SEARCH%>"><fmt:message key="search_task" bundle="${messages}" /></a>
                    <br />
                    
                    <c:if test="${sessionScope.empleado.rolId != 3}">
	                    <a href="<%=request.getContextPath()%><%=Views.TAREA_CREAR%>"><fmt:message key="create_task" bundle="${messages}" /></a>
	                    <br />
	                    <a href="<%=request.getContextPath()%><%=Views.TAREA_UPDATE%>"><fmt:message key="update_task" bundle="${messages}" /></a>
	                    <br />
	                    <a href="<%=request.getContextPath()%><%=Views.TAREA_DELETE%>"><fmt:message key="delete_task" bundle="${messages}" /></a>
                    </c:if>
                </li>
            </c:if>

            <!-- Outras seções para Diretores e Gestor -->
            <c:if test="${sessionScope.empleado.rolId == 1 || sessionScope.empleado.rolId == 4}">
                <li>
                    <h2><fmt:message key="client" bundle="${messages}" /></h2>
                    <a href="<%=request.getContextPath()%><%=Views.CLIENTE_SEARCH%>"><fmt:message key="search_client" bundle="${messages}" /></a>
                    <br />
                    <a href="<%=request.getContextPath()%><%=Views.CLIENTE_CREAR%>"><fmt:message key="create_client" bundle="${messages}" /></a>
                    <br />
                    <a href="<%=request.getContextPath()%><%=Views.CLIENTE_UPDATE%>"><fmt:message key="update_client" bundle="${messages}" /></a>
                    <br />
                    <a href="<%=request.getContextPath()%><%=Views.CLIENTE_DELETE%>"><fmt:message key="delete_client" bundle="${messages}" /></a>
                </li>
            </c:if>
        </ul>
    </nav>
</aside>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
	<aside class="sidebar">
	<h1>ABAJO ESTA EL MENU CON TODAS LAS OPCIONES POSIBLES PARA LA APLICACION</h1>
		<div class="user-profile">
		<img alt="profile image" src="${pageContext.request.contextPath}/ImageServlet?action=profileImage&id=${empleado.id}&imageName=g1.jpg" />

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
				<li>
					<h2><fmt:message key="client" bundle="${messages}" /></h2>
					<a href="<%=request.getContextPath()%><%=Views.CLIENTE_SEARCH%>"><fmt:message key="search_client" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.CLIENTE_CREAR%>"><fmt:message key="create_client" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.CLIENTE_UPDATE%>"><fmt:message key="update_client" bundle="${messages}"/></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.CLIENTE_DELETE%>"><fmt:message key="delete_client" bundle="${messages}" /></a>
				</li>
				<li>
					<h2><fmt:message key="project_comments" bundle="${messages}" /></h2>
					<a href="<%=request.getContextPath()%><%=Views.COMENTARIOPROYECTO_SEARCH%>"><fmt:message key="search_comment" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.COMENTARIOPROYECTO_CREAR%>"><fmt:message key="create_comment" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.COMENTARIOPROYECTO_UPDATE%>"><fmt:message key="update_comment" bundle="${messages}"/></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.COMENTARIOPROYECTO_DELETE%>"><fmt:message key="delete_comment" bundle="${messages}" /></a>
				</li>
				<li>
					<h2><fmt:message key="task_comments" bundle="${messages}" /></h2>
					<a href="<%=request.getContextPath()%><%=Views.COMENTARIOTAREA_SEARCH%>"><fmt:message key="search_comment" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.COMENTARIOTAREA_CREAR%>"><fmt:message key="create_comment" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.COMENTARIOTAREA_UPDATE%>"><fmt:message key="update_comment" bundle="${messages}"/></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.COMENTARIOTAREA_DELETE%>"><fmt:message key="delete_comment" bundle="${messages}" /></a>
				</li>
				<li>
					<h2><fmt:message key="imputation" bundle="${messages}" /></h2>
					<a href="<%=request.getContextPath()%><%=Views.IMPUTACION_SEARCH%>"><fmt:message key="search_imputation" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.IMPUTACION_SEARCHALL%>"><fmt:message key="searchall_imputation" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.IMPUTACION_CREAR%>"><fmt:message key="create_imputation" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.IMPUTACION_DELETE%>"><fmt:message key="delete_imputation" bundle="${messages}" /></a>
				</li>
				<li>
					<h2><fmt:message key="role" bundle="${messages}" /></h2>
					<a href="<%=request.getContextPath()%><%=Views.ROL_SEARCH%>"><fmt:message key="search_role" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.ROL_SEARCHALL%>"><fmt:message key="searchall_role" bundle="${messages}" /></a>
				</li>
				<li>
					<h2><fmt:message key="state" bundle="${messages}" /></h2>
					<a href="<%=request.getContextPath()%><%=Views.ESTADO_SEARCH%>"><fmt:message key="search_state" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.ESTADO_SEARCHALL%>"><fmt:message key="searchall_state" bundle="${messages}" /></a>
				</li>
				<li>
					<h2><fmt:message key="asignemployeentask" bundle="${messages}" /></h2>
					<a href="<%=request.getContextPath()%><%=Views.EMPLEADOTAREA_SEARCHEMPLEADO%>"><fmt:message key="search_employeesintask" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.EMPLEADOTAREA_SEARCHTAREA%>"><fmt:message key="search_tasksofemployee" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.EMPLEADOTAREA_SEARCH%>"><fmt:message key="search_employeetaskrelation" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.EMPLEADOTAREA_CREAR%>"><fmt:message key="asign_employeetotask" bundle="${messages}" /></a>
					</br>
					<a href="<%=request.getContextPath()%><%=Views.EMPLEADOTAREA_DELETE%>"><fmt:message key="delete_employeetaskrelation" bundle="${messages}" /></a>
				</li>
				
				<li><a href="#settings">Settings</a></li>
			</ul>
		</nav>
	</aside>
