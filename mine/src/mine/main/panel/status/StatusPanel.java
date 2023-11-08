package mine.main.panel.status;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mine.main.frame.MainFrame;

/**
 * 残りいくつ開けたらいいかなどのステータス管理パネル。
 * ステータス自体はGameStatusクラスにて管理
 * @author 増田裕介
 *
 */
public class StatusPanel extends JPanel{
	private GameStatus gameStatus;
	private JLabel labelWall;// 開けなければいけないマス数
	private JLabel labelFail;// 失敗回数
	private JLabel labelTime;// 経過時間
	
	public StatusPanel(GameStatus gameStatus) {
		setLayout(new FlowLayout());
		this.gameStatus=gameStatus;
		add(new JLabel("残り"));
		labelWall = new JLabel(Integer.toString(this.gameStatus.getLastWall()));
		add(labelWall);
		add(new JLabel("失敗"));
		labelFail = new JLabel(Integer.toString(0));
		add(labelFail);
		add(new JLabel("経過時間"));
		labelTime=new JLabel("0");
		add(labelTime);
	}
	
	public GameStatus getGamestatus() {
		return gameStatus;
	}
	
	// 安全な場所を開けた
	public void wallOpen(MainFrame frame) {
		gameStatus.wallOpen(frame);
		reSet();
	}
	
	// 経過時間をセットする
	public void setTime() {
		labelTime.setText(Long.toString(this.gameStatus.getSecond()));
	}
	
	// 爆弾を開けた
	public void failOpen() {
		labelFail.setText(Integer.toString(gameStatus.fail()));
	}
	
	//値を 再セット
	public void reSet() {
		labelWall.setText(Integer.toString(gameStatus.getLastWall()));
	}

	public void failOpen(boolean cat) {
		if(cat) {
			catOpen();
		}else {
			failOpen();
		}
		
	}
	
	private boolean haveCat=false;
	private int count=1;
	private JLabel c= new JLabel("1");
	private void catOpen() {
		if(haveCat) {
			c.setText(Integer.toString(++count));
		}else {
			add(new JLabel("猫"));
			add(c);
			haveCat=true;
		}
	}
}
