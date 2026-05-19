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
import model.DeleteMutterLogic;
import model.User;

@WebServlet("/DeleteMutter")
public class DeleteMutter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 1. 削除画面の表示処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //  URLパラメータから削除対象のIDを取得
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);

        // ログインユーザーとDAOを準備
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        MutterDAO dao = new MutterDAO();

        //ログインチェック
        if (loginUser == null) {
            String errorMsg = "ログインしてください";
            session.setAttribute("errorMsg", errorMsg);
            response.sendRedirect("index.jsp");
            return;
        }
        // つぶやきを取得して、自分のものかチェック
        Mutter mutter = dao.findById(id);
        if (mutter.getUserId() == loginUser.getId()){

            //削除ロジックの実行
            DeleteMutterLogic logic = new DeleteMutterLogic();
            logic.execute(id);
        }else {
            String errorMsg = "削除する権限がありません。";
            session.setAttribute("errorMsg", errorMsg);
        }
        response.sendRedirect("Main");
    }

}
