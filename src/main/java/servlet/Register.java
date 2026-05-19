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
import model.RegisterUserLogic;
import model.User;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // registerView.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerView.jsp");
        dispatcher.forward(request, response);
    }

        
    // 登録実行処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        // Userインスタンス（ユーザー情報）の生成
        User registerUser = new User(name, pass);

        // 登録処理の実行
        RegisterUserLogic logic = new RegisterUserLogic();
        boolean result = logic.execute(registerUser);

        // 登録結果をリクエストスコープに保存
        request.setAttribute("result", result);

        //登録結果画面へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerResult.jsp");
        dispatcher.forward(request, response);
    }
}
