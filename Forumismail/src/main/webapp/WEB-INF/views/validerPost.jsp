<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validation des Publications</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css"/>" />
</head>
<body>
	<div class="div">
		<c:import url="entete.jsp"/>
	</div>
	<form:form modelAttribute="validationModel" method="post"
		action="${pageContext.servletContext.contextPath}/validerPost/${post.id}/${post.idPage}">
		<div class="div">
			<table>
				<tr>
					<td valign="top" align="center"><c:import url="profil.jsp"></c:import>
					</td>
					<td valign="top">
						<div class="divContenu">
							<table >
								<tr>
									<td width="130" class="border" >Description</td>
									<td  colspan="2" align="right" width="420" class="border">Valider?</td>
								</tr>
								<c:if test="${not empty categories}">
									<tr>
										<td colspan="3" bgcolor="#00ffff" class="border">
											Liste des catégories à valider
										</td>
										<c:forEach items="${categories}" var="i">
											<tr >
												<td colspan="2"  class="border">${i.description}</td>
												<td align="right" width="80"  class="border"><form:checkbox path="categories" value="${i.id}" />
												</td>
											</tr>
										</c:forEach>
								</c:if>
								<c:if test="${not empty sousCategories}">
									<tr>
										<td colspan="3" bgcolor="#00ffff" class="border">
											Liste des sous catégories à valider
										</td>
									</tr>
										<c:forEach items="${sousCategories}" var="i">
											<tr>
												<td colspan="2"   class="border">${i.description}</td>
												<td align="right"   class="border"><form:checkbox path="SousCategories" value="${i.id}" />
												</td>
											</tr>
										</c:forEach>
								</c:if>
								<c:if test="${not empty sujets}">
									<tr>
										<td colspan="3" bgcolor="#00ffff"  class="border">
											Liste des sujets à valider
										</td>
									</tr>
										<c:forEach items="${sujets}" var="i">
											<tr>
												<td  class="border">${i.intitule}</td>
												<td  class="border">${i.contenu}</td>
												<td align="right" class="border"><form:checkbox path="sujets" value="${i.id}" />
												</td>
											</tr>
										</c:forEach>
								</c:if>
								<c:if test="${not empty reponses}">
									<tr>
										<td colspan="3" bgcolor="#00ffff"   class="border">
											Liste des publications à valider
										</td>
									</tr>
										<c:forEach items="${reponses}" var="i">
											<tr>
												<td class="border">${i.auteur.nom}</td>
												<td class="border">${i.contenu}</td>
												<td align="right" class="border"><form:checkbox path="reponses" value="${i.id}" />
												</td>
											</tr>
										</c:forEach>
								</c:if>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td></td><td align="right"><input type="submit" value="   Valider   " />
					<input type="reset" value="   Annuler   " /></td>
			</table>
		</div>
	</form:form>
</body>
</html>