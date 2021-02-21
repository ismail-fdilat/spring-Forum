<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="divProfil">
		<c:choose>
			<c:when test="${not empty sessionScope.auteur}">
					 		${sessionScope.auteur.nom } ${sessionScope.auteur.prenom} <br>
				<c:choose>
					<c:when test="${sessionScope.auteur.photo!=null}">
						<img
							src="${pageContext.servletContext.contextPath}/photoAuteur?id=${sessionScope.auteur.id}"
							WIDTH=75 HEIGHT=75>
						<br />
					</c:when>
					<c:when test="${sessionScope.auteur.sexe=='F'}">
						<img src="<c:url value="/resources/images/profilF.png"/>" WIDTH=75
							HEIGHT=75>
						<br />
					</c:when>
					<c:when test="${sessionScope.auteur.sexe=='M'}">
						<img src="<c:url value="/resources/images/profilH.png"/>" WIDTH=75
							HEIGHT=75>
						<br />
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
							Visiteur <br />
				<img src="<c:url value="/resources/images/visiteur.png"/>" WIDTH=75
					HEIGHT=75>
			</c:otherwise>
		</c:choose>
		<br>
		
		<c:choose>
			<c:when test="${not empty sessionScope.auteur}">
		       	<a href="${pageContext.servletContext.contextPath}/abonnés/${post.id}/${post.idPage}">abonnés ${sessionScope.auteur.follower}</a><br>
		       	<a href="${pageContext.servletContext.contextPath}/abonnements/${post.id}/${post.idPage}">abonnements ${sessionScope.auteur.following}</a><br>
		       	<a href="${pageContext.servletContext.contextPath}/notification/${post.id}/${post.idPage}">notification</a><br>
				<a href="${pageContext.servletContext.contextPath}/deconnecter/${post.id}/${post.idPage}">se déconnecter</a><br>
				<a href="${pageContext.servletContext.contextPath}/updateAuteur/${post.id}/${post.idPage}">Profil</a><br>
				<c:if test="${sessionScope.auteur.role=='ADMIN' || sessionScope.auteur.role=='MODERATOR' }">
					<a href="${pageContext.servletContext.contextPath}/validation/${post.id}/${post.idPage}">Administration </a> 
				</c:if>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.servletContext.contextPath}/Log/${post.id}/${post.idPage}">Se connecter</a><br>
				<a href="${pageContext.servletContext.contextPath}/inscription/${post.id}/${post.idPage}">S'inscrire</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>