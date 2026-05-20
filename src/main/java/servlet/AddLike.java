package servlet;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.MutterDAO; 

@WebServlet("/Like")
public class AddLike extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("mutterId");
        if (idStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Idが指定されていません");
            return;
        }
        int mutterId;
        try {
            mutterId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Idが整数ではありません");
            return;
        }
        MutterDAO dao = new MutterDAO();
        dao.addLike(mutterId);
        response.sendRedirect("Main");
    }
}
