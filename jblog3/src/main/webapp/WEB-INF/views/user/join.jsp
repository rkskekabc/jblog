<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" ></script>
<script>
	$(function(){
		$('#blog-id').change(function(){
			$('#btn-checkid').show();
			$('#img-checkid').hide();
		});
		$('#btn-checkid').click(function(){
			var id = $('#blog-id').val();
			if(id == ''){
				return;
			}
			
			/* ajax 통신 */
			$.ajax({
				url: "${pageContext.servletContext.contextPath }/user/api/checkid?id=" + id,
				type: "get",
				dataType: "json",
				data: "",
				success: function(response){
					if(response.result != "success"){
						console.error(response.message);
						return;
					}
					
					if(response.data == true){
						alert('이미 존재하는 아이디입니다. \n다른 아이디을 사용해 주세요.');
						$("#blog-id").focus();
						$("#blog-id").val("");
						return;
					}

					$('#btn-checkid').hide();
					$('#img-checkid').show();
				},
				error: function(xhr, error){
					console.error("error:" + error);
				}
			});
			
			console.log(id);
		});
	});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url='/WEB-INF/views/includes/menu.jsp'></c:import>
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input id="name" path="name" />
			<p style="font-weight: bold; color: #f00; text-align: left; padding:0; margin:0">
				<form:errors path="name" />
			</p>
			<label class="block-label" for="blog-id">아이디</label>
			<!-- input id="blog-id" name="id" type="text" -->
			<form:input id="blog-id" path="id"/> 
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="font-weight: bold; color: #f00; text-align: left; padding:0; margin:0">
				<form:errors path="id" />
			</p>
			<label class="block-label" for="password">패스워드</label>
			<form:password id="password" path="password" />
			<p style="font-weight: bold; color: #f00; text-align: left; padding:0; margin:0">
				<form:errors path="password" />
			</p>
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
