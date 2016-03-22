<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ログイン画面</title>
<style type="text/css">
* {
	padding: 5px;
	margin: 0px;
}

body {
	textalign: center;
	background: url("login.png") no-repeat;
	fontcolor:blue;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>

<<<<<<< HEAD
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><c:out value="${message}" /></li>
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session" />
	</c:if>
=======
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" /></li>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

>>>>>>> 4bd8a2a3afb31ce75d17c8b68710b441c7c40e07
	<h1>ユーザーログイン</h1>
	<form action="login" method="post">
		<br />
		<table>
			<tr>
				<label for="loginId"><th>ログインID</th></label>
				<td><input name="loginId" id="loginId" /></td>
			</tr>
			<br />

			<tr>
				<label for="password"><th>パスワード</th></label>
				<td><input name="password" type="password" id="password" /></td>
			</tr>
			<br />

			<tr>
				<td><input type="submit" value="ログイン" /><a href="./">戻る</a></td>
			</tr>
			<br />

		</table>
	</form>
</body>
</html>