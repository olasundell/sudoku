package sudoku.gui;

import sudoku.model.Sudoku;
import sudoku.model.Cell;
import sudoku.solver.RankSolver;
import sudoku.solver.SimpleSolver;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: Feb 1, 2010
 * Time: 11:29:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class SudokuForm extends JFrame implements ActionListener {
	private JLabel[] labels;
	private JPanel[] panels;
	private JPanel bottomPanel;
	private JPanel boardPanel;
	private GridLayout layout;
	private Sudoku sudoku;
	private RankSolver rankSolver;
	private SimpleSolver simpleSolver;
	private JButton quit;
	private JButton iterate;

	public SudokuForm(Sudoku sudoku) {
		simpleSolver=new SimpleSolver();
		rankSolver=new RankSolver();
		
		this.sudoku=sudoku;
		sudoku.calculateCandidates();
		System.out.println(sudoku.toString());

		drawMe();
	}

	private void drawMe() {
		labels = new JLabel[81];
		panels = new JPanel[9];

		boardPanel = new JPanel();
		bottomPanel = new JPanel();

		layout=new GridLayout(3,3);
		boardPanel.setLayout(layout);
		boardPanel.setMinimumSize(new Dimension(500,500));

		this.setLayout(new BorderLayout());
		this.add(boardPanel,BorderLayout.CENTER);
		this.add(bottomPanel,BorderLayout.SOUTH);

		iterate = new JButton("Iterate");
		iterate.setActionCommand("Iterate");
		iterate.addActionListener(this);

		quit = new JButton("Quit");
		quit.setActionCommand("Quit");
		quit.addActionListener(this);

		bottomPanel.add(iterate);
		bottomPanel.add(quit);

		this.setMinimumSize(new Dimension(500,600));

		for (int i=0;i<panels.length;i++) {
			panels[i]=new JPanel();
			panels[i].setBorder(LineBorder.createBlackLineBorder());
			panels[i].setLayout(new GridLayout(3,3));
			boardPanel.add(panels[i]);
		}

		for (int i=0;i<labels.length;i++) {
			labels[i]=new JLabel();
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			labels[i].setBorder(LineBorder.createBlackLineBorder());
			panels[i/9].add(labels[i]);
		}
		drawLabels();
	}

	public void actionPerformed(ActionEvent e) {
		if ("Quit".equals(e.getActionCommand())) {
			System.exit(0);
		} else if ("Iterate".equals(e.getActionCommand())) {
			iterate.setEnabled(false);
			System.out.println("Iterating...");
			if (!simpleSolver.iterate(sudoku)) {
				rankSolver.iterate(sudoku);
			}
			drawLabels();
			iterate.setEnabled(true);
		}
	}

	private void drawLabels() {
		Cell currentCell;
		boolean showCandidates=true;
		for (int i=0;i<labels.length;i++) {
			currentCell=sudoku.getCell(i);
			if (!currentCell.isUndecided() || showCandidates) {
				String newStr=currentCell.toString();
				if (!labels[i].getText().equals(newStr) ) {
					if (!labels[i].getText().isEmpty()) {
						labels[i].setForeground(Color.RED);
						if (newStr.indexOf('{') < 0) {
							labels[i].setForeground(Color.GREEN);
						}
					}

					labels[i].setText(newStr);
				} else {
					labels[i].setForeground(Color.BLACK);
				}
			}
		}
	}

/*
	public static void main(String[] args) {
		Sudoku sudoku=new Sudoku(SudokuTest.veryHardBoard);
		SudokuForm form=new SudokuForm(sudoku);

		form.setVisible(true);
	}
*/
}

