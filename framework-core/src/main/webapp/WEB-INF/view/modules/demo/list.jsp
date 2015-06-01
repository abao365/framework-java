<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>列表页面</title>
<script type="text/javascript">
	function onSubmitFind(currentPage){
		document.getElementById("currentPage").value = currentPage;
		document.getElementById("findByUser").submit();
	}

</script>
</head>
<body>
<a href="${pageContext.request.contextPath}/demo/add">添加用户</a>
<form id="findByUser" action="${pageContext.request.contextPath}/demo/find" method="post">
<table border="1" width="800px" style="text-align:center" align="center">
	<thead>
		<tr>
			<td>用户名</td>
			<td colspan="4"><input type="text" name="userName" value="${query.userName }" /></td>
			<td><input type="button" onclick="onSubmitFind(1)" value="查询" /></td>
		</tr>
		<tr>
			<td>UUID</td>
			<td>用户名</td>
			<td>性别</td>
			<td>年龄</td>
			<td>密码</td>
			<td>操作</td>
		</tr>
	</thead>
	<c:if test="${page.pageState }">
	<tbody>
		<c:forEach var="demo" items="${page.data }">
		<tr>
			<td>${demo.uuid }</td>
			<td>${demo.userName }</td>
			<td>${demo.sex }</td>
			<td>${demo.age }</td>
			<td>${demo.password }</td>
			<td><a href="${pageContext.request.contextPath}/demo/get/${demo.uuid }">详情</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/demo/update/${demo.uuid }">修改</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/demo/del/${demo.uuid }">删除</a></td>
		</tr>
		</c:forEach>
	</tbody>
	</c:if>
	<tfoot>
		<tr>
			<td colspan="6">
			<c:if test="${page.totalPages > 1 and page.currentPage > 1}"><a href="javascript:void(0);" onclick="onSubmitFind(1)">首页</a>&nbsp;
			<a href="javascript:void(0);" onclick="onSubmitFind(${page.currentPage-1})">上一页</a>
			</c:if>
			当前${page.currentPage }页/共${page.totalPages }页/每页
			<input type="text" style="width:20px; height:20px;" value="${page.pageSize}" name="pageSize" />条&nbsp;
			<c:if test="${page.totalPages > 1 and page.currentPage < page.totalPages}">
			<a href="javascript:void(0);" onclick="onSubmitFind(${page.currentPage+1})">下一页</a>&nbsp;
			<a href="javascript:void(0);" onclick="onSubmitFind(${page.totalPages})">尾页</a></c:if>
			<input type="text" style="width:20px; height:20px;" value="${page.currentPage}" name="currentPage" id="currentPage" />
			<input type="submit" name="go" value="跳转" />
			</td>
		</tr>
	</tfoot>
	
	<c:if test="${!page.pageState }">
		<tr><td colspan="6">没有数据</td></tr>
	</c:if>
</table>
</form>
</body>