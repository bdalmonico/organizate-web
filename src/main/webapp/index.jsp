<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
	<div style="height:100% ; width:100%; align-content: center;align-items: center; align-self: center; text-align: center;">
		<h1><fmt:message key="welcome" bundle="${messages}"/></h1>
	</div>
	
	<script>
		<!-- TODO: Pasar a un incidex-ws-client.js -->
		$(document).ready(function() {
			$("#nombre").keyup(function() {
				var nombre = $(this).val();
				$.ajax({
					type: "GET",
					url: "OrganizateWebServices/proyecto",
					data: {
						'nombre': nombre										
					},
					<!-- contentType: "" --->
					dataType: "json",
					success: function(results) {
						var htmlResultado = "<ul>";
						for (var i = 0; i< results.page.length; i++) {
							htmlResultado += "<li>"+results.page[i].nombre+" :  "+"</li>";
						}
						htmlResultado += "</ul>";
						$("#results").html(htmlResultado);
					}				
				});
			});
		});
	</script>
	<label>insira aqui o nombre:</label>
	<input id="nombre"></input>
	<div>Resultados</div>
	<div id="results"></div>
<%@include file="/common/footer.jsp" %>
