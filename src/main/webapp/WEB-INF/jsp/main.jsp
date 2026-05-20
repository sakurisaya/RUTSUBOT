<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ page import="model.User,model.Mutter,java.util.List" %>
<%
User loginUser = (User) session.getAttribute("loginUser");
List<Mutter> mutterList = (List<Mutter>) request.getAttribute("mutterList");
String errorMsg = (String) request.getAttribute("errorMsg");
    if (errorMsg == null) {
      errorMsg = (String) session.getAttribute("errorMsg");
      session.removeAttribute("errorMsg");// 一度表示したら消す（重要！）
    }
%>
        <!DOCTYPE html>
        <html>

        <head>
          <meta charset="UTF-8">
          <title>RUTSUBOT</title>
        </head>

        <body>
          <h1>RUTSUBOTメイン</h1>
          <p>
            <%= loginUser.getName() %>さん、ログイン中
              <a href="Logout">ログアウト</a>
          </p>
          <p><a href="Main">更新</a></p>
          <form action="Main" method="post">
            <input type="text" name="text">
            <input type="submit" value="つぶやく">
          </form>
          <form action="SearchMutter" method="post">
            <input type="text" name="keyword" placeholder="検索">
            <input type="submit" value="検索">
          </form>
          <% if(errorMsg !=null){ %>
            <p style="color:red;">
              <%= errorMsg %>
            </p>
            <% } %>
              <% if(mutterList !=null) { %>
                <% for(Mutter mutter : mutterList){%>
                  <div style="border-bottom: 1px solid gray; padding-block: 5px;">
                    <div style="font: small;">
                      <span style="color: gray; font-size: 13px"><%=mutter.getCreatedAt() %></span>
                      <span style="font-weight:bold; margin-inline:5px;"><%=mutter.getUserName()%>さん</span>
                      <% if(mutter.getUserId() == loginUser.getId()) {%>
                      <a href="UpdateMutter?id=<%= mutter.getId() %>">編集</a>
                      <a href="DeleteMutter?id=<%= mutter.getId() %>" onclick="return confirm('本当に削除しますか？')">削除</a>
                      <% } %>
                      <form method="post" action="Like" style="display: inline;">
                          <input type="hidden" name="mutterId" value="<%= mutter.getId() %>" />
                              <button type="submit" style="border:none;background:none;color:#ff4500;">
                                  ♡ <%= mutter.getLikeCount() %>
                              </button>
                      </form>
                    </div>
                    <p style="margin-block: 5px;"><%=mutter.getText()%></p>
                    </div>
              <% } %>
              <% } %>
        </body>

        </html>