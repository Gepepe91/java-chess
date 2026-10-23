package chess;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

import javax.swing.JPanel;

public class ChessGamePanel extends JPanel{
	
	private final Map<String, Image> images = new HashMap<>();
	


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
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE && !chessGame.playing) {
					chessGame.startTurn();
				}
				else if (e.getKeyCode() != KeyEvent.VK_SPACE && chessGame.playing)
					chessGame.invalidMove();
				else if (e.getKeyCode() == KeyEvent.VK_SPACE && chessGame.playing)
					chessGame.endTurn();
				repaint();
			}
		});

		
		// Here, the "timer" is just a loop waiting for player input. All the turn-based logic is in
		// ChessGame.java
		
		/*
		final Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (chessGame.hasPlayed()) {
					chessGame.update();
					repaint();
				}
			}
		}, 100, 50);
		*/
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
		
		boolean cellWithPiece = false;
		
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
		if (cellWithPiece)
			g.setColor(Color.RED);
		g.fillRect(cellX, cellY, cellSize - 1, cellSize - 1);
	}
	
	void drawPiece(Graphics g, Piece piece, int cellSize) {
	    if (!piece.isAlive) return;

	    String colorName = (piece.color == 0) ? "white" : "black";
	    Image img = images.get(colorName + "_" + piece.getType());
	    if (img == null) return;

	    int x = piece.position.column * cellSize + borderSize;
	    int y = piece.position.line * cellSize + borderSize;
	    g.drawImage(img, x, y, cellSize - 1, cellSize - 1, this);
	}
	/*
	void drawPiece(Piece piece, int line, int column, int imageSize, Color color) {
		if (piece.getType() == "Pawn")
			drawPawn();
		else if (piece.getType() == "Crook")
			drawCrook();
		else if (piece.getType() == "Bishop")
			drawBishop();
		else if (piece.getType() == "Knight")
			drawKnight();
		else if (piece.getType() ==  "Queen")
			drawQueen();
		else if (piece.getType() == "King")
			drawKing();			
	}
	*/
	
	void drawPawn() {
	}
	
	void drawCrook() {
	}
	
	void drawBishop() {
	}
	
	void drawKnight() {
	}
	
	void drawQueen() {
	}
	
	void drawKing() {
	}
	
	private void loadImages() {
	    String[] types = {"pawn", "rook", "bishop", "knight", "queen", "king"};
	    String[] colors = {"white", "black"};
	    for (String c : colors)
	        for (String t : types)
	            images.put(c + "_" + t, new ImageIcon("media/" + c + "_" + t + ".png").getImage());
	}
	
	
	
	
}
