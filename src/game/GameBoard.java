package game;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements MouseListener {
	private MyButton[][] bts;
	private int[][] arr;//mảng đánh dấu số mìn từng ô
	private int numberOfMines;
	private Point[] mines;//mảng lưu vị trí của mìn
	private boolean[][] tick;//mảng đánh dấu các ô đã được mở
	private boolean[][] tickFlag;//mảng đánh đấu các ô cắm cờ
	private DoMin doMin;

	public GameBoard(int row, int collum, int n, DoMin doMin) {
		this.doMin = doMin;
		bts = new MyButton[row][collum];
		arr = new int[row][collum];
		tick = new boolean[row][collum];
		tickFlag = new boolean[row][collum];
		numberOfMines = n;
		mines = new Point[n];
		//tạo ngẫu nhiên vị trí mìn được đặt
		for (int i = 0; i < mines.length; i++) {
			//khi mà vị trí mìn trùng với trước đó thì vị trí tiếp tục thay đổi
			do {
				mines[i] = new Point(new Random().nextInt(row), new Random().nextInt(collum));
			} while (arr[mines[i].x][mines[i].y] == -1);
			arr[mines[i].x][mines[i].y] = -1;
		}
		//tạo các con số xung quanh mìn
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != -1)
					arr[i][j] = count(i, j);
			}
		}
		setBorder(BorderFactory.createLoweredBevelBorder());
		setLayout(new GridLayout(row, collum));
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < collum; j++) {
				add(bts[i][j] = new MyButton(DoMin.data.getData().get("default button"), 30, 35));
				bts[i][j].addMouseListener(this);
			}
		}
	}

	//đếm số mìn xung quanh 1 vị trí
	public int count(int x, int y) {
		int c = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i >= 0 && i < arr.length && j >= 0 && j < arr[i].length)
					if (arr[i][j] == -1)
						c++;
			}
		}
		return c;
	}

	//tạo các hình ảnh thay cho những con số đếm số mìn tại trị trí i,j
	public void setButton(int i, int j) {
		switch (arr[i][j]) {
		case 0:
			bts[i][j].setImg(DoMin.data.getData().get("o0"));
			bts[i][j].setEnabled(false);
			break;
		case 1:
			bts[i][j].setImg(DoMin.data.getData().get("o1"));
			bts[i][j].setEnabled(false);
			break;
		case 2:
			bts[i][j].setImg(DoMin.data.getData().get("o2"));
			bts[i][j].setEnabled(false);
			break;
		case 3:
			bts[i][j].setImg(DoMin.data.getData().get("o3"));
			bts[i][j].setEnabled(false);
			break;
		case 4:
			bts[i][j].setImg(DoMin.data.getData().get("o4"));
			bts[i][j].setEnabled(false);
			break;
		case 5:
			bts[i][j].setImg(DoMin.data.getData().get("o5"));
			bts[i][j].setEnabled(false);
			break;
		case 6:
			bts[i][j].setImg(DoMin.data.getData().get("o6"));
			bts[i][j].setEnabled(false);
			break;
		case 7:
			bts[i][j].setImg(DoMin.data.getData().get("o7"));
			bts[i][j].setEnabled(false);
			break;
		case 8:
			bts[i][j].setImg(DoMin.data.getData().get("o8"));
			bts[i][j].setEnabled(false);
			break;
		}
	}

	// mở rộng 8 ô khi kick vào ô bất kỳ trừ mìn
	public void winden(int x, int y) {
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i >= 0 && i < arr.length && j >= 0 && j < arr[x].length && !tick[i][j]) {
					//nếu là arr[i][j] == 0 thì đổi icon cho button rồi đệ quy để dò các cô = 0 tiếp
					if (arr[i][j] == 0) {
						setButton(i, j);
						tick[i][j] = true;
						winden(i, j);
					}
					//nếu arr[i][j] > 0 thì chỉ đổi icon
					if (arr[i][j] > 0) {
						setButton(i, j);
						tick[i][j] = true;
					}
				}
			}
		}
	}

	//tạo kết thúc game
	public void end() {
		for (int i = 0; i < bts.length; i++) {
			for (int j = 0; j < bts[i].length; j++) {
				tick[i][j] = true;
				bts[i][j].setEnabled(false);
				if (arr[i][j] < 0 && !tickFlag[i][j]) {
					bts[i][j].setImg(DoMin.data.getData().get("mines3"));
				} else if (tickFlag[i][j] == true && arr[i][j] >= 0) {
					bts[i][j].setImg(DoMin.data.getData().get("mines2"));
				} else
					setButton(i, j);
			}
		}
		doMin.end();
		DoMin.inGame = false;
	}

	//kiểm tra win
	public boolean win() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (!tick[i][j] && arr[i][j] >= 0)
					return false;
			}
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < bts.length; i++) {
			for (int j = 0; j < bts[i].length; j++) {
				if (bts[i][j] == e.getSource() && e.getButton() == MouseEvent.BUTTON1 && !tick[i][j]) {
					//khi kick chuột trái nếu ô đó là bom thì thua...
					//nếu ô đó > 0 thì mở ô đó
					//nếu là ô trống thì dò xung quanh nó
					if (arr[i][j] < 0) {
						end();
						bts[i][j].setImg(DoMin.data.getData().get("mines1"));
						bts[i][j].setEnabled(false);
						DoMin.controlBt.setImg(DoMin.data.getData().get("lose"));
					} else if (arr[i][j] > 0) {
						setButton(i, j);
						tick[i][j] = true;
					} else
						winden(i, j);
					if (win() && DoMin.inGame) {
						JOptionPane.showMessageDialog(null, "You Win!!!");
						end();
						DoMin.controlBt.setImg(DoMin.data.getData().get("win"));
					}
				}
				//tạo sk cắm cờ
				if (bts[i][j] == e.getSource() && e.getButton() == MouseEvent.BUTTON3) {
					//khi kick chuột phải thì cắm cờ
					if (tick[i][j] == false) {
						bts[i][j].setImg(DoMin.data.getData().get("flag"));
						tick[i][j] = true;
						tickFlag[i][j] = true;
						doMin.reductionMinus();
					} else {
						bts[i][j].setImg(DoMin.data.getData().get("default button"));
						tick[i][j] = false;
						tickFlag[i][j] = false;
						doMin.increaseMinus();
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
