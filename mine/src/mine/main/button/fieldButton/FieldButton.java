package mine.main.button.fieldButton;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import mine.main.button.icon.BombIcon;
import mine.main.frame.MainFrame;

/**
 * 各マスのボタン
 * @author 増田裕介
 *
 */
public class FieldButton extends JButton implements MouseListener{
	public static final String EMPTY ="　"; 
	public static final String FLAG = "旗"; 
	public static final String Q = "?"; 
	public static final String BOMB = "爆"; 
	private int[][] vec = {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}};

	private String param;
	private Color paramColor;// 文字の色
	private boolean opened=false;// オープン済みかどうか
	// ボタンがどの座標にいるか(y,x)
	private int y; 
	private int x;
	// ボタンがあるフレーム
	private MainFrame frame;

	public FieldButton(String param,int y,int x,MainFrame frame) {
		this.frame = frame;
		this.y=y;
		this.x=x;
		addMouseListener(this);
		setParam(param);
	}

	public void setParam(String param) {
		this.param=param;
		setBackground(Color.GREEN);
		paramColor = switch(param) {
		case "8","7"->Color.RED;
		case "6","5"->Color.GREEN;
		case "4","3"->Color.YELLOW;
		case "1","2"->Color.BLUE;
		default -> Color.BLACK;
		};
	}

	/**
	 * マスを開ける
	 */
	public void open() {
		if(opened||getText().equals(FLAG)) {
			return;
		}
		opened=true;
		switch(param) {
		case BOMB:
			BombIcon icon= new BombIcon();
			setIcon(icon);
			frame.getStatusPanel().failOpen(icon.isCat());
			JOptionPane.showMessageDialog(null, icon.getMessage(),"結果",JOptionPane.ERROR_MESSAGE);
			setBackground(Color.RED);
			break;
		case EMPTY:
			setBackground(Color.WHITE);raundOpen();break;
		default:
			setBackground(Color.GRAY);
		}

		setForeground(paramColor);
		if(!param.equals(BOMB)) {
			setText(param);
			frame.getStatusPanel().wallOpen(frame);
		}
	}
	/**
	 * 周辺マスを開ける
	 */
	private void raundOpen() {
		FieldButton[][] f = frame.getGameField().getField();
		for(int[] v:vec) {
			int tempX=x+v[0];
			int tempY=y+v[1];
			try {
				f[tempY][tempX].open();
			}catch(Exception e) {
				continue;
			}
		}
	}

	public boolean isOpen () {
		return opened;
	}
	/**
	 * フラッグ切り替え
	 */
	public void flag() {
		if(opened) {
			return;
		}
		switch(getText()) {
		case FLAG:setText(Q);break;
		case Q:setText("");break;
		default:setText(FLAG);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		switch(e.getButton()) {
		case MouseEvent.BUTTON1:
			open();break;
		case MouseEvent.BUTTON2:
			raundOpen();break;
		case MouseEvent.BUTTON3:
			flag();break;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
