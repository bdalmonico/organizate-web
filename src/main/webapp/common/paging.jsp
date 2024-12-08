<c:url value="${baseURL}" var="firstPageURL">  
		<c:param name="page" value="1"/>    
</c:url>	
			
<a href="${firstPageURL}">Primera</a>
		
<c:url value="${baseURL}" var="previousPageURL">  
		<c:param name="page" value="${currentPage-1}"/>    
</c:url>

<a href="${previousPageURL}">Anterior</a>

<c:forEach var="i" begin="${fromPage}" end="${toPage}" step="1">
	<c:url value="${baseURL}" var="pageURL">  
			<c:param name="page" value="${i}"/>    
	</c:url>
	&nbsp;<a href="${pageURL}">${i}</a>&nbsp;
</c:forEach>  
								
<c:url value="${baseURL}" var="nextPageURL">  
		<c:param name="page" value="${currentPage+1}"/>    
</c:url>
<a href="${nextPageURL}">Siguiente</a>

<c:url value="${baseURL}" var="lastPageURL">  
		<c:param name="page" value="${lastPage}"/>    
</c:url>
<a href="${lastPageURL}">Ultima</a>


<p>${resultados.total} resultados encontrados</p>	
</div>
</body>
</html>