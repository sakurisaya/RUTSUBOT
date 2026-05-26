<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RUTSUBOT - ログイン</title>
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
                <h2>ログイン</h2>
                <form action="Login" method="post">
                    <div class="form-group">
                        <label for="name">ユーザー名</label>
                        <input type="text" id="name" name="name" required placeholder="ユーザー名を入力">
                    </div>
                    <div class="form-group">
                        <label for="pass">パスワード</label>
                        <input type="password" id="pass" name="pass" required placeholder="パスワードを入力">
                    </div>
                    <button type="submit" class="btn">ログイン</button>
                </form>
                
                <div style="margin-top: 2rem; border-top: 2px dashed var(--border-color); padding-top: 1.5rem;" class="text-center">
                    <p style="margin-top: 0; color: var(--text-secondary); font-weight: 700; font-size: 0.9rem;">初めてご利用ですか？</p>
                    <a href="Register" class="btn btn-secondary">新規ユーザー登録</a>
                </div>
            </div>
        </main>
    </div>
</body>
</html>