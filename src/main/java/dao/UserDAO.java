package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDAO {
    // データベース接続情報
    private final String JDBC_URL = "jdbc:h2:file:C:/poritec/ソフトウェア実習/dokoTsubu/db/dokoTsubu;AUTO_SERVER=TRUE";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

        /**
     * ログイン情報の合致するユーザーを検索する
     * @param user ログイン入力された名前とパスワードを持つUserインスタンス
     * @return DBに存在すればID入りのUserインスタンス、存在しなければnull
     */
    public User findByLogin(User user) {
        User loginUser = null;

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文の準備
            String sql = "SELECT ID, NAME, PASS FROM USERS WHERE NAME = ? AND PASS = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, user.getName());
            pStmt.setString(2, user.getPass());

            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();

            // 一致したユーザーがいれば、そのユーザー情報を返す
            if (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String pass = rs.getString("PASS");
                loginUser = new User(id, name, pass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return loginUser;
    }

    
    /**
     * 新規ユーザーをデータベースに登録する
     * @param user 登録する名前とパスワードを持つUserインスタンス
     * @return 登録成功ならtrue
     */
    public boolean create(User user) {
        System.out.println("DEBUG: UserDAO.create 開始 - 名前: " + user.getName());
        
        try {
            // JDBCドライバを明示的に読み込む
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("DEBUG: H2ドライバが見つかりません。libフォルダを確認してください。");
            e.printStackTrace();
            return false;
        }

        // データベースへ接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // INSERT文を準備（IDはauto_incrementなので指定しない）[1]
            String sql = "INSERT INTO USERS(NAME, PASS) VALUES(?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, user.getName());
            pStmt.setString(2, user.getPass());

            // INSERT文を実行
            int result = pStmt.executeUpdate();
            if (result != 1) {
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("DEBUG: SQLException発生！ メッセージ: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
