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
<h1>abonnés</h1>

<c:forEach items="${sessionScope.auteur.followers}" var="i" >
   
     
											    <c:choose>
													<c:when test="${i.photo!=null}">
														<img
															src="${pageContext.servletContext.contextPath}/photoAuteur?id=${i.id}"
															WIDTH=50 HEIGHT=50>
													</c:when>
													<c:when test="${i.sexe=='F'}">
														<img src="<c:url value="/resources/images/profilF.png"/>"
															WIDTH=50 HEIGHT=50>
													</c:when>
													<c:when test="${i.sexe=='M'}">
														<img src="<c:url value="/resources/images/profilH.png"/>"
															WIDTH=50 HEIGHT=50>
													</c:when>
												</c:choose> 	
	                                                         ${i.nom} ${i.prenom}
	                                                         <br>
</c:forEach>
</body>
</html>