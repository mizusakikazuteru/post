<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
<style type="text/css">
* {
	padding: 5px;
	margin: 0px;
}

body {
	textalign: center;
}

table {
	width: 800px;
	background: while;
	border: 2px black solid;
	border-collapse: collapse;
}

th {
	border: 1px black solid;
	background: #CCCCFF;
}

td {
	border: 1px black solid;
}

li {
	display: inline-block;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>

	<p>
		お疲れ様です
		<c:out value="${loginUser .name}" />
		さん
	</p>
	<c:if test="${ empty loginUser .name }">
		<a href="login">ログイン</a>
	</c:if>
	<center>
		<h1>ホーム画面</h1>
		<c:if test="${ not empty loginUser .name }">
			<ul id="menu">
				<li><a href="post">新規投稿</a></li>





	<c:out value="${branch .id}" />
	<li><a href="management.jsp">ユーザー管理</a></li>

	<c:if test="${ empty branch .id }">
		<a href="home"></a>
	</c:if>
	<li><a href="logout">ログアウト</a></li>
			</ul>
		</c:if>

		<form action="home" method="post">
			<table>
				<tr>
					<th>件名</th>
					<td><input type="text" name="title" maxlength="50" size="90" /></td>
				</tr>
				<tr>
					<th>本文</th>
					<td><textarea name="messages" cols="70" rows="20" /></textarea></td>
				</tr>
				<tr>
					<th>カテゴリー</th>
					<td><input type="text" name="category" maxlength="10"
						size="10" />&nbsp;&nbsp;&nbsp;&nbsp; <select><option>期間指定</option></select></td>
				</tr>
				<tr>
					<th>登録日時</th>
					<td><input type="text" name="created_at" maxlength="10"
						size="20" />&nbsp;&nbsp;&nbsp;&nbsp; <select><option>期間指定</option></select></td>
				</tr>
				<tr>
					<th>登録者</th>
					<td><input type="text" name="created_at" maxlength="10"
						size="20" /></td>
				</tr>
				<tr>
					<th>コメント</th>
					<td><textarea name="messages" cols="70" rows="20" /></textarea></td>
				</tr>
				<tr>
					<td colspan="2" id="button"><a href="./">ホームに戻る</a></td>
				</tr>
			</table>

		</form>
	</center>
</body>
</html>