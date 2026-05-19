package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Mutter;
import model.SearchMutterLogic;

@WebServlet("/SearchMutter")
public class SearchMutter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");

        // 検索処理の実行
        SearchMutterLogic searchMutterLogic = new SearchMutterLogic();
        List<Mutter> mutterList = searchMutterLogic.execute(keyword);

        // 検索結果をリクエストスコープに保存
        request.setAttribute("mutterList", mutterList);
        if (mutterList.isEmpty()) {
            String errorMsg = "<span style=\"color:blue;\">検索結果：０件";
            request.setAttribute("errorMsg", errorMsg);
        }

        // メイン画面にフォワードして表示
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
        dispatcher.forward(request, response);
    }
}
