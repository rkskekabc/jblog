<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp" />
		<div id="wrapper">
			<div id="content">
				<c:choose>
					<c:when test="${fn:length(postList) != 0 }">
						<div class="blog-content">
							<h4>${postVo.title }</h4>
							<p>${fn:replace(postVo.content, newline, "<br/>") }<p>
						</div>
					</c:when>
					<c:otherwise>
						<div class="blog-content">
							<h3>아직 작성된 글이 없습니다!</h3>
						</div>
					</c:otherwise>
				</c:choose>
				<ul class="blog-list">
					<c:forEach items="${postList }" var="vo">
						<li><a href="${pageContext.request.contextPath }/${blogVo.id }/${vo.categoryNo }/${vo.no }">${vo.title }</a> <span>${vo.regDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/${blogVo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList }" var="vo" varStatus="status">
					<li><a href="${pageContext.request.contextPath }/${blogVo.id }/${vo.no }">${vo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>