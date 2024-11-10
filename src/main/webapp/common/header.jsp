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
	href="/HelloWorldWeb/css/styles.css" />
<title>Organizate</title>
</head>
<body>
	<img src="/HelloWorldWeb/imgs/logo.webp" width="100" height="60" />
	<%@include file="/common/user-menu.jsp"%>
	<a
		href="<%=request.getContextPath()%>/public/UsuarioServlet?action=change-locale&locale=gl_ES">Galego</a>
	<a
		href="<%=request.getContextPath()%>/public/UsuarioServlet?action=change-locale&locale=es">Español</a>
	<a
		href="<%=request.getContextPath()%>/public/UsuarioServlet?action=change-locale&locale=en">English</a>