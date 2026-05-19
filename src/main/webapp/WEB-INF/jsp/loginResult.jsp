<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RUTSUBOT</title>
</head>
<body>
<h1>RUTSUBOTログイン</h1>
<% if(loginUser != null) { %>
  <p>ログインに成功しました</p>
  <p>ようこそ<%= loginUser.getName() %>さん</p>
  <a href="Main">つぶやき投稿・閲覧へ</a>
<% } else { %>
  <p>ログインに失敗しました</p>
  <% if(errorMsg !=null){ %>
  <p style="color:red;">
    <%= errorMsg %>
  </p>
  <% } %>

  <a href="index.jsp">トップへ</a>
<% } %>
</body>
</html>