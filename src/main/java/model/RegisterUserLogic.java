package model;
import dao.UserDAO;

public class RegisterUserLogic {
    /**
     * ユーザー情報をデータベースに登録する
     * @param user 登録するユーザー情報
     * @return 登録成功ならtrue、失敗ならfalse
    */
    public boolean execute(User user) {
        UserDAO dao = new UserDAO();
        return dao.create(user);
    }
}
