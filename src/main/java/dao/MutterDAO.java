package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {
    // データベース接続情報
    private final String JDBC_URL = "jdbc:h2:file:C:/poritec/ソフトウェア実習/RUTSUBOT/db/RUTSUBOT;AUTO_SERVER=TRUE";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    // 前回の学びを活かし、ドライバーをロードする処理を共通化
    private void loadDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
    }

    /** つぶやきをすべて取得する */  
    public List<Mutter> findAll() {
        List<Mutter> mutterList = new ArrayList<>();
        loadDriver();


        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文の準備 (JOINを使用してユーザー名を取得)
            String sql = "SELECT M.ID, M.USER_ID, U.NAME, M.TEXT, M.CREATED_AT FROM MUTTERS M JOIN USERS U ON M.USER_ID = U.ID ORDER BY M.ID DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int userId = rs.getInt("USER_ID");
                String userName = rs.getString("NAME");
                String text = rs.getString("TEXT");
                java.sql.Timestamp createdAt = rs.getTimestamp("CREATED_AT");
                
                mutterList.add(new Mutter(id, userId, userName, text,createdAt));
            }
            System.out.println("DEBUG: MutterDAO.findAll 取得件数: " + mutterList.size());

        } catch (SQLException e) {
            System.out.println("DEBUG: MutterDAO.findAll エラー: " + e.getMessage());
            e.printStackTrace();
            return mutterList;
        }
        return mutterList;
    }

     // 【検索機能】特定のキーワードを含むつぶやきを検索する
    public List<Mutter> findWithFilter(String keyword) {
        List<Mutter> mutterList = new ArrayList<>();
        loadDriver();


        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文の準備 (JOINを使用してユーザー名を取得)
            String sql = "SELECT M.ID, M.USER_ID, U.NAME, M.TEXT, M.CREATED_AT FROM MUTTERS M JOIN USERS U ON M.USER_ID = U.ID WHERE M.TEXT LIKE ? ORDER BY M.ID DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, "%"+ keyword + "%");//キーワードを％で囲む


            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int userId = rs.getInt("USER_ID");
                String userName = rs.getString("NAME");
                String text = rs.getString("TEXT");
                java.sql.Timestamp createdAt = rs.getTimestamp("CREATED_AT");
                mutterList.add(new Mutter(id, userId, userName, text, createdAt));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mutterList;
    }

     // 新規つぶやきを登録する
    public boolean create(Mutter mutter) {
        loadDriver();

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            String sql = "INSERT INTO MUTTERS(USER_ID, TEXT) VALUES(?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, mutter.getUserId());
            pStmt.setString(2, mutter.getText());
            int result = pStmt.executeUpdate();

            System.out.println("DEBUG: MutterDAO.create 登録結果: " + result);
            return result == 1;

        } catch (SQLException e) {
            System.out.println("DEBUG: MutterDAO.create エラー: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

     // 特定のIDのつぶやき内容を更新する
    public boolean update(Mutter mutter) {
        loadDriver();

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            String sql = "UPDATE MUTTERS SET TEXT = ? WHERE ID = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, mutter.getText());
            pStmt.setInt(2, mutter.getId());
            int result = pStmt.executeUpdate();

            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     // 特定のIDのつぶやきを削除する
    public boolean delete(int id) {
        loadDriver();

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            String sql = "DELETE FROM MUTTERS WHERE ID = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            int result = pStmt.executeUpdate();

            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** 特定のIDのつぶやきを1件取得する */
public Mutter findById(int id) {
    loadDriver();
    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
        String sql = "SELECT M.ID, M.USER_ID, U.NAME, M.TEXT, M.CREATED_AT FROM MUTTERS M JOIN USERS U ON M.USER_ID = U.ID WHERE M.ID = ?";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setInt(1, id);
        ResultSet rs = pStmt.executeQuery();
        if (rs.next()) {
            java.sql.Timestamp createdAt = rs.getTimestamp("CREATED_AT");
            return new Mutter(rs.getInt("ID"), rs.getInt("USER_ID"), rs.getString("NAME"), rs.getString("TEXT"), createdAt);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}
