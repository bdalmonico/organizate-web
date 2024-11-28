<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<div class="container">
	<%
	ClienteDTO cli = (ClienteDTO) request.getAttribute(Attributes.CLIENTE);
	%>

	<p>
		<b><fmt:message key="id2p" bundle="${messages}"/></b>
		<%=cli.getId()%><br>
	</p>
	<p>
		<b><fmt:message key="name2p" bundle="${messages}"/></b> <%=cli.getNombre()%>
	</p>

	<p>
		<b><fmt:message key="email2p" bundle="${messages}"/></b> <%=cli.getEmail()%>
	</p>
	


</div>


<%@include file="/common/footer.jsp"%>
