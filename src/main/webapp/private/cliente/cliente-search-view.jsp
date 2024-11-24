<%@ page import="com.bruno.training.web.util.*"%>
<%@include file="/common/header.jsp"%>

<form action="<%=request.getContextPath()%>/private/ClienteServlet" method="post">
	<h3><fmt:message key="search_client" bundle="${messages}"/></h3>
	<input type="hidden" name="action" value="search" />
	<label><fmt:message key="id2p" bundle="${messages}"/></label>
	<input type="number" name="id" /> 
	<label><fmt:message key="name2p" bundle="${messages}"/></label> 
	<input type="text" name="nombre" />
	<label><fmt:message key="email2p" bundle="${messages}"/></label> 
	<input type="text" name="email" />
	<input type="submit" value="<fmt:message key="search" bundle="${messages}"/>" />
</form>


<div class="results-container">
	<!--  getPage() ? -->
	<c:choose>
		<c:when test="${not empty resultados}">
			<h3><fmt:message key="results" bundle="${messages}"/></h3>
			<ul>
				<c:forEach var="cli" items="${resultados}">
					<li>
						<b>ID: <c:out value="${cli.id}"/>:<b/>
						<a href="<%=request.getContextPath()%>/private/ClienteServlet?action=detail&id=${cli.id}">
							<c:out value="${cli.nombre}" />
						</a>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p><fmt:message key="search_more" bundle="${messages}"/></p>
		</c:otherwise>
	</c:choose>
</div>

<%@include file="/common/footer.jsp"%>
