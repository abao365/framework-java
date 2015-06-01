<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>框架demo-关系型数据库操作-图片详情页</title>
</head>
<body>
<c:if test="${not empty tfsResult}">
	<img alt="" src="${tfsResult.tfsUrl}">
</c:if>
<c:if test="${not empty tFSResults}">
<c:forEach var="item" items="${ tFSResults}">
	<img alt="" src="${item.tfsUrl}">
</c:forEach>
</c:if>
</body>
</html>

