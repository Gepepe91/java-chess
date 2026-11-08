package chess;

import java.awt.BorderLayout;

import javax.swing.WindowConstants;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Chess");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ChessGamePanel gamePanel = new ChessGamePanel();
		//GameConsole console = new GameConsole();
		
		frame.setLayout(new BorderLayout());
		frame.add(gamePanel, BorderLayout.CENTER);
		//frame.add(console, BorderLayout.EAST);

		frame.setSize(500, 500);
		//frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		//console.redirectSystemOut();
		System.out.println("This is chess!");

		gamePanel.requestFocusInWindow();
		
		System.out.println((int) (Math.sqrt(3)));

	}

}
