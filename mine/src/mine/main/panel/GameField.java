package mine.main.panel;

import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JPanel;

import mine.main.button.fieldButton.FieldButton;
import mine.main.frame.MainFrame;

/**
 * 爆弾などが入ってるマスをまとめたもの
 * @author 増田裕介
 *
 */
public class GameField extends JPanel{
	private FieldButton[][] field;//所属しているフィールド
	// 八方向を表す
	private int[][] vec = {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}};
	
	public GameField(int y,int x,int num,MainFrame frame,int putY,int putX) {
		setLayout(new GridLayout(y, x));
		field=new FieldButton[y][x];
		
		// 爆弾の座標をyyxxの形で作る(100<xにするならyyyxxxの形にしないとバグ発生するので注意)
		HashSet<Integer> bombSet = new HashSet<>();
		Random random = new Random();
		
		while(true) {
			int by = random.nextInt(y);
			int bx = random.nextInt(x);
			
			if(putY*100+putX==by*100+bx) {
				continue;
			}
			bombSet.add(by*100+bx);
			if(bombSet.size()==num) {
				break;
			}
		}
		for(int i=0;i<field.length;i++) {
			for(int j=0;j<field[i].length;j++) {
				String fieldParam=FieldButton.EMPTY;
				if(bombSet.contains(i*100+j)) {
					fieldParam =FieldButton.BOMB;
				}else {
					//周辺の爆弾の数を数える
					int count = 0;
					
					for(int[] v : vec) {
						int targetY = i+v[0];
						int targetX = j+v[1];
						if(targetX==100) {
							continue;
						}
						if(bombSet.contains(targetY*100+targetX)) {
							count++;
						}
					}
					if(count>0) {
						fieldParam = Integer.toString(count);
					}
				}
				field[i][j]=new FieldButton(fieldParam,i,j,frame);
				add(field[i][j]);
			}
		}
	}
	
	public FieldButton[][] getField(){
		return field;
	}
}
