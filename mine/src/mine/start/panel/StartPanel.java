package mine.start.panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import mine.start.box.StartBox;
import mine.start.frame.StartFrame;
/**
 * パラメーター設定用パネル
 * @author 増田裕介
 *
 */
public class StartPanel extends JPanel{
	public static final int MAX_Y=20;//Y軸の最大マス数
	public static final int MAX_X=30;//X軸の最大マス数
	public static final String Y_AXIS_TITLE="縦軸:1～"+MAX_Y;
	public static final String X_AXIS_TITLE="横軸:1～"+MAX_X;
	public static final String BOM_TITLE="爆弾数";
	public static final String[] BOX_TITLES= {Y_AXIS_TITLE,X_AXIS_TITLE,BOM_TITLE};

	private StartBox[] boxs=new StartBox[BOX_TITLES.length];

	public StartPanel(StartFrame startFrame) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		for(int i=0;i<boxs.length;i++){
			boxs[i]=new StartBox(BOX_TITLES[i]);
			boxs[i].getParamF().addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(e.getKeyChar()==KeyEvent.VK_ENTER) {
						startFrame.gameStart();
					}
				}
				@Override
				public void keyReleased(KeyEvent e) {}
				@Override
				public void keyPressed(KeyEvent e) {}
			});
			add(boxs[i]);
		}
	}

	public int getY() {
		return getText(0);
	}
	public int getX() {
		return getText(1);
	}
	public int getBoms() {
		return getText(2);
	}

	private int getText(int i) {
		try {
			return Integer.parseInt(boxs[i].getParam());
		}catch(Exception e) {
			return -1;
		}
	}
}