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
<a href="${pageContext.request.contextPath}/demo/add">添加</a>
<a href="${pageContext.request.contextPath}/demo/update/${demo.uuid}">更新</a>
<table border="1" width="400px" style="text-align:center" align="center">
	<tr>
		<td colspan="2">用户信息</td>
	</tr>
	<tr>
		<td>用户编号:</td>
		<td>${demo.uuid}</td>
	</tr>
	<tr>
		<td>用户名:</td>
		<td>${demo.userName}</td>
	</tr>
	<tr>
		<td>密码:</td>
		<td>${demo.password}</td>
	</tr>
	<tr>
		<td>性别:</td>
		<td>${demo.sex}</td>
	</tr>
	<tr>
		<td>年龄:</td>
		<td>${demo.age}</td>
	</tr>
	<tr>
		<td>注册时间:</td>
		<td>${demo.createTime}</td>
	</tr>
</table>
</body>




