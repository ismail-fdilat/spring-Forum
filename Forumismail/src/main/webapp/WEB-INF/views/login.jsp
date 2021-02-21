<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css"/>" />
<meta charset="UTF-8">
<title>Authentification</title>
</head>
<body>
<div class="divContenu">
	<table>
		<tr>
			<td><img alt="Authentification" src="<c:url value="/resources/images/auth.jpg"/>"></td>
			<td>
				<form:form modelAttribute="identifiant" method="post" 
						   action="${pageContext.servletContext.contextPath}/Log" >
				<table>
				<c:if test="${not empty errorBean}">
					<tr>
						<td colspan="2" class="erreur">${errorBean.message }</td>
					</tr>						
				</c:if>
					<tr>
						<td>Email:</td>
						<td><form:input path="email" size="30" maxlength="60"/></td>
						<td><form:errors path="email" class="erreur"></form:errors></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><form:password path="password" size="30" maxlength="60"/></td>
						<td><form:errors path="password" class="erreur"></form:errors></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="OK"/></td>
						<td><a href="${pageContext.servletContext.contextPath}/inscription"> s'inscrire</a>
				</table>
				</form:form>
	</table>

</div>
</body>
</html>