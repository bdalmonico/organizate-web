<%@ page import="com.bruno.training.web.util.*"%>

<nav class="horizontal-menu">
    <ul>
        <li><h3><fmt:message key="employee" bundle="${messages}"/></h3>
            <ul class="submenu">
                <li><a href="/HelloWorldWeb<%=Views.EMPLEADO_SEARCH%>"><fmt:message key="search_employee" bundle="${messages}"/></a></li>
                <li><a href="/HelloWorldWeb<%=Views.EMPLEADO_INSERT%>"><fmt:message key="create_employee" bundle="${messages}"/></a></li>
            </ul>
        </li>
        <li><h3><fmt:message key="project" bundle="${messages}"/></h3>
            <ul class="submenu">
                <li><a href="/HelloWorldWeb<%=Views.PROYECTO_SEARCH%>"><fmt:message key="search_project" bundle="${messages}"/></a></li>
                <li><a href="/HelloWorldWeb<%=Views.PROYECTO_CREAR%>"><fmt:message key="create_project" bundle="${messages}"/></a></li>
            </ul>
        </li>
        <li><h3><fmt:message key="task" bundle="${messages}"/></h3>
            <ul class="submenu">
                <li><a href="/HelloWorldWeb<%=Views.TAREA_SEARCH%>"><fmt:message key="search_task" bundle="${messages}"/></a></li>
                <li><a href="/HelloWorldWeb<%=Views.TAREA_CREAR%>"><fmt:message key="create_task" bundle="${messages}"/></a></li>
            </ul>
        </li>
    </ul>
</nav>