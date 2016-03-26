package sudoku.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.model.Sudoku;
import sudoku.model.Cell;
import sudoku.solver.RankSolver;
import sudoku.solver.SimpleSolver;
import sudoku.util.SudokuUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class SudokuForm extends JFrame implements ActionListener {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	private JLabel[] labels;
	private JPanel[] panels;
	private JPanel bottomPanel;
	private JPanel boardPanel;
	private GridLayout layout;
	private final Sudoku sudoku;
	private final RankSolver rankSolver;
	private final SimpleSolver simpleSolver;
	private JButton quit;
	private JButton iterate;
	private Font standardFont;
	private Font solvedFont;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public SudokuForm(Sudoku sudoku) {
		simpleSolver=new SimpleSolver();
		rankSolver=new RankSolver();

		setupFonts();
		
		this.sudoku=sudoku;
		System.out.println(sudoku.toString());

		this.addComponentListener(new ResizeListener(this));

		drawMe();
	}

	protected void setSudokuFont(Font font) {
		logger.trace("Setting font with size {}", font.getSize());
		this.standardFont = font;
		this.solvedFont = standardFont.deriveFont(Font.BOLD);
	}

	private void setupFonts() {
		this.setSudokuFont(new JLabel().getFont());
	}

	private void drawMe() {
		labels = new JLabel[81];
		panels = new JPanel[9];

		boardPanel = new JPanel();
		bottomPanel = new JPanel();

		layout=new GridLayout(3,3);
		boardPanel.setLayout(layout);
		boardPanel.setMinimumSize(new Dimension(WIDTH, HEIGHT));

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
			labels[i].setFont(standardFont);
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

	protected void drawLabels() {
		Cell currentCell;
		boolean showCandidates=true;

		for (int i = 0 ; i < labels.length ; i++) {
			currentCell=sudoku.getCell(i);
			if (!currentCell.isUndecided() || showCandidates) {
				String newStr=currentCell.toString();
				if (labels[i] != null) {
					labels[i].setFont(standardFont);

					if (labels[i].getText() != null && !labels[i].getText().equals(newStr)) {
						if (!labels[i].getText().isEmpty()) {
							labels[i].setForeground(Color.RED);
							if (newStr.indexOf('{') < 0) {
								labels[i].setFont(solvedFont);
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
	}

	public static class ResizeListener implements ComponentListener {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		private final SudokuForm sudokuForm;
		private final Font standardFont;

		public ResizeListener(SudokuForm sudokuForm) {
			this.sudokuForm = sudokuForm;
			this.standardFont = new JLabel().getFont();
		}

		@Override
		public void componentResized(ComponentEvent e) {
			Component c = e.getComponent();

			logger.info(c.getSize().toString());
			double least = Math.min(c.getSize().getHeight(), c.getSize().getWidth());

			Font f = standardFont.deriveFont(standardFont.getSize() * (float) (least / ((float) WIDTH)));

			sudokuForm.setSudokuFont(f);
			sudokuForm.drawLabels();
		}

		@Override
		public void componentMoved(ComponentEvent e) {

		}

		@Override
		public void componentShown(ComponentEvent e) {

		}

		@Override
		public void componentHidden(ComponentEvent e) {

		}
	}

	public static void main(String[] args) {
		Sudoku sudoku=new Sudoku(SudokuUtil.VERY_HARD_BOARD);
		SudokuForm form=new SudokuForm(sudoku);

		form.setVisible(true);
	}
}

