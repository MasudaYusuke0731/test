package mine.main.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import mine.main.button.fieldButton.FieldButton;
import mine.main.panel.FirstPanel;
import mine.main.panel.GameField;
import mine.main.panel.status.GameStatus;
import mine.main.panel.status.StatusPanel;
/**
 * ゲーム用フレーム
 * @author 増田裕介
 *
 */
public class MainFrame extends JFrame{
	public static final int BUTTON_WIDTH=50;
	public static final int BUTTON_HEIGHT=35;
	public static final int STATUS_HEIGHT=60;
	private StatusPanel statusPanel;
	private GameField gameField;
	private FirstPanel firstPanel;
	

	public MainFrame(int y,int x,int num) {
		setLayout(new BorderLayout());
		GameStatus gameStatus = new GameStatus(y, x, num);
		statusPanel = new StatusPanel(gameStatus);
		add(statusPanel,BorderLayout.NORTH);
		
		firstPanel = new FirstPanel(y, x, num, this);
		add(firstPanel,BorderLayout.CENTER);
		
		setTitle("MineSweeper");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(x*BUTTON_WIDTH,y*BUTTON_HEIGHT+STATUS_HEIGHT));
		setVisible(true);
	}
	
	public void makeField(int y,int x,int num,int putY,int putX) {
		remove(firstPanel);
		gameField =new GameField(y, x, num,this,putY,putX);
		add(gameField,BorderLayout.CENTER);
		FieldButton[][] f = gameField.getField();
		f[putY][putX].open();
		setSize(new Dimension(x*BUTTON_WIDTH,y*BUTTON_HEIGHT+STATUS_HEIGHT));
	}

	/**
	 * getter
	 * @return
	 */
	public StatusPanel getStatusPanel() {
		return statusPanel;
	}
	public GameField getGameField() {
		return gameField;
	}
}
