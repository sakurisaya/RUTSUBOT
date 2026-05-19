package model;
import java.io.Serializable;

public class User implements Serializable {
  private int id; // ユーザーid
  private String name; // ユーザー名
  private String pass; // パスワード

  public User() { }

  // 登録用コンストラクタ（idはDBが自動採番するため不要）
  public User(String name, String pass) {
    this.name = name;
    this.pass = pass;
  }
  // DBからの取得用コンストラクタ（idを含む）
  public User(int id, String name, String pass) {
    this.id = id;
    this.name = name;
    this.pass = pass;
  }

  public int getId() { return id; }
  public String getName() { return name; }
  public String getPass() { return pass; }
}