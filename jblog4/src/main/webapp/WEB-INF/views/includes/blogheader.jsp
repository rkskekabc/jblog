<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
	<h1><a href="${pageContext.request.contextPath }/${blogVo.id }">${blogVo.title }</a></h1>
	<ul>
		<c:choose>
			<c:when test="${authUser == null }">
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath }/${blogVo.id }/admin/basic">블로그 관리</a></li>
			</c:otherwise>
		</c:choose>
		<c:if test='${result == "fail" }'>
			<li><p style="color: red; font-weight: bold">관리 권한이 없습니다.</p></li>
		</c:if>
	</ul>
</div>