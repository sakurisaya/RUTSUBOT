package servlet;

import java.io.IOException;

import dao.MutterDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Mutter;
import model.UpdateMutterLogic;
import model.User;

@WebServlet("/UpdateMutter")
public class UpdateMutter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 1. 編集画面の表示処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // URLパラメータから編集対象のIDを取得
        int id = Integer.parseInt(request.getParameter("id"));

        MutterDAO dao = new MutterDAO();
        Mutter mutter = dao.findById(id);

        // 取得したデータをリクエストスコープに保存してJSPへ
        request.setAttribute("mutter", mutter);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateMutter.jsp");
        dispatcher.forward(request, response);
    }

    // 更新実行処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        String text = request.getParameter("text");

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        MutterDAO dao = new MutterDAO();

        //ログインチェック
        if (loginUser == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Mutter mutter = dao.findById(id);
        if (mutter != null && mutter.getUserId() == loginUser.getId()) {
        // 中身だけ新しく作り直す
        mutter = new Mutter(id, 0, null, text);

        // 更新ロジックの実行
        UpdateMutterLogic logic = new UpdateMutterLogic();
        boolean result = logic.execute(mutter);

        if (result) {
            // 更新成功ならメイン画面（Mainサーブレット）へリダイレクト
            response.sendRedirect("Main");
        } else {
            // 失敗時はエラーメッセージを持って編集画面へ戻る等の処理（任意）
            request.setAttribute("errorMsg", "更新に失敗しました");
            doGet(request, response);
        }
    } else {
        //自分以外のつぶやきの場合
        session = request.getSession();
        String errorMsg = "編集する権限がありません"; 
        session.setAttribute("errorMsg", errorMsg);
        response.sendRedirect("Main");
    }
    }
}
