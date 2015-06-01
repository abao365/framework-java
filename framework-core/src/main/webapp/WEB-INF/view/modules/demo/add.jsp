<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>框架demo-关系型数据库操作-添加</title>
<script type="text/javascript">
	j = 1;
	$(document).ready(function(){
		$("#btn_add2").click(function(){
			document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';
			  j = j + 1;
		});
	});

	function del_1(o){
	 document.getElementById("newUpload1").removeChild(document.getElementById("div_"+o));
	}
	
	function del_2(o){
		 document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));
	}

</script>
</head>

<body>
<a href="${pageContext.request.contextPath}/demo/find">列表页面</a>
<form id="findByUser" action="${pageContext.request.contextPath}/demo/add" method="post">
<table border="1" width="400px" style="text-align:center" align="center">
	<tr>
		<td colspan="2">添加</td>
	</tr>
	<tr>
		<td>用户名:</td>
		<td><input type="text" name="userName" value="${user.userName}" /></td>
	</tr>
	<tr>
		<td>密码:</td>
		<td><input type="text" name="password" value="${user.password}" /></td>
	</tr>
	<tr>
		<td>性别:</td>
		<td><input type="text" name="sex" value="${user.sex}" /></td>
	</tr>
	<tr>
		<td>年龄:</td>
		<td><input type="text" name="age" value="${user.age}" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="添加"></td>
	</tr>
</table>
</form>
<hr>
<form id="findByUser" action="${pageContext.request.contextPath}/demo/upload" enctype="multipart/form-data" method="post">
<table border="1" width="400px" style="text-align:center" align="center">
	<tr>
		<td colspan="2">上传图片</td>
	</tr>
	<tr>
		<td>文件:</td>
		<td><input type="file" name="file" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="上传"></td>
	</tr>
</table>
</form>
<hr>
<form id="findByUser" action="${pageContext.request.contextPath}/demo/upload3" enctype="multipart/form-data" method="post">
<table border="1" width="400px" style="text-align:center" align="center">
	<tr>
		<td colspan="2">多文件上传图片</td>
	</tr>
	<tr>
		<td>文件1:</td>
		<td><input type="file" name="file" /></td>
	</tr>
	<tr>
		<td>文件2:</td>
		<td><input type="file" name="file1" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="上传"></td>
	</tr>
</table>
</form>
</body>
</html>

