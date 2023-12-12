package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeListener;

public class Menu extends JMenuBar implements ActionListener {
	private JMenu option, difficult, size;
	private JMenuItem newGame, quit;
	private JRadioButtonMenuItem easy, normal, hard, small, medium, large;
	private DoMin doMin;

	public Menu(DoMin doMin) {
		this.doMin = doMin;
		add(option = new JMenu("Option"));
		option.add(newGame = new JMenuItem("Ván mới"));
		newGame.addActionListener(this);
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		option.addSeparator();
		option.add(difficult = new JMenu("Độ khó"));
		difficult.add(easy = new JRadioButtonMenuItem("Dễ"));
		easy.addActionListener(this);
		difficult.add(normal = new JRadioButtonMenuItem("Trung bình"));
		normal.addActionListener(this);
		difficult.add(hard = new JRadioButtonMenuItem("Khó"));
		hard.addActionListener(this);
		ButtonGroup btg1 = new ButtonGroup();
		btg1.add(easy);
		btg1.add(normal);
		btg1.add(hard);
		option.add(size = new JMenu("Kích thước"));
		size.add(small = new JRadioButtonMenuItem("12x10"));
		small.addActionListener(this);
		size.add(medium = new JRadioButtonMenuItem("15x20"));
		medium.addActionListener(this);
		size.add(large = new JRadioButtonMenuItem("20x30"));
		large.addActionListener(this);
		ButtonGroup btg2 = new ButtonGroup();
		btg2.add(small);
		btg2.add(medium);
		btg2.add(large);
		option.addSeparator();
		option.add(quit = new JMenuItem("Thoát"));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//ván mới
		if (newGame == e.getSource()) {
			if (!doMin.inGame)
				doMin.newGame();
			else {
				int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn chơi lại chứ?",
						"Nhắc nhở!!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION)
					doMin.newGame();
			}
		}
		//mức dễ
		if (easy == e.getSource()) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Nhắc nhở!!!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				doMin.easy();
				doMin.newGame();
			}
		}
		//trung bình
		if (normal == e.getSource()) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Nhắc nhở!!!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				doMin.normal();
				doMin.newGame();
			}
		}
		//khó
		if (hard == e.getSource()) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Nhắc nhở!!!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				doMin.hard();
				doMin.newGame();
			}
		}
		//kích thước nhỏ
		if (small == e.getSource()) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Nhắc nhở!!!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION)
				doMin.small();
		}
		//trung bình
		if (medium == e.getSource()) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Nhắc nhở!!!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION)
				doMin.medium();
		}
		//lớn
		if (large == e.getSource()) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Nhắc nhở!!!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION)
				doMin.large();
		}
	}
}
