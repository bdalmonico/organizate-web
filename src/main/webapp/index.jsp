<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
	<div style="height:100% ; width:100%; align-content: center;align-items: center; align-self: center; text-align: center;">
		<h1><fmt:message key="welcome" bundle="${messages}"/></h1>
	</div>
	
	
	<label>insira aqui o nombre:</label>
	<input id="nombre"></input>
	<div>Resultados</div>
	<div id="results"></div>
<%@include file="/common/footer.jsp" %>
