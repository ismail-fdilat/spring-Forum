<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<a href="<%=request.getContextPath()%>/accueil">-> Tous les catégories</a>/<br>
		<c:if test="${not empty sessionScope.nav.idCat }">
		----><a href="<%=request.getContextPath()%>/categorie/${sessionScope.nav.idCat}/">${sessionScope.nav.CDescription}</a>/<br>
		<c:if test="${not empty sessionScope.nav.idSCat }">
		--------><a href="<%=request.getContextPath()%>/sousCategorie/${sessionScope.nav.idSCat}/">${sessionScope.nav.SCDescription}</a>/<br>
		<c:if test="${not empty sessionScope.nav.idSuj }">
		----------------><a href="<%=request.getContextPath()%>/sujet/${sessionScope.nav.idSuj}/">${sessionScope.nav.SDescription}</a>	
		</c:if>
		</c:if>
		</c:if>
</body>
</html>