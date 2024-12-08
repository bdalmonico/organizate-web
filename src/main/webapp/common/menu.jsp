<%@ page import="com.bruno.training.web.util.*"%>


<aside class="sidebar">
<div class="user-profile">
    <img alt="profile image" src="${pageContext.request.contextPath}/ImageServlet?action=profileImage&id=${empleado.id}&imageName=g1.jpg" />
    <p style="color:#305f8a; font-size:16px; font-weight: bold;">${sessionScope.empleado.nombre} ${sessionScope.empleado.apellido}</p>
    <p style="color:#3b3b3b;  text-transform: lowercase; font-size:12px; font-weight: bold;">${sessionScope.empleado.email}</p>
</div>
<nav class="menu">
    <ul>
    	<li style="text-align: center">
	     	<h2>
	     		<a href="<%=request.getContextPath()%>/index.jsp">Inicio</a>
	     	</h2>	
    	</li>
        <li style="text-align: center">
	        <h2>
		        <a href="<%=request.getContextPath()%><%=Views.EMPLEADO_SEARCH%>">
		         	<fmt:message key="employee" bundle="${messages}"/>
		        </a>
	        </h2>
        </li>
        <li style="text-align: center">
	         <h2>
	         	<a href="<%=request.getContextPath()%>/private/ProyectoServlet?action=search">
	         		<fmt:message key="project" bundle="${messages}" />
	         	</a>
	         </h2>
        </li>
        <li style="text-align: center">
            <h2>
	        	<a href="<%=request.getContextPath()%>/private/TareaServlet?action=search">
	        		<fmt:message key="task" bundle="${messages}" />
	        	</a>
	        </h2>              
        </li>
      

        <li style="text-align: center">
            <h2>
            	<a href="<%=request.getContextPath()%>/private/ClienteServlet?action=search">
            		<fmt:message key="client" bundle="${messages}" />
            	</a>
            </h2>
        </li>
    </ul>
</nav>
</aside>
