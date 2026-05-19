package servlet;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LoginLogic;
import model.User;
import dao.UserDAO; 

@WebServlet("/Login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // リクエストパラメータの取得
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");
    // Userインスタンス（ユーザー情報）の生成
    User user = new User(name, pass);
    // ログイン処理の実行 (DB照合)
    LoginLogic loginLogic = new LoginLogic();

    UserDAO dao = new UserDAO();
    User loginUser = dao.findByLogin(user);


    // ログイン判定とセッションへの保存
    // 1. 入力値のバリデーション（まず入力を確認する）
    if (name == null || name.isEmpty() || pass == null || pass.isEmpty()) {
      String errorMsg = "必要事項を入力してください";
      request.setAttribute("errorMsg", errorMsg);
    } else if (loginUser != null) {
       // 2.セッションスコープにDBから取得したユーザー情報（ID入り）を保存
      HttpSession session = request.getSession();
      session.setAttribute("loginUser", loginUser);
    } else {
      // 3. 認証失敗
      String errorMsg = "ユーザー名またはパスワードが正しくありません";
      request.setAttribute("errorMsg", errorMsg);
    }
    // ログイン結果画面にフォワード
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
    dispatcher.forward(request, response);
  }
}