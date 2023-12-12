package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

public class MyLabel extends JLabel {

	private Image img;

	public MyLabel(Image img, int w, int h) {
		this.img = img;
		setPreferredSize(new Dimension(w, h));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		repaint();
	}
	
	public void setImg(Image img) {
		this.img = img;
	}
}
