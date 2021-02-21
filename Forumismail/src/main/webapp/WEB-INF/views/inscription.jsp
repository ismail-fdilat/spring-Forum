<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css"/>" />
<meta charset="UTF-8">
<title>Inscription</title>
<script type="text/javascript">
	function verifier() {
		var fileUpload = document.getElementById("fileUpload");
		if (typeof (fileUpload.files) != "undefined") {
			var size = parseFloat(fileUpload.files[0].size / 1024).toFixed(2);
			if (size > 50) {
				alert(size
						+ " KB.\n veuillez séléctionner une image de taille inférieur à 50 ko");
				fileUpload.value = "";
				var v = document.getElementById("ph");
				v.src = "";
			} else {
				var v = document.getElementById("ph");
				v.setAttribute("src", fileUpload.value);
			}
		} else {
			alert("This browser does not support HTML5.");
		}
	}
</script>
</head>
<body>
<div class="div">
	<table>
		<tr>
			<td><img alt="Inscription"
				src=<c:url value="/resources/images/insc.png"/> />
			<td>
				<h3>
					Si vous êtes déjà membre, <a
						href="${pageContext.servletContext.contextPath}/Log"> cliquer
						ici</a>
				</h3> <form:form modelAttribute="auteur"
					action="${pageContext.servletContext.contextPath}/addAuteur"
					method="post" enctype="multipart/form-data">
					<fieldset>
						<legend align="left">Inscription</legend>
						<table>
							<tr>
								<td>Nom:</td>
								<td><form:input path="nom" /></td>
								<td><form:errors path="nom" class="erreur"></form:errors></td>
								<c:if test="${auteur.id!=null}">
									<td rowspan="7"><img src="photoAuteur?id=${auteur.id}"
											id="ph" WIDTH=75 HEIGHT=75></td>
								</c:if>
								<c:if test="${auteur.id==null}">
									<td rowspan="7"><img src="" id="ph" WIDTH=75 HEIGHT=75></td>
								</c:if>
							</tr>
							<tr>
								<td>Prenom:</td>
								<td><form:input path="prenom" /></td>
								<td><form:errors path="prenom" class="erreur"></form:errors></td>
							</tr>
							<tr>
								<td>Email:</td>
								<td><form:input path="email" /></td>
								<td><form:errors path="email" class="erreur"></form:errors></td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><form:password path="password" /></td>
								<td><form:errors path="password" class="erreur"></form:errors></td>
							</tr>
							<tr>
								<fmt:formatDate value="${auteur.dateNaissance}" var="dateString"
									pattern="dd/MM/yyyy" />
								<td>Date de naissance:</td>
								<td><form:input path="dateNaissance" value="${dateString}"
										placeholder="dd/MM/yyyy" /></td>
								<td><form:errors path="dateNaissance" class="erreur"></form:errors></td>
							</tr>
							<tr>
								<td>Pays:</td>
								<td><form:input path="pays" /></td>
								<td><form:errors path="pays"></form:errors></td>
							</tr>
							<tr>
								<td>Sexe:</td>
								<td>Masculin<form:radiobutton path="sexe" value="M" /> ||
									Féminin<form:radiobutton path="sexe" value="F" />
								</td>
							</tr>
							<tr>
								<td colspan="2"><input type="file" name="file"
									id="fileUpload" accept=".jpg,.jpeg,.png" onchange="verifier()"></td>
							</tr>
							<tr>
								<td>
								<td><input type="submit" value="Enregistrer">
						</table>
					</fieldset>
				</form:form>
	</table>
</div>
</body>
</html>