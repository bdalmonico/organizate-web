<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<form action="${pageContext.request.contextPath}/private/UsuarioServlet?action=uploadImage" method="post" enctype="multipart/form-data">
       <img alt="profile image" style="height:50px; width: 50px;" src="${pageContext.request.contextPath}/ImageServlet?action=profileImage&id=${empleado.id}&imageName=g1.jpg" />
       <input type="hidden" name="id" value="${empleado.id}">
       <input type="file" name="file">
       <input type="submit" value="Subir foto">
</form>

<form action="${pageContext.request.contextPath}/private/UsuarioServlet" method="post">
	<h3><fmt:message key="update_employee" bundle="${messages}"/></h3>
	<input type="hidden" name="action" value="update" />
	
	<label><fmt:message key="id2p" bundle="${messages}"/></label> 
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.ID)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="text" name="id" value="${sessionScope.empleado.id}" />
	
	<label><fmt:message key="name2p" bundle="${messages}"/></label> 
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.NOMBRE)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="text" name="nombre" value="${sessionScope.empleado.nombre}" />
	
	<label><fmt:message key="lastname2p" bundle="${messages}"/></label> 
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.APELLIDO)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="text" name="apellido" value="${sessionScope.empleado.apellido}"/>
	
	<label><fmt:message key="email2p" bundle="${messages}"/></label> 
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.EMAIL)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="text" name="email" value="${sessionScope.empleado.email}" />
	
	<label><fmt:message key="password2p" bundle="${messages}"/></label>
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.CONTRASENA)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="password" name="contrasena" value="${sessionScope.empleado.contrasena}" />
	
	<label><fmt:message key="role2p" bundle="${messages}"/></label> 
	 <c:forEach var="error" items="${errors.getFieldErrors(Parameters.ROLID)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
    <select id="<%=Parameters.ROLID%>" name="<%=Parameters.ROLID%>" style="text-align: center">
		<option value=""> - </option>
		<option value="3"><fmt:message key="${programmer}" bundle="${messages}" /></option>
		<option value="1"><fmt:message key="${director}" bundle="${messages}" /></option>
		<option value="2"><fmt:message key="${administrator}" bundle="${messages}" /></option>
		<option value="4"><fmt:message key="${manager}" bundle="${messages}" /></option>
	</select>

	<label><fmt:message key="registration_date2p" bundle="${messages}"/></label> 
	<c:forEach var="error" items="${errors.getFieldErrors(Parameters.FECHAALTA)}">
        <li class="error-message"><fmt:message key="${error}" bundle="${messages}" /></li>
    </c:forEach>
	<input type="date" name="fechaAlta" value="${sessionScope.empleado.fechaAlta}"/>
	
	<input type="submit" value="<fmt:message key="update" bundle="${messages}"/>" />
	
</form>

<%@include file="/common/footer.jsp"%>
