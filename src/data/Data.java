package data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Data {
	private Map<String, Image> data;

	public Data() {
		data = new HashMap<String, Image>();
		try {
			BufferedImage bufferedImage = ImageIO.read(new File("img/minesweeper.png"));
			Image num0 = bufferedImage.getSubimage(0, 0, 13, 23);
			Image num1 = bufferedImage.getSubimage(13, 0, 13, 23);
			Image num2 = bufferedImage.getSubimage(26, 0, 13, 23);
			Image num3 = bufferedImage.getSubimage(39, 0, 13, 23);
			Image num4 = bufferedImage.getSubimage(52, 0, 13, 23);
			Image num5 = bufferedImage.getSubimage(65, 0, 13, 23);
			Image num6 = bufferedImage.getSubimage(78, 0, 13, 23);
			Image num7 = bufferedImage.getSubimage(91, 0, 13, 23);
			Image num8 = bufferedImage.getSubimage(104, 0, 13, 23);
			Image num9 = bufferedImage.getSubimage(117, 0, 13, 23);
			data.put("0", num0);
			data.put("1", num1);
			data.put("2", num2);
			data.put("3", num3);
			data.put("4", num4);
			data.put("5", num5);
			data.put("6", num6);
			data.put("7", num7);
			data.put("8", num8);
			data.put("9", num9);
			Image smile = bufferedImage.getSubimage(0, 56, 25, 25);
			Image lose = bufferedImage.getSubimage(79, 56, 25, 25);
			Image win = bufferedImage.getSubimage(105, 56, 25, 25);
			data.put("smile", smile);
			data.put("lose", lose);
			data.put("win", win);
			Image dfBt = bufferedImage.getSubimage(1, 40, 14, 14);
			data.put("default button", dfBt);
			Image o0 = bufferedImage.getSubimage(1, 24, 16, 16);
			Image o1 = bufferedImage.getSubimage(17, 24, 16, 16);
			Image o2 = bufferedImage.getSubimage(33, 24, 16, 16);
			Image o3 = bufferedImage.getSubimage(49, 24, 16, 16);
			Image o4 = bufferedImage.getSubimage(65, 24, 16, 16);
			Image o5 = bufferedImage.getSubimage(81, 24, 16, 16);
			Image o6 = bufferedImage.getSubimage(97, 24, 16, 16);
			Image o7 = bufferedImage.getSubimage(113, 24, 16, 16);
			Image o8 = bufferedImage.getSubimage(129, 24, 16, 16);
			data.put("o0", o0);
			data.put("o1", o1);
			data.put("o2", o2);
			data.put("o3", o3);
			data.put("o4", o4);
			data.put("o5", o5);
			data.put("o6", o6);
			data.put("o7", o7);
			data.put("o8", o8);
			Image mines1 = bufferedImage.getSubimage(32, 40, 14, 14);
			Image mines2 = bufferedImage.getSubimage(49, 40, 14, 14);
			Image mines3 = bufferedImage.getSubimage(65, 40, 14, 14);
			data.put("mines1", mines1);
			data.put("mines2", mines2);
			data.put("mines3", mines3);
			Image flag = bufferedImage.getSubimage(17, 40, 14, 14);
			data.put("flag", flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Image> getData() {
		return data;
	}
}
