package mine.start.frame;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mine.main.frame.MainFrame;
import mine.start.panel.StartPanel;

/**
 * パラメーター設定用フレーム
 * @author 増田裕介
 *
 */
public class StartFrame extends JFrame{

	private StartPanel sPanel=new StartPanel(this);

	public StartFrame() {
		JPanel panel= new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(sPanel);
		
		JButton startButton = new JButton("start");
		startButton.addActionListener((e)->{gameStart();});
		panel.add(startButton);
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MineSweeper");
		pack();
		setVisible(true);
	}

	public void gameStart() {
		int y= sPanel.getY();
		int x= sPanel.getX();
		int b= sPanel.getBoms();

		if(y>0&&x>0&&b<y*x&&y<=StartPanel.MAX_Y&&x<=StartPanel.MAX_X) {
			new MainFrame(y, x, b);
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "値が不正です","エラー",JOptionPane.WARNING_MESSAGE);
		}
	}
}
