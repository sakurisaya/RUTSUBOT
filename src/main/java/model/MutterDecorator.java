package model;

import java.util.List;
import java.util.Random;

/**
 * つぶやきをランダムにデコレーションするクラス
 */
public class MutterDecorator {
    // ランダム決定を行うための道具
    private static final Random random = new Random();

    // 文頭に挿入する言葉
    private static final List<String> PREFIXES = List.of(
            "恐れ入りますが、",
            "ていうか",
            "あ、",
            "ときに、",
            "【悲報】", // 2ch/まとめ風
            "要チェックやけど、", // 関西の解説者風
            "お疲れ様です。ぶっちゃけ、", // ビジネスマンの本音
            "聞いてよ、", // ギャル・女子トーク風
            "拙者、若輩者ながら", // 侍・ござる系
            "ちょ、待てよ！", // 某トレンディドラマ風
            "ココだけの話、", // 噂好き
            "目を見て一言、");

    // 語尾
    private static final List<String> SUFFIXES = List.of(
            "ですわね&emsp;", // お嬢様
            "だぜ&emsp;", // ワイルド
            "っっっ！&emsp;", // 勢い
            "やねん&emsp;", // 関西弁
            "でごわす&emsp;", // 力士・ごわす系
            "でありんす&emsp;", // 花魁・廓言葉風
            "氏〜！！&emsp;", // ネットオタク風
            "なのだよ&emsp;", // インテリ・眼鏡キャラ風
            "かも分からん&emsp;", // 曖昧
            "っピ...&emsp;", // 謎のゆるキャラ風
            "マニア&emsp;" // 専門家
    );

    // 末尾に挿入する言葉
    private static final List<String> ENDINGS = List.of(
            "&emsp;知らんけど",
            "&emsp;そんな気がした2026年、夏",
            "&emsp;気のせいかしら",
            "&emsp;草々",
            "&emsp;なんてな",
            "（早口）", // オタクのトドメ
            "（個人の感想です）", // 通販番組の注釈
            "（CV:大塚明夫）", // 急にイケボ化
            "&emsp;現場からは以上です。", // 報道番組風
            "（要出典）", // Wikipedia風
            "&emsp;あ、これコピペね。" // 台無しにする一言
    );

    /**
     * つぶやきを受け取り、デコレーションした文字列を返す
     * 
     * @param originalText 元のつぶやき
     * @return デコレーションされたつぶやき
     */

    public static String decorate(String originalText) {
        // 空ならそのまま返す
        if (originalText == null || originalText.isEmpty()) {
            return originalText;
        }

        // 文字を組み立てるための道具
        StringBuilder sb = new StringBuilder();

        // 文字の挿入

        // 文頭
        if (random.nextDouble() < 0.4) {
            int index = random.nextInt(PREFIXES.size());
            sb.append(PREFIXES.get(index));
        }
        // 元のつぶやきを返す
        sb.append(originalText);

        // 語尾
        if (random.nextDouble() < 0.4) {
            int index = random.nextInt(SUFFIXES.size());
            sb.append(SUFFIXES.get(index));
        }

        // 末尾
        if (random.nextDouble() < 0.4) {
            int index = random.nextInt(ENDINGS.size());
            sb.append(ENDINGS.get(index));
        }
        return sb.toString();
    }
}
