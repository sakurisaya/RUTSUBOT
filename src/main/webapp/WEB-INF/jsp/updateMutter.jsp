<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ page import="model.User,model.Mutter" %>
<%
Mutter mutter = (Mutter) request.getAttribute("mutter");
%>
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <title>RUTSUBOT-つぶやき編集</title>
      </head>

      <body>
        <h1>つぶやき編集</h1>
        <form action="UpdateMutter" method="post">
          <input type="hidden" name="id" value="<%= mutter.getId() %>">
          <p>ユーザー名：<%= mutter.getUserName() %>
          </p>
          <p>内容<br>
            <textarea name="text" rows="4" cols="40" required id=""><%= mutter.getText() %></textarea>
          </p>
          <input type="submit" value="更新">
        </form>
        <p><a href="Main">戻る</a></p>
      </body>

      </html>