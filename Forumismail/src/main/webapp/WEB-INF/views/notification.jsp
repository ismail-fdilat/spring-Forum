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
<h1>notification</h1>

<c:forEach items="${reponse}" var="i">
										
											    <c:choose>
													<c:when test="${i.auteur.photo!=null}">
														<img
															src="${pageContext.servletContext.contextPath}/photoAuteur?id=${i.auteur.id}"
															WIDTH=50 HEIGHT=50>
													</c:when>
													<c:when test="${i.auteur.sexe=='F'}">
														<img src="<c:url value="/resources/images/profilF.png"/>"
															WIDTH=50 HEIGHT=50>
													</c:when>
													<c:when test="${i.auteur.sexe=='M'}">
														<img src="<c:url value="/resources/images/profilH.png"/>"
															WIDTH=50 HEIGHT=50>
													</c:when>
												</c:choose> 	
											<p> <b>${i.auteur.prenom } ${i.auteur.nom}</b> a publier la reponse""""" <b> ${i.contenu}    </b> """"" 
											le  ${i.datePost} </p>
												<c:if test="${not empty sessionScope.auteur}">	
													 <c:if test="${empty sessionScope.auteur.followings}">
                                            <a href="${pageContext.servletContext.contextPath}/follow/${i.auteur.id}/${i.id}"> <button>s'abonné</button><a>
												</c:if>	
                                                <c:forEach items="${sessionScope.auteur.followings}" var="l">
                                                <c:choose>
                                                <c:when test="${l.id != i.auteur.id}">
												<a href="${pageContext.servletContext.contextPath}/follow/${i.auteur.id}/${i.id}"> <button>s'abonné</button><a>
												</c:when>
												<c:when test="${l.id == i.auteur.id}">
												<a href="${pageContext.servletContext.contextPath}/follow/${i.auteur.id}/${i.id}"> <button>se désabonné</button><a>
												</c:when>
												</c:choose>
												</c:forEach>		 	
												</c:if>
												<br>
												<br>

									</c:forEach>

</body>
</html>