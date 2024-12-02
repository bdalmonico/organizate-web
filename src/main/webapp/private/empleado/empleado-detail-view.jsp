<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="container">
	<%
	EmpleadoDTO emp = (EmpleadoDTO) request.getAttribute(Attributes.EMPLEADO);
	%>
	<div id="image-container">
        <img alt="profile image" 
             src="<%=request.getContextPath()%>/ImageServlet?action=profileImage&id=${sessionScope.empleado.id}&imageName=profile.png" />
    </div>

	<p>
		<b><fmt:message key="id2p" bundle="${messages}"/></b>
		<%=emp.getId()%><br>
	</p>
	<p>
		<b><fmt:message key="name2p" bundle="${messages}"/></b> <%=emp.getNombre()%>
	</p>
	<p>
		<b><fmt:message key="lastname2p" bundle="${messages}"/></b> <%=emp.getApellido()%>
	</p>
	<p>
		<b><fmt:message key="email2p" bundle="${messages}"/></b> <%=emp.getEmail()%>
	</p>
	<p>
		<b><fmt:message key="password2p" bundle="${messages}"/></b> <%=emp.getContrasena()%>
	</p>
	<p>
		<b><fmt:message key="registration_date2p" bundle="${messages}"/></b> <%=emp.getFechaAlta()%>
	</p>
	<p>
		<b><fmt:message key="role2p" bundle="${messages}"/></b> <%=emp.getRolId()%>
	</p>

            <div class="nav-container">
                <div class="account">
                    <c:choose>
                        <c:when test="${sessionScope.empleado.nombre != null}">
                        
                            <a href="<%=request.getContextPath()%>/private/empleado/my-profile.jsp">
                            	<span><c:out value="${sessionScope.empleado.nombre}" /></span>
                            </a>
                            <img alt="imagen logo" height="50px" width="50px" src="<%=request.getContextPath()%>/imgs/user-icon.png" class="user-icon" />
                        </c:when>
                        <c:otherwise>
                            <span>nd</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="logo">
                    <img src="<%=request.getContextPath()%>/imgs/logo.webp" alt="Organizate">
                </div>
              
            </div>
       



</div>


<%@include file="/common/footer.jsp"%>
