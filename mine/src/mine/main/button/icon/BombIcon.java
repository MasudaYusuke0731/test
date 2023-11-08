package mine.main.button.icon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;

import mine.main.frame.MainFrame;

public class BombIcon extends ImageIcon{
	private static Random random = new Random();
	private static String[] iconURL = {
			"./image/skallBomb.png",
			"./image/other_bomb01_01.png",
			"./image/nekoneko.png",
			"./image/5741.png",
			"./image/5747.png",
	};

	private static Image[] images;

	private int iconNo;
	
	private final int CAT_NO=2;

	static {
		images = new Image[iconURL.length];
		for(int i=0;i<images.length;i++) {
			images[i]=scaleImage(
					trimImage(iconURL[i])
					);
		}
	}


	public BombIcon() {
		iconNo =random.nextInt(images.length);
		setImage(images[iconNo]);
	}

	public String getMessage() {
		return switch(iconNo) {
		case CAT_NO->"にゃーん";
		default->"死";
		};
	}
	
	public boolean isCat() {
		return iconNo==CAT_NO;
	}

	private static Image scaleImage(BufferedImage image) {
		int buttonWidth = MainFrame.BUTTON_WIDTH;
		int buttonHeight = MainFrame.BUTTON_HEIGHT;
		int newWidth = image.getWidth();
		int newHeight = image.getHeight();

		if (image.getWidth() > buttonWidth) {
			newWidth = buttonWidth;
			newHeight = (image.getHeight() * newWidth) / image.getWidth();
		}
		if (newHeight > buttonHeight) {
			newHeight = buttonHeight;
			newWidth = (image.getWidth() * newHeight) / image.getHeight();
		}

		return image.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
	}

	private static BufferedImage trimImage(String imagePath) {
		ImageIcon icon = new ImageIcon(imagePath);
		BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		icon.paintIcon(null, g2d, 0, 0);
		g2d.dispose();

		// 余白を削除する
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		int minX = width;
		int minY = height;
		int maxX = 0;
		int maxY = 0;

		// 透明ピクセルの範囲を調べる
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = bufferedImage.getRGB(x, y);
				if (new Color(pixel, true).getAlpha() != 0) {  // 透明ピクセルでない場合
					if (x < minX) minX = x;
					if (x > maxX) maxX = x;
					if (y < minY) minY = y;
					if (y > maxY) maxY = y;
				}
			}
		}

		// 余白を削除したイメージを作成する
		int trimmedWidth = maxX - minX + 1;
		int trimmedHeight = maxY - minY + 1;

		BufferedImage crop = bufferedImage.getSubimage(minX, minY, trimmedWidth, trimmedHeight);

		return crop;
	}

}
