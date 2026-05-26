<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RUTSUBOT - ログイン結果</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="app-container">
        <aside class="sidebar">
            <div class="logo-area">
                <h1 class="logo-title">RUTSUBOT</h1>
                <p class="logo-subtitle">混ざり合う、みんなの日常とつぶやきのるつぼ</p>
            </div>
        </aside>
        
        <main class="main-content">
            <div class="card">
                <h2>ログイン結果</h2>
                <% if(loginUser != null) { %>
                    <div class="alert alert-success">
                        ログインに成功しました！<br>
                        <strong><%= loginUser.getName() %></strong> さん、ようこそ！
                    </div>
                    <div class="btn-group" style="margin-top: 1.5rem;">
                        <a href="Main" class="btn">つぶやきタイムラインへ</a>
                    </div>
                <% } else { %>
                    <div class="alert alert-danger">
                        ログインに失敗しました。
                        <% if(errorMsg != null){ %>
                            <br><span style="font-size: 0.9rem; font-weight: normal;"><%= errorMsg %></span>
                        <% } %>
                    </div>
                    <div class="btn-group" style="margin-top: 1.5rem;">
                        <a href="index.jsp" class="btn">ログイン画面へ戻る</a>
                    </div>
                <% } %>
            </div>
        </main>
    </div>
</body>
</html>