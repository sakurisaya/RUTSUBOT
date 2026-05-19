<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RUTSUBOT-ユーザー登録</title>
</head>
</head>
<body>
<h1>ユーザー登録</h1>
<form action="Register" method="post">
    ユーザー名：<input type="text" name="name" required><br>
    パスワード：<input type="password" name="pass" required><br>
    <input type="submit" value="登録">
</form>
<a href="index.jsp">トップへ</a>
</body>
</html>