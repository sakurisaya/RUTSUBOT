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
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RUTSUBOT - ホーム</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="app-container">
        <!-- 左側サイドバー（タイトル/ロゴ） -->
        <aside class="sidebar">
            <div class="logo-area">
                <h1 class="logo-title">RUTSUBOT</h1>
                <p class="logo-subtitle">混ざり合う、みんなの日常とつぶやきのるつぼ</p>
            </div>
        </aside>
        
        <!-- 右側メインコンテンツ -->
        <main class="main-content">
            <!-- 追従（Sticky）ヘッダーエリア：ユーザー状態、検索、更新、ログアウト、投稿 -->
            <div class="sticky-header">
                <div class="header-status-and-search">
                    <div class="user-status-compact">
                        <div class="avatar avatar-sm" style="background-color: var(--secondary-color); color: #FFFFFF; border-color: var(--secondary-color);">
                            <%= loginUser.getName() != null && !loginUser.getName().isEmpty() ? loginUser.getName().substring(0, 1).toUpperCase() : "U" %>
                        </div>
                        <span class="user-status-name"><strong><%= loginUser.getName() %></strong></span>
                    </div>
                    
                    <!-- 検索フォーム（インライン虫眼鏡） -->
                    <form action="SearchMutter" method="post" class="search-compact-form">
                        <input type="text" name="keyword" placeholder="検索..." class="search-input">
                        <button type="submit" class="btn-icon" title="検索">🔍</button>
                    </form>
                    
                    <!-- アクションナビゲーション -->
                    <div class="header-nav-actions">
                        <a href="Main" class="btn-action-icon" title="タイムラインを更新">🔄</a>
                        <a href="Logout" class="btn-action-icon btn-action-icon-danger" title="ログアウト">🚪</a>
                    </div>
                </div>

                <!-- つぶやき投稿フォーム（極小化） -->
                <form action="Main" method="post" class="post-compact-form">
                    <input type="text" name="text" required placeholder="なにかつぶやく？" class="post-input">
                    <button type="submit" class="btn btn-post-compact">つぶやく</button>
                </form>
            </div>

            <!-- エラーメッセージ表示 -->
            <% if(errorMsg != null){ %>
                <div class="alert alert-danger">
                    <%= errorMsg %>
                </div>
            <% } %>

            <!-- つぶやきタイムライン -->
            <div class="card">
                <h2>タイムライン</h2>
                
                <% if(mutterList != null && !mutterList.isEmpty()) { %>
                    <% for(Mutter mutter : mutterList){ %>
                        <% 
                          String initial = "U";
                          if (mutter.getUserName() != null && !mutter.getUserName().isEmpty()) {
                              initial = mutter.getUserName().substring(0, 1).toUpperCase();
                          }
                        %>
                        <div class="mutter-item">
                            <div class="mutter-header">
                                <!-- メタ情報（アバター、ユーザー名、日付） -->
                                <div class="mutter-meta">
                                    <div class="avatar"><%= initial %></div>
                                    <div class="mutter-user-details">
                                        <span class="mutter-username"><%= mutter.getUserName() %></span>
                                        <span class="mutter-date"><%= mutter.getCreatedAt() %></span>
                                    </div>
                                </div>
                                
                                <!-- アクション（いいね、編集、削除）を名前ブロックと横並びに -->
                                <div class="mutter-actions-container">
                                    <!-- いいねボタン -->
                                    <form method="post" action="Like" style="display: inline;">
                                        <input type="hidden" name="mutterId" value="<%= mutter.getId() %>" />
                                        <button type="submit" class="like-btn <%= mutter.getLikeCount() > 0 ? "liked" : "" %>">
                                            <span>♥</span><%= mutter.getLikeCount() %>
                                        </button>
                                    </form>
                                    
                                    <!-- 投稿者本人の場合のみ編集・削除を表示 -->
                                    <% if(mutter.getUserId() == loginUser.getId()) { %>
                                        <a href="UpdateMutter?id=<%= mutter.getId() %>" class="action-link-icon" title="編集">✏️</a>
                                        <a href="DeleteMutter?id=<%= mutter.getId() %>" class="action-link-icon action-link-icon-danger" title="削除" onclick="return confirm('本当に削除しますか？')">🗑️</a>
                                    <% } %>
                                </div>
                            </div>
                            
                            <!-- 本文（アバターの横位置に綺麗に揃う） -->
                            <p class="mutter-content"><%= mutter.getText() %></p>
                        </div>
                    <% } %>
                <% } else { %>
                    <div style="text-align: center; padding: 2rem 1rem; color: var(--text-secondary);">
                        <p style="margin: 0; font-weight: 700; font-size: 0.95rem;">つぶやきがまだありません</p>
                        <p style="margin: 0.25rem 0 0 0; font-size: 0.8rem;">最初のつぶやきを投稿してみましょう！</p>
                    </div>
                <% } %>
            </div>
        </main>
    </div>
</body>
</html>