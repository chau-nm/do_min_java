package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

public class MyButton extends JButton {
	private Image img;

	public MyButton(Image img, int w, int h) {
		this.img = img;
		setPreferredSize(new Dimension(w, h));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		repaint();
	}
	
	public void setImg(Image img) {
		this.img = img;
	}
}
