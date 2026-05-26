<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User,model.Mutter" %>
<%
Mutter mutter = (Mutter) request.getAttribute("mutter");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RUTSUBOT - つぶやき編集</title>
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
                <h2>つぶやき編集</h2>
                <form action="UpdateMutter" method="post">
                    <input type="hidden" name="id" value="<%= mutter.getId() %>">
                    
                    <div class="form-group">
                        <label>投稿者</label>
                        <p style="margin: 0; font-weight: bold; color: var(--text-primary); font-size: 1.1rem;">
                            <%= mutter.getUserName() %> さん
                        </p>
                    </div>
                    
                    <div class="form-group">
                        <label for="text">つぶやき内容</label>
                        <textarea id="text" name="text" rows="4" required placeholder="つぶやきを編集してください..."><%= mutter.getText() %></textarea>
                    </div>
                    
                    <div class="btn-group">
                        <button type="submit" class="btn">更新する</button>
                        <a href="Main" class="btn btn-secondary">キャンセル</a>
                    </div>
                </form>
            </div>
        </main>
    </div>
</body>
</html>