package model;

import dao.MutterDAO;

public class PostMutterLogic {
  /**
   * つぶやきをデータベースに登録する
   * 
   * @param mutter 投稿するつぶやき
   * @return 登録成功ならtrue、失敗ならfalse
   */
  public boolean execute(Mutter mutter) {

    // 元のつぶやき取得
    String originalText = mutter.getText();

    // デコレーション
    String decoratedText = MutterDecorator.decorate(originalText);

    // テキストをセット
    Mutter decoratedMutter = new Mutter(mutter.getUserId(), decoratedText);

    // MutterDAOを使用してDBに保存
    MutterDAO dao = new MutterDAO();
    return dao.create(decoratedMutter);

  }
}