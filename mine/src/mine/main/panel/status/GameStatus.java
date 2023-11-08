package mine.main.panel.status;

import javax.swing.JOptionPane;

import mine.main.frame.MainFrame;
import mine.main.thread.TimeThread;

/**
 * 失敗数やクリアの管理などを行う
 * @author 増田裕介
 *
 */
public class GameStatus {
	private int bombsNum;
	private int wallNum;
	private boolean start = false;
	private int fail=0;
	long startTime;
	
	public GameStatus(int y,int x,int b) {
		wallNum=y*x;
		bombsNum=b;
	}
	
	public boolean isClear() {
		return bombsNum==wallNum;
	}
	public void start() {
		start=true;
	}
	
	/**
	 * 安全な場所を開けた時
	 */
	public void wallOpen(MainFrame frame) {
		if(!start) {
			start=true;
			startTime=System.currentTimeMillis();
			new TimeThread(frame.getStatusPanel());
		}
		wallNum--;
		if(isClear()) {
			JOptionPane.showMessageDialog(null, "クリア!\nクリアタイム:"+getSecond()+"秒","やったね",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
	
	public long getSecond() {
		return (System.currentTimeMillis()-startTime)/1000;
	}
	
	/**
	 * 失敗した時
	 * @return 現在の失敗数
	 */
	public int fail() {
		return ++fail;
	}
	
	public boolean isStarted() {
		return start;
	}
	
	public int getLastWall() {
		return wallNum-bombsNum;
	}
}
