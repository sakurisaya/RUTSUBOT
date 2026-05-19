<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>RUTSUBOT</title>
    </head>

    <body>
        <h1>RUTSUBOTへようこそ</h1>
        <form action="Login" method="post">
            ユーザー名：<input type="text" name="name"><br>
            パスワード：<input type="password" name="pass"><br>
            <input type="submit" value="ログイン">
        </form>
        <p><a href="Register">新規登録はこちら</a></p>
    </body>

    </html>