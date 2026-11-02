package chess;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessGamePanel extends JPanel {
	
	/*
	 Description of this mess of a vibe-coded class
	 
	 create chessGame
	 
	 constructor ChessGamePanel()
	 	do chessGame.initGame()
	 	set key listeners
	 	set mouse listeners
	 	
	 void paintComponent(Graphics g)
	 	define cells size
	 	draw black and white alternance calling drawcell
	 	call drawPlayerPieces(chessGame.playerWhite)
	 	write message
	 	
	 void drawCell()
	 	similar to Tetris, draw black or white
	 	
	 void drawPlayerPieces(Graphics g, Player player, String colorName, int cellSize)
	 	declares Piece[] pieces = {player.pawn1, player.pawn2, ...}, sort of list/array of pieces
	 	then, loop of pieces and call drawPiece()
	 	
	 void drawPiece(Graphics g, Piece piece, String colorName, int cellSize)
	 	checks if there is a piece or is alive
	 	checks if the piece type is null
	 	checks if the file name exist
	 	defines images display size
	 	call g.drawImage()
	 	
	 void loadImages()
	 	defines 2 strings array, one with the type and one with color
	 	the two of them makes all file names
	 	Loop on them and explicitly look for corresponding files in the media directory
	 	load them with Image method
	 	
	 Idea : method selectCell that calculate which cell has been clicked
	 */

	private final Map<String, Image> images = new HashMap<>();// ?

	ChessGame chessGame = new ChessGame();

	int borderSize = 30;
	// Need to make those variables global because they are called in multiples methods
	//int cellWidth = (getWidth() - 2 * borderSize) / chessGame.gameGrid.nbColumns;
	//int cellHeight = (getHeight() - 2 * borderSize) / chessGame.gameGrid.nbLines;
	//int cellSize = Math.min(cellWidth, cellHeight);
	
	int pixelXClicked, pixelYClicked;

	ChessGamePanel() {
		setFocusable(true);

		chessGame.initGame();   // move pieces from (0,0) to correct places
		loadImages();           // charge les sprites une seule fois

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
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
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent m) {
				pixelXClicked = m.getX();
				pixelYClicked = m.getY();
				System.out.println("Mouse : " + pixelXClicked + " , " + pixelYClicked);
				//Position selectedCell = selectCell(pixelXClicked,pixelYClicked);
				//selectedCell.print();
				repaint();
				
				
			}
		});
	}
	
	Position selectCell(int x, int y, int cellSize, int borderSize) {
		/*
		 Should return only the selected cell
		 */
		int cellUpLimit, cellDownLimit, cellLeftLimit, cellRightLimit;
		Position selectedCell = new Position();
		for (int line = 0; line < chessGame.gameGrid.nbLines; line++) {
			for (int column = 0; column < chessGame.gameGrid.nbColumns; column++) {
				cellUpLimit = line*cellSize + borderSize;
				cellLeftLimit = column*cellSize + borderSize;
				cellDownLimit = cellUpLimit + cellSize;
				cellRightLimit = cellLeftLimit + cellSize;
				
				if (cellLeftLimit < x && x < cellRightLimit && cellUpLimit < y && y < cellDownLimit)
					selectedCell.setPosition(line, column);
			}
		}
		return selectedCell;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int cellWidth = (getWidth() - 2 * borderSize) / chessGame.gameGrid.nbColumns;
		int cellHeight = (getHeight() - 2 * borderSize) / chessGame.gameGrid.nbLines;
		int cellSize = Math.min(cellWidth, cellHeight);
		

		// 1. Les cases
		for (int line = 0; line < chessGame.gameGrid.nbLines; line++) {
			for (int column = 0; column < chessGame.gameGrid.nbColumns; column++) {
				drawCell(g, line, column, cellSize, Color.LIGHT_GRAY);
			}
		}

		// 2. Les pièces, par-dessus les cases
		drawPlayerPieces(g, chessGame.whitePlayer, "white", cellSize);
		drawPlayerPieces(g, chessGame.blackPlayer, "black", cellSize);

		// 3. Le message
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, (int) Math.sqrt(getWidth() * getHeight()) / 20));
		//g.drawString(chessGame.message, 2 * getWidth() / 4, 3 * getHeight() / 4);
		
		// 4. Select a cell
		Position selectedCell = selectCell(pixelXClicked, pixelYClicked, cellSize, borderSize);
		selectedCell.print();
	}

	void drawCell(Graphics g, int line, int column, int cellSize, Color color) {
		boolean cellIsBlack = (line + column) % 2 == 0;

		int cellX = column * cellSize + borderSize;
		int cellY = line * cellSize + borderSize;

		g.setColor(cellIsBlack ? Color.WHITE : Color.DARK_GRAY);
		g.fillRect(cellX, cellY, cellSize - 1, cellSize - 1);
	}

	// Rassemble toutes les pièces d'un joueur et les dessine
	void drawPlayerPieces(Graphics g, Player player, String colorName, int cellSize) {
		Piece[] pieces = {
			player.pawn1, player.pawn2, player.pawn3, player.pawn4,
			player.pawn5, player.pawn6, player.pawn7, player.pawn8,
			player.leftRook, player.rightRook,
			player.leftKnight, player.rightKnight,
			player.leftBishop, player.rightBishop,
			player.queen, player.king
		};
		for (Piece piece : pieces) {
			drawPiece(g, piece, colorName, cellSize);
		}
	}

	
	void drawPiece(Graphics g, Piece piece, String colorName, int cellSize) {
		if (piece == null || !piece.isAlive) // do nothing if the cell is empty
			return;

		String type = piece.getType();   // "pawn", "rook", ... (null tant que la classe n'est pas faite)
		if (type == null) {
			System.out.println("Type is null");
			return;
			}

		Image img = images.get(colorName + "_" + type); //declares the names of the image files
		if (img == null)
			return;

		int x = piece.position.column * cellSize + borderSize; //defines the sizes of the images
		int y = piece.position.line * cellSize + borderSize;
		g.drawImage(img, x, y, cellSize - 5, cellSize - 5, this); // actually draw the images
	}

	private void loadImages() {
		String[] types = {"pawn", "rook", "bishop", "knight", "queen", "king"};
		String[] colors = {"white", "black"};

		for (String c : colors) {
			for (String t : types) {
				String fileName = t;
				File file = new File("/Users/gabriel/Documents/Etudes/M3/JAVA/Perso/src/chess/media/" + c + "_" + fileName + ".png");

				if (!file.exists()) {
					System.out.println("Image introuvable : " + file.getAbsolutePath());
					continue;
				}
				images.put(c + "_" + t, new ImageIcon(file.getPath()).getImage());
			}
		}
	}
	
	
}