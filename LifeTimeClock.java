
/**
 * 愛のJava256本ノック for Java 5.0
 * Javaサンプルソース ver0.2C "LifeTimeClock"
 * LifeTimeClock.java 「時間を文字列に整形してデジタル時計」
 *
 * 2005/09/23 制作：安永ノリカズ
 *
 * 【コンパイル＆実行方法】
 *     >javac LifeTimeClock.java
 *     >java LifeTimeClock
 * 【キーワード】
 *     書式文字列(format string), ベースライン(base line:基準線),
 *     アセント(ascent:上昇), ディセント(descent:下降),
 * 【試してみよう】
 *     java.text.SimpleDateFormatを使って時刻を整形する
 *     年月日も表示する。
 *     アナログ時計を作る。
 */
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LifeTimeClock extends JFrame {

  static final int C00 = 1000;

  public LifeTimeClock() {
    DrawPanel L00 = new DrawPanel();
    add(L00);

    new Timer(C00, L00).start();
  }

  public static void main(String[] A00) {
    Toolkit.getDefaultToolkit().setDynamicLayout(true);

    LifeTimeClock L00 = new LifeTimeClock();
    L00.setTitle("LifeTimeClock");
    L00.setDefaultCloseOperation(EXIT_ON_CLOSE);
    L00.setBackground(Color.white);
    L00.setSize(200, 100);
    L00.setVisible(true);
  }
}

class DrawPanel extends JPanel implements ActionListener {

  public DrawPanel() {
    setBackground(Color.white);
  }

  public void actionPerformed(ActionEvent A00) {
    repaint();
  }

  public void paintComponent(Graphics A00) {
    super.paintComponent(A00);

    Date now = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSXXX");
    try {
      Date lifeLimit = df.parse("2074/01/01 00:00:00.000+00:00");
      long leftTime = (lifeLimit.getTime() - now.getTime()) / 1000;

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(lifeLimit);
      int lifeLimitYear = calendar.get(Calendar.YEAR); // - 1900;
      calendar.setTime(now);
      int nowYear = calendar.get(Calendar.YEAR); // - 1900;
      // 生きてる間に100で割り切れる年は来ないので4で割り切れる年しか考慮しない
      // うるう年が何回あるか計算する
      int leapYearCount = 0;
      System.out.println("year: " + nowYear + "," + lifeLimitYear);
      for (int year = nowYear + 1; year < lifeLimitYear; year++) {
        // System.out.println("year: " + year);
        if (year % 4 == 0) {
          leapYearCount += 1;
        }
      }
      System.out.println(
          "leapYearCount: " + leapYearCount + "," + (lifeLimitYear - nowYear - leapYearCount));

      long leftYear = lifeLimitYear - nowYear; // leftTime / (366 * leapYearCount + 365 * (lifeLimitYear - nowYear -
                                               // leapYearCount));
      System.out.println("leftYear: " + leftYear);
      // long leftMonth = calendar.setTime(now);
      int nowMonth = calendar.get(Calendar.MONTH); // - 1900;
      long leftDay = leftTime / 86400;
      String L00 = String.format("残り %d 日", leftDay);

      FontMetrics L01 = A00.getFontMetrics();

      A00.drawString(
          L00,
          (getWidth() - L01.stringWidth(L00)) / 2,
          (getHeight() + L01.getAscent() - L01.getDescent()) / 2);
    } catch (ParseException e) {
      System.out.println("正しい日付ではありません");
    }
  }
}
