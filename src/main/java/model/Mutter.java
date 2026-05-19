package model;
import java.io.Serializable;
import java.sql.Timestamp;

public class Mutter implements Serializable {
  private int id; // つぶやきid
  private int userId; // ユーザーID
  private String userName; // ユーザー名 (表示用)
  private String text; // つぶやき内容
  private Timestamp createdAt; //作成日時

  public Mutter() { }

  public Mutter(int userId, String text, Timestamp createdAt) {
      this.userId = userId;
      this.text = text;
      this.createdAt = createdAt;
  }

  public Mutter(int userId, String text) {
      this.userId = userId;
      this.text = text;
      this.createdAt = null;
  }

  public Mutter(int id, int userId, String userName, String text) {
    this.id = id; 
    this.userId = userId;
    this.userName = userName;
    this.text = text;
  }
  
  public Mutter(int id, int userId, String userName, String text, Timestamp createdAt) {
    this.id = id; 
    this.userId = userId;
    this.userName = userName;
    this.text = text;
    this.createdAt = createdAt;

  }

  public int getId() { return id; }
  public int getUserId() { return userId; }
  public String getUserName() { return userName; }
  public String getText() { return text; }
  public Timestamp getCreatedAt() { return createdAt;}
} 