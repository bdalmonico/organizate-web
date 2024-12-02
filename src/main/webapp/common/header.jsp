<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bruno.org.model.*"%>
<%@ page import="com.bruno.training.web.controller.*"%>
<%@ page import="com.bruno.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<fmt:setLocale value="${sessionScope['locale']}" />
<fmt:setBundle basename="resources.i18n.Messages" var="messages" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/styles.css" />
<title>Organizate</title>
</head>
<body>


	<header>
		
		<div class="logo"><a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/imgs/logo.webp" width="100" height="60"/></a></div>
		<h1><fmt:message key="welcome" bundle="${messages}"/></h1>
		<div class="header-right">
			<div class="language-options">
				<a
					href="<%=request.getContextPath()%>/UsuarioServlet?action=change-locale&locale=en">English</a>
				<a
					href="<%=request.getContextPath()%>/UsuarioServlet?action=change-locale&locale=es">Español</a>
				<a
					href="<%=request.getContextPath()%>/UsuarioServlet?action=change-locale&locale=pt">Português</a>
			</div>
			<button class="settings"><%@include file="/common/user-menu.jsp"%></button>
		</div>
		
	</header>
	


	

	<div class="coontainer">