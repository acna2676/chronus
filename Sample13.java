//Sample13
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
public class Sample13 {
  private static final String window_title = "ラジオボタンクリックとテキストフィールド表示";
  private static final int window_width = 640;
  private static final int window_height = 400;
  private static final String rb1_title = "abc";
  private static final String rb2_title = "xyz";
  private static final String data1 = "あああああ";
  private static final String data2 = "んんんんんんんんんん";
  private static final int tf_size = 20;
  JTextField tf = new JTextField(Sample13.data1, Sample13.tf_size);
  // 主処理
  public static void main(final String ar[]) {
    final Sample13 sample = new Sample13();
  }
  // コンストラクタ
  public Sample13() {
    // フレームを作成
    final JFrame f = new JFrame(Sample13.window_title);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // ラジオボタンを作成
    final JRadioButton rb1 = new JRadioButton(Sample13.rb1_title, true);
    final JRadioButton rb2 = new JRadioButton(Sample13.rb2_title);
    // ボタングループにラジオボタンを追加
    final ButtonGroup bg = new ButtonGroup();
    bg.add(rb1);
    bg.add(rb2);
    // ラジオボタンにアイテムリスナを登録
    rb1.addItemListener(new SelectItemListener());
    rb2.addItemListener(new SelectItemListener());
    // ラジオボタンとテキストフィールドをレイアウト
    final JPanel p = new JPanel();
    p.add(rb1);
    p.add(rb2);
    f.getContentPane().add(p, BorderLayout.NORTH);
    final JPanel p2 = new JPanel();
    p2.add(this.tf);
    f.getContentPane().add(p2, BorderLayout.CENTER);
    // フレームを表示
    f.setSize(Sample13.window_width, Sample13.window_height);
    f.setVisible(true);
  }
  // アイテムイベントを受け取るリスナ
  class SelectItemListener implements ItemListener {
    public void itemStateChanged(final ItemEvent e) {
      // イベント発生元オブジェクト取得
      final AbstractButton sel = (AbstractButton) e.getItemSelectable();
      // 選択されているチェックボックスの場合
      if (e.getStateChange() == ItemEvent.SELECTED) {
        if (sel.getText().equals(Sample13.rb1_title)) {
          Sample13.this.tf.setText(Sample13.data1);
        } else if (sel.getText().equals(Sample13.rb2_title)) {
          Sample13.this.tf.setText(Sample13.data2);
        }
      }
    }
  }
}
