<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="admin-menu">
	<c:choose>
		<c:when test='${tab == "basic" }'>
			<li class="selected"><a href="${pageContext.request.contextPath }/${blogVo.id }/admin/basic">기본설정</a></li>
			<li><a href="${pageContext.request.contextPath }/${blogVo.id }/admin/category">카테고리</a></li>
			<li><a href="${pageContext.request.contextPath }/${blogVo.id }/admin/write">글작성</a></li>
		</c:when>
		<c:when test='${tab == "category" }'>
			<li><a href="${pageContext.request.contextPath }/${blogVo.id }/admin/basic">기본설정</a></li>
			<li class="selected"><a href="${pageContext.request.contextPath }/${blogVo.id }/admin/category">카테고리</a></li>
			<li><a href="${pageContext.request.contextPath }/${blogVo.id }/admin/write">글작성</a></li>
		</c:when>
		<c:when test='${tab == "write" }'>
			<li><a href="${pageContext.request.contextPath }/${blogVo.id }/admin/basic">기본설정</a></li>
			<li><a href="${pageContext.request.contextPath }/${blogVo.id }/admin/category">카테고리</a></li>
			<li class="selected"><a href="${pageContext.request.contextPath }/${blogVo.id }/admin/write">글작성</a></li>
		</c:when>
	</c:choose>
</ul>