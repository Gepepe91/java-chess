package chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class ChessGamePanel extends JPanel{
	


	ChessGame chessGame = new ChessGame();
	
	Color getColor(int color) {
		if (color == 1)
			return Color.darkGray;
		else if (color == 2)
			return Color.YELLOW;
		else if (color == 3)
			return Color.RED;
		else if (color == 4)
			return Color.MAGENTA;
		else if (color == 5)
			return Color.GREEN;
		else if (color == 6)
			return Color.BLUE;
		else if (color == 7)
			return Color.ORANGE;
		return Color.WHITE;
	}

	int borderSize = 30;
	
	
	
	
	
	
	
	

	ChessGamePanel() {
		setFocusable(true);

		// listening the keyboard
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				
				// Listen to keys
				/*
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					chessGame.moveLeft();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					chessGame.moveRight();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					chessGame.moveDown();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					chessGame.rotate();
				} else if (e.getKeyCode() == KeyEvent.VK_E){
					chessGame.exchange();
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE){
					chessGame.hardDrop();
				} 
				*/
				
				repaint();
			}
		});

		final Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				chessGame.update();
				repaint();
			}
		}, 1000, 500);
	}
	
	
	
	
	
	
	

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Taille d'une case carrée : la plus petite des deux possibilités
		//int cellWidth = (getWidth() - 2 * borderSize) / chessGame.gameGrid.nbColumns;
		int cellWidth = (getWidth() - 2 * borderSize) / (chessGame.gameGrid.nbColumns);
		int cellHeight = (getHeight() - 2 * borderSize) / chessGame.gameGrid.nbLines;
		int cellSize = Math.min(cellWidth, cellHeight);

		// 1. La grille : cases vides et blocs déjà posés
		for (int line = 0; line < chessGame.gameGrid.nbLines; line++) {
			for (int column = 0; column < chessGame.gameGrid.nbColumns; column++) {
				Color color = Color.LIGHT_GRAY;   // couleur d'une case vide
				drawCell(g, line, column, cellSize, color);
			}
		}
		
		//Message
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, (int) Math.sqrt( getWidth() * getHeight() )/20 ) );
		g.drawString(chessGame.message, 2*getWidth()/4, 3*getHeight()/4);
		
		/*
		if (chessGame.isGameOver) {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 28));
			g.drawString("GAME OVER", borderSize, getHeight() / 2);
		}
		*/
	}
	

	// Dessine une case de la grille avec la couleur demandée
	void drawCell(Graphics g, int line, int column, int cellSize, Color color) {
		
		boolean cellIsBlack;
		boolean cellIsGray;
		
		int sumLineColumn = line + column;
		
		if (sumLineColumn % 2 == 0) {
			cellIsBlack = true;
		} else {
			cellIsBlack = false;
		}
		cellIsGray = !cellIsBlack;
		
		int cellX = column * cellSize + borderSize;
		int cellY = line * cellSize + borderSize;

		// Dessin des rectangles de la grille
		if (cellIsBlack)
			g.setColor(Color.DARK_GRAY);
		if (cellIsGray)
			g.setColor(Color.WHITE);
		g.fillRect(cellX, cellY, cellSize - 1, cellSize - 1);
	}
	
	
	
	
}
