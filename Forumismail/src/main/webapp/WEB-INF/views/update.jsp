<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css"/>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<br>
	<center>
		<table>
			<tr>
				<td><img alt="inscription"
					src=<c:url value="/resources/images/insc.png"/> />
				<td align="right">
					<h3>
						Si Vous êtes déjà membre, <a
							href="${pageContext.servletContext.contextPath}/Log"> cliquer ici </a>
					</h3> <form:form modelAttribute="auteur" action="${pageContext.servletContext.contextPath}/update"
						method="post" enctype="multipart/form-data">
						<fieldset >
							<legend align="left">Inscription</legend>
							<table class="tableStyle">
								<tr>
									<td>Nom:</td>
									<td><form:input path="nom" size="30" maxlength="60" /></td>
									<td><form:errors path="nom" cssClass="erreur" /></td>
									<c:if test="${auteur.id!=null}">
										<td rowspan="7"><img src="${pageContext.servletContext.contextPath}/photoAuteur?id=${auteur.id}"
											id="ph" WIDTH=75 HEIGHT=75></td>
									</c:if>
									<c:if test="${auteur.id==null}">
										<td rowspan="7"><img src="" id="ph" WIDTH=75 HEIGHT=75></td>
									</c:if>
								</tr>
								<tr>
									<td>Prenom:</td>
									<td><form:input path="prenom" size="30" maxlength="60" /></td>
									<td><form:errors path="prenom" cssClass="erreur" /></td>
								</tr>
								<tr>

									<td>Email:</td>
									<td><form:input type="email" path="email" size="30"
											maxlength="60" /></td>
									<td><form:errors path="email" cssClass="erreur" /></td>
								</tr>
								<tr>
									<td>Password:</td>
									<td><form:password path="password" size="30"
											maxlength="60" placeholder="Password" /></td>
									<td><form:errors path="password" cssClass="erreur" /></td>
								</tr>
								<tr>
									<td>Date naissance :</td>
									<fmt:formatDate value="${auteur.dateNaissance}"
										var="dateString" pattern="dd/MM/yyyy" />
									<td><form:input path="dateNaissance" size="30"
											maxlength="60" value="${dateString }"
											placeholder="dd/MM/yyyy" /></td>
									<td><form:errors path="dateNaissance" cssClass="erreur" /></td>
								</tr>
								<tr>
									<td>Pays :</td>
									<td><form:input path="pays" size="30" maxlength="60" /></td>
									<td><form:errors path="pays" cssClass="erreur" /></td>
								</tr>
								<tr>
									<td>Sexe :</td>
									<td colspan="2"> 
									    Masculin <form:radiobutton	path="sexe" value="M" /> || 
									    Féminin <form:radiobutton path="sexe" value="F" />
									    <%-- <td><form:select path="sexe">
											<form:option value="M">M</form:option>
											<form:option value="F">F</form:option>
										</form:select> --%>
									</td>
								</tr>

								<tr>
									<td>Photo :</td>
									<td colspan="3"><input type="file" name="file"
										id="fileUpload" accept=".jpg,.jpeg,.png" onchange="verifier()"></td>
								</tr>
								<tr>
									<td>
									<td colspan="3"><input type="submit" value="Enregistrer" />
									<input type="submit" name="Annuler" value="Annuler" /></td>
								</tr>
							</table>
						</fieldset>
					</form:form>
				</td>
			</tr>
		</table>
	</center>
</body>
</html>