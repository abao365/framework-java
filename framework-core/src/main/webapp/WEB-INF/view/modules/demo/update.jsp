<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>详情页</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/demo/find">列表页面</a>
<a href="${pageContext.request.contextPath}/demo/add">添加用户</a>
<form id="findByUser" action="${pageContext.request.contextPath}/demo/update" method="post">
<table border="1" width="400px" style="text-align:center" align="center">
	<tr>
		<td colspan="2">用户更新<input type="hidden" name="uuid" value="${demo.uuid}" /></td>
	</tr>
	<tr>
		<td>用户名:</td>
		<td><input type="text" name="userName" value="${demo.userName}" /></td>
	</tr>
	<tr>
		<td>密码:</td>
		<td><input type="text" name="password" value="${demo.password}" /></td>
	</tr>
	<tr>
		<td>性别:</td>
		<td><input type="text" name="sex" value="${demo.sex}" /></td>
	</tr>
	<tr>
		<td>年龄:</td>
		<td><input type="text" name="age" value="${demo.age}" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="更新"></td>
	</tr>
</table>
</form>
</body>