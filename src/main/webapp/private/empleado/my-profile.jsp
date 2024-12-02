<%@page import="com.bruno.training.web.util.SessionManager"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<include file="/common/header.jsp"/>


<div class="container">
    <h1>Perfil de Usuario</h1>
    <div id="image-container">
        <img alt="profile image" 
             src="${pageContext.request.contextPath}/ImageServlet?action=profileImage&id=${sessionScope.empleado.id}&imageName=profile.png" />
    </div>
    
    <form action="<%=request.getContextPath()%>/private/FileUploadServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="empleadoId" value="${sessionScope.empleado.id}">
        <input type="file" name="file">
        <input type="submit" value="Subir Foto">
    </form>
    
    <form action="<%=request.getContextPath()%>/private/EmpleadoServlet" method="post">
        <input type="hidden" name="action" value="edit-profile">
        
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${sessionScope.empleado.nombre}" disabled required>
        
        <label for="apellido">Primer Apellido:</label>
        <input type="text" id="apellido" name="apellido" value="${sessionScope.empleado.apellido}" disabled required>
        
        <label><fmt:message key="registration_date2p" bundle="${messages}"/></label> 
		<input type="date" name="fechaAlta" />
	
        <label for="email">Correo Electrónico:</label>
        <input type="email" id="email" name="email" value="${sessionScope.empleado.email}" disabled required>
        
        <label>Rol<fmt:message key="role2p" bundle="${messages}"/></label>
		<input type="text" name="rolId" />
        <br/>
        <label for="contrasena">Contraseña:</label>
        <input type="contrasena" id="contrasena" name="contrasena" value="${sessionScope.empleado.contrasena}" disabled required>
        
        <button type="submit" class="save-button">Guardar Cambios</button>
    </form>
    
</div>

<include file="/common/footer.jsp">
