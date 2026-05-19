package model;
import dao.UserDAO;

public class LoginLogic {
    /**
   * ログイン情報の存在チェックを行う
   * @param user ログイン入力された情報
   * @return DBにユーザーが存在すればtrue、存在しなければfalse
   */
  public boolean execute(User user) {
    // UserDAOを使用してデータベースからユーザーを検索
    UserDAO dao = new UserDAO();
    User loginUser = dao.findByLogin(user);
    
    //一致するユーザーがいれば（! null）ログイン成功
    return loginUser != null;

  }
}