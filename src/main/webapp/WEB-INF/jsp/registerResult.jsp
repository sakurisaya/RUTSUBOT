<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<% 
    // リクエストスコープから登録結果（true/false）を取得 
    boolean result=(boolean) request.getAttribute("result"); 
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RUTSUBOT - ユーザー登録結果</title>
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
                <h2>ユーザー登録結果</h2>
                <% if(result) { %>
                    <div class="alert alert-success">
                        ユーザー登録が完了しました！
                    </div>
                    <div class="btn-group" style="margin-top: 1.5rem;">
                        <a href="index.jsp" class="btn">ログイン画面へ</a>
                    </div>
                <% } else { %>
                    <div class="alert alert-danger">
                        ユーザー登録に失敗しました。ユーザー名がすでに使用されている可能性があります。
                    </div>
                    <div class="btn-group" style="margin-top: 1.5rem;">
                        <a href="Register" class="btn">登録画面へ戻る</a>
                        <a href="index.jsp" class="btn btn-secondary">トップへ戻る</a>
                    </div>
                <% } %>
            </div>
        </main>
    </div>
</body>
</html>