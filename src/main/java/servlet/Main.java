package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // ぶやきリストをDBから取得して、リクエストスコープに保存
    GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
    List<Mutter> mutterList = getMutterListLogic.execute();
    System.out.println("DEBUG: Main.doGet 取得件数: " + (mutterList != null ? mutterList.size() : "null"));
    request.setAttribute("mutterList", mutterList);

    // ログインしているか確認
    HttpSession session = request.getSession();
    User loginUser = (User) session.getAttribute("loginUser");

    if (loginUser == null) { // ログインしていない場合
      // リダイレクト
      response.sendRedirect("index.jsp");
    } else { // ログイン済みの場合
      // フォワード
      RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
      dispatcher.forward(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // リクエストパラメータの取得
    request.setCharacterEncoding("UTF-8");
    String text = request.getParameter("text");

    // 入力値チェック
    if (text != null && text.length() != 0) {
      // 投稿処理の場合
      HttpSession session = request.getSession();
      User loginUser = (User) session.getAttribute("loginUser");

      // つぶやきをDBに保存
      Mutter mutter = new Mutter(loginUser.getId(), text);

      PostMutterLogic postMutterLogic = new PostMutterLogic();
      postMutterLogic.execute(mutter);

      // 投稿後は全件取得して表示を更新
      GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
      List<Mutter> mutterList = getMutterListLogic.execute();
      request.setAttribute("mutterList", mutterList);

    } else {
      // エラーメッセージをリクエストスコープに保存
      request.setAttribute("errorMsg", "つぶやきが入力されていません");
      // エラー時も全件取得して表示を更新
      GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
      List<Mutter> mutterList = getMutterListLogic.execute();
      request.setAttribute("mutterList", mutterList);
    }

    // メイン画面にフォワード
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
    dispatcher.forward(request, response);
  }
}