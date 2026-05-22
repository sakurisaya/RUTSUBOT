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
    System.out.println("DEBUG: PostMutterLogic 実行開始");

    // 元のつぶやき取得
    String originalText = mutter.getText();

    // デコレーション
    String decoratedText = MutterDecorator.decorate(originalText);

    // テキストをセット
    Mutter decoratedMutter = new Mutter(mutter.getUserId(), decoratedText);

    System.out.println("DEBUG: デコレーション結果: " + decoratedText);

    // MutterDAOを使用してDBに保存
    MutterDAO dao = new MutterDAO();
    return dao.create(decoratedMutter);

  }
}