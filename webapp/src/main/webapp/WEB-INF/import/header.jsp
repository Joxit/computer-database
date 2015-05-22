<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/dashboard"><spring:message
				code="global.title" /></a>
		<sec:authorize access="not isAnonymous()">
			<form class="navbar-right navbar-brand" method="get"
				action="<c:url value="/logout" />">
				<input class="btn btn-primary" type="submit" value="Log out" /> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</sec:authorize>
		<mylib:lang id="${param.id }" />
	</div>
</header>