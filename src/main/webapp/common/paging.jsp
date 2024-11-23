<%-- URL para primera pagina --%>
				<%-- TODO: Solo se pone el enlace a la primera página si currentPage != 1 --%>				
				<c:url value="${baseURL}" var="firstPageURL">  
 					<c:param name="page" value="1"/>    
				</c:url>				
				<a href="${firstPageURL}">Primera</a>
	
				<%-- URL para la página anterior --%>
				<%-- TODO: Solo se pone si no estoy en la primera y hay más de dos --%>				
				<c:url value="${baseURL}" var="previousPageURL">  
 					<c:param name="page" value="${currentPage-1}"/>    
				</c:url>
				<a href="${previousPageURL}">Anterior</a>

				<c:forEach var="i" begin="${fromPage}" end="${toPage}" step="1">
					<%-- TODO <c:if si i!=current page se pone el enlace, y si no solo el número --%>
					<c:url value="${baseURL}" var="pageURL">  
 						<c:param name="page" value="${i}"/>    
					</c:url>
					&nbsp;<a href="${pageURL}">${i}</a>&nbsp;
				</c:forEach>  
				
				<%-- URL para la página siguiente --%>				
				<%-- TODO: Solo se pone si no estoy en la última y faltan más de dos para la última --%>									
				<c:url value="${baseURL}" var="nextPageURL">  
 					<c:param name="page" value="${currentPage+1}"/>    
				</c:url>
				<a href="${nextPageURL}">Siguiente</a>
				
				<%-- URL para la última página --%>
				<%-- TODO: Solo se pone el enlace si no estamos en la última pagina,
				     es decir, si currentPage!=lastPage --%>
				<c:url value="${baseURL}" var="lastPageURL">  
 					<c:param name="page" value="${lastPage}"/>    
				</c:url>
				<a href="${lastPageURL}">Ultima</a>
				
				
				<p>${resultados.total} resultados encontrados</p>	
</div>
</body>
</html>