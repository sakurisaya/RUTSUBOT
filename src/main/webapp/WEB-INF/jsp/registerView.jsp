<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RUTSUBOT - ユーザー登録</title>
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
                <h2>ユーザー登録</h2>
                <form action="Register" method="post">
                    <div class="form-group">
                        <label for="name">ユーザー名</label>
                        <input type="text" id="name" name="name" required placeholder="希望するユーザー名を入力">
                    </div>
                    <div class="form-group">
                        <label for="pass">パスワード</label>
                        <input type="password" id="pass" name="pass" required placeholder="パスワードを入力">
                    </div>
                    <div class="btn-group">
                        <button type="submit" class="btn">登録する</button>
                        <a href="index.jsp" class="btn btn-secondary">トップへ戻る</a>
                    </div>
                </form>
            </div>
        </main>
    </div>
</body>
</html>