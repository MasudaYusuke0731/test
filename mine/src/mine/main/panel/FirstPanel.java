package mine.main.panel;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import mine.main.frame.MainFrame;

public class FirstPanel extends JPanel{
	
	public FirstPanel(int y,int x,int num,MainFrame frame) {
		setLayout(new GridLayout(y,x));
		for(int i=0;i<y;i++) {
			for(int j=0;j<x;j++) {
				JButton btn=new JButton();
				final int putY=i;
				final int putX=j;
				btn.addActionListener(e->{frame.makeField(y, x, num,putY,putX);});
				add(btn);
			}
		}
	}
	
}