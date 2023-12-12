package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import data.Data;

public class DoMin extends JFrame implements ActionListener {
	public static Data data;
	private JPanel controlPn, numberOfMinesPn, timePn;
	private MyLabel hundredLb, dozenLb, unitLb, hourLb, minuteLb, secondsLb;
	public static MyButton controlBt;
	private GameBoard gameBoard;
	public static boolean inGame;
	private int n, count;
	private int seconds;
	private Timer timer;
	private Menu menu;
	private int row, collum;

	public DoMin(int row, int collum) {
		this.row = row;
		this.collum = collum;
		data = new Data();
		inGame = true;
		n = (row * collum) / 6;
		count = n;
		seconds = 0;
		//tạo đếm thời gian
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seconds++;
				setTime(seconds);
			}
		});
		timer.start();
		setLayout(new BorderLayout(0, 20));
		add(controlPn = new JPanel(), BorderLayout.NORTH);
		controlPn.setBorder(BorderFactory.createBevelBorder(1));
		controlPn.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		controlPn.add(numberOfMinesPn = new JPanel(), BorderLayout.WEST);
		numberOfMinesPn.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		int c = n % 10, b = n / 10 % 10, a = n / 100 % 10;
		numberOfMinesPn.add(hundredLb = new MyLabel(data.getData().get(a + ""), 25, 42));
		numberOfMinesPn.add(dozenLb = new MyLabel(data.getData().get(b + ""), 25, 42));
		numberOfMinesPn.add(unitLb = new MyLabel(data.getData().get(c + ""), 25, 42));
		controlPn.add(controlBt = new MyButton(data.getData().get("smile"), 40, 40));
		controlBt.addActionListener(this);
		controlPn.add(timePn = new JPanel(), BorderLayout.EAST);
		timePn.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		timePn.add(hourLb = new MyLabel(data.getData().get("0"), 25, 42));
		timePn.add(minuteLb = new MyLabel(data.getData().get("0"), 25, 42));
		timePn.add(secondsLb = new MyLabel(data.getData().get("0"), 25, 42));
		add(gameBoard = new GameBoard(row, collum, n, this));
		setJMenuBar(menu = new Menu(this));

		pack();
		setVisible(true);
		setTitle("Dò Mìn");
		setIconImage(new ImageIcon("img/doMin.jpg").getImage());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//đếm số mìn còn lại
	public void increaseMinus() {
		setNumberOfMinus(++count);
	}

	public void reductionMinus() {
		setNumberOfMinus(--count);
	}

	//đặt lại số mìn về ban đầu
	public void setNumberOfMinus(int n) {
		int c = n % 10, b = n / 10 % 10, a = n / 100 % 10;
		hundredLb.setImg(data.getData().get(a + ""));
		dozenLb.setImg(data.getData().get(b + ""));
		unitLb.setImg(data.getData().get(c + ""));
	}

	//đặt lại thời gian
	public void setTime(int seconds) {
		int c = seconds % 10, b = seconds / 10 % 10, a = seconds / 100 % 10;
		hourLb.setImg(data.getData().get(a + ""));
		minuteLb.setImg(data.getData().get(b + ""));
		secondsLb.setImg(data.getData().get(c + ""));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//xử lý nút mặt cười(chơi lại)
		if (controlBt == e.getSource()) {
			if (!inGame)
				newGame();
			else {
				int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn chơi lại chứ?",
						"Nhắc nhở!!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION)
					newGame();
			}
		}
	}

	//chơi lại
	public void newGame() {
		remove(gameBoard);
		count = n;
		setNumberOfMinus(count);
		add(gameBoard = new GameBoard(row, collum, n, this));
		inGame = true;
		controlBt.setImg(DoMin.data.getData().get("smile"));
		seconds = 0;
		setTime(seconds);
		timer.start();
		repaint();
		revalidate();
	}

	public void end() {
		timer.stop();
	}

	public void easy() {
		n = (row * collum) / 6;
		setNumberOfMinus(n);
	}

	public void normal() {
		n = (row * collum) / 5;
		setNumberOfMinus(n);
	}

	public void hard() {
		n = (row * collum) / 4;
		setNumberOfMinus(n);
	}

	public void small() {
		setVisible(false);
		new DoMin(12, 10);
	}

	public void medium() {
		setVisible(false);
		new DoMin(15, 20);
	}

	public void large() {
		setVisible(false);
		new DoMin(20, 30);
	}

	public static void main(String[] args) {
		new DoMin(12, 10);
	}
}
