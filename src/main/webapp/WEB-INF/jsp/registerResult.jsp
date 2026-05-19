<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="model.User" %>
        <% // リクエストスコープから登録結果（true/false）を取得 boolean result=(boolean) request.getAttribute("result"); %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>RUTSUBOT-ユーザー登録結果</title>
            </head>

            <body>
                <h1>ユーザー登録結果</h1>
                <% if(result) { %>
                    <p>登録が完了しました。</p>
                    <a href="index.jsp">topページへ</a>
                    <% } else { %>
                        <p>登録に失敗しました</p>
                        <a href="Register">登録画面へ戻る</a>
                        <% } %>
            </body>

            </html>