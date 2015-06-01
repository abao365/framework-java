<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>框架demo</title>
</head>
<body>
<ol>
	<li><a href="${pageContext.request.contextPath}/demo/find" target="_blank">关系型数据库操作</a></li>
	<li><a href="${pageContext.request.contextPath}/demo/home/data" target="_blank">Mongodb和Memcache(JSON)</a></li>
	<li><a href="${pageContext.request.contextPath}/demo/home/data?callback=call" target="_blank">Mongodb和Memcache(JSONP)</a></li>
	<li><a href="${pageContext.request.contextPath}/demo/add" target="_blank">上传图片</a></li>
	<li><a href="${pageContext.request.contextPath}/demo/image/create" target="_blank">验证码</a></li>
</ol>
</body>
</html>

