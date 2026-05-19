package model;
import dao.MutterDAO;

public class PostMutterLogic {
      /**
     * つぶやきをデータベースに登録する
     * @param mutter 投稿するつぶやき
     * @return 登録成功ならtrue、失敗ならfalse
     */
  public boolean execute(Mutter mutter) {
    // MutterDAOを使用してDBに保存
    MutterDAO dao= new MutterDAO();
    return dao.create(mutter);
  }
}