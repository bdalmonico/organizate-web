<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bruno.org.model.*"%>
<%@ page import="com.bruno.training.web.controller.*"%>
<%@ page import="com.bruno.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.bruno.training.web.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<fmt:setLocale value="${sessionScope['locale']}" />
<fmt:setBundle basename="resources.i18n.Messages" var="messages" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css" />
		<title>Organizate</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-3.7.1.min.js"></script>
	</head>
	<body>
		<header>
			
			<div class="logo">
				<a href="<%=request.getContextPath()%>/index.jsp">
					<img src="<%=request.getContextPath()%>/imgs/logo.png"/>
				</a>
			</div>
			<div class="header-right">
				<div class="language-options">
					<a href="<%=request.getContextPath()%>/UsuarioServlet?action=change-locale&locale=en">English</a>
					<a href="<%=request.getContextPath()%>/UsuarioServlet?action=change-locale&locale=es">Español</a>
					<a href="<%=request.getContextPath()%>/UsuarioServlet?action=change-locale&locale=pt">Português</a>
				</div>
				<%@include file="/common/user-menu.jsp"%>
			</div>
		</header>
	<div class="row">	
	<%@include file="/common/menu.jsp"%>






