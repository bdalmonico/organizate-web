<%-- URL para primera pagina --%>
				<%-- TODO: Solo se pone el enlace a la primera p�gina si currentPage != 1 --%>				
				<c:url value="${baseURL}" var="firstPageURL">  
 					<c:param name="page" value="1"/>    
				</c:url>				
				<a href="${firstPageURL}">Primera</a>
	
				<%-- URL para la p�gina anterior --%>
				<%-- TODO: Solo se pone si no estoy en la primera y hay m�s de dos --%>				
				<c:url value="${baseURL}" var="previousPageURL">  
 					<c:param name="page" value="${currentPage-1}"/>    
				</c:url>
				<a href="${previousPageURL}">Anterior</a>

				<c:forEach var="i" begin="${fromPage}" end="${toPage}" step="1">
					<%-- TODO <c:if si i!=current page se pone el enlace, y si no solo el n�mero --%>
					<c:url value="${baseURL}" var="pageURL">  
 						<c:param name="page" value="${i}"/>    
					</c:url>
					&nbsp;<a href="${pageURL}">${i}</a>&nbsp;
				</c:forEach>  
				
				<%-- URL para la p�gina siguiente --%>				
				<%-- TODO: Solo se pone si no estoy en la �ltima y faltan m�s de dos para la �ltima --%>									
				<c:url value="${baseURL}" var="nextPageURL">  
 					<c:param name="page" value="${currentPage+1}"/>    
				</c:url>
				<a href="${nextPageURL}">Siguiente</a>
				
				<%-- URL para la �ltima p�gina --%>
				<%-- TODO: Solo se pone el enlace si no estamos en la �ltima pagina,
				     es decir, si currentPage!=lastPage --%>
				<c:url value="${baseURL}" var="lastPageURL">  
 					<c:param name="page" value="${lastPage}"/>    
				</c:url>
				<a href="${lastPageURL}">Ultima</a>
				
				
				<p>${resultados.total} resultados encontrados</p>	
</div>
</body>
</html>