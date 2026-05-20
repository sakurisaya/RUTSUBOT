package model;
import java.io.Serializable;
import java.sql.Timestamp;

public class Mutter implements Serializable {
  private int id; // つぶやきid
  private int userId; // ユーザーID
  private String userName; // ユーザー名 (表示用)
  private String text; // つぶやき内容
  private Timestamp createdAt; //作成日時
  private int likeCount; //いいねカウント 

  public Mutter() { }

   // ① 新規投稿（DB が timestamp と likeCount をデフォルトで設定）
  public Mutter(int userId, String text) {
      this.userId = userId;
      this.text = text;
      this.createdAt = null;
      this.likeCount = 0;
  }

  // ② DB から取得したフルレコード
  public Mutter(int id, int userId, String userName, String text, Timestamp createdA, int likeCount) {
    this.id = id; 
    this.userId = userId;
    this.userName = userName;
    this.text = text;
    this.createdAt = createdAt;
    this.likeCount = likeCount;

  }

  // ③ 互換用：5 引数版（likeCount が無い場合は 0 にフォールバック）
  public Mutter(int id, int userId, String userName, String text, Timestamp createdAt) {
    this(id, userId, userName, text, createdAt, 0);
  }
  // ④ 互換用：ID のみが分かっているケース（UpdateMutter が使う）
  public Mutter(int id, int userId, String userName, String text) {
    this(id, userId, userName, text, null, 0);
  }

  public int getId() { return id; }
  public int getUserId() { return userId; }
  public String getUserName() { return userName; }
  public String getText() { return text; }
  public Timestamp getCreatedAt() { return createdAt;}
  public int getLikeCount() { return likeCount; }
} 