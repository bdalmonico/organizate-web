<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div>
    <form action="${pageContext.request.contextPath}/private/UsuarioServlet?action=uploadImage" method="post" enctype="multipart/form-data">
        <img alt="profile image" style="height:50px; width: 50px;" src="${pageContext.request.contextPath}/ImageServlet?action=profileImage&id=${empleado.id}&imageName=g1.jpg" />
        <input type="hidden" name="id" value="${empleado.id}">
        <input type="file" name="file">
        <input type="submit" value="Subir foto">
    </form>
		<p>
			<b><fmt:message key="id2p" bundle="${messages}"/></b>
			<c:out value="${empleado.id}" /><br>
		</p>
		
		<p>
			<b><fmt:message key="name2p" bundle="${messages}"/></b> <c:out value="${empleado.nombre}" />
		</p>
		<p>
			<b><fmt:message key="lastname2p" bundle="${messages}"/></b> <c:out value="${empleado.apellido}" />
		</p>
		<p>
			<b><fmt:message key="email2p" bundle="${messages}"/></b> <c:out value="${empleado.email}" />
		</p>
		<p>
			<b><fmt:message key="password2p" bundle="${messages}"/></b> <c:out value="${empleado.contrasena}" />
		</p>
		<p>
			<b><fmt:message key="registration_date2p" bundle="${messages}"/></b> <c:out value="${empleado.fechaAlta}" />
		</p>
		<p>
			<fmt:message key="role2p" bundle="${messages}"/><c:out value="${empleado.rolId}" />
		</p>
        <p>
        	<b><fmt:message key="role2p" bundle="${messages}"/></b> 
            <c:choose>
                <c:when test="${empleado.rolId == 1}">Diretor</c:when>
                <c:when test="${empleado.rolId == 2}">Administrador</c:when>
                <c:when test="${empleado.rolId == 3}">Programador</c:when>
                <c:when test="${empleado.rolId == 4}">Gestor</c:when>
                <c:otherwise>Unknown Role</c:otherwise>
            </c:choose>
        </p>

	</div>


<%@include file="/common/footer.jsp"%>
