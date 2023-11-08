package mine.start.box;

import java.awt.TextField;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
/**
 * フィールドのパラメーターを入れる用の簡単なパネル
 * @author 増田裕介
 *
 */
public class StartBox extends Box{
	private TextField param = new TextField();
	
	public StartBox(String title) {
		super(BoxLayout.Y_AXIS);
		add(new JLabel(title));
		add(param);
	}
	public String getParam() {
		return param.getText();
	}
	
	public TextField getParamF() {
		return param;
	}
}
