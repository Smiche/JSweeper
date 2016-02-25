import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/* JSweeper, minesweeper java implementation.
*  Copyright (c) 2016 Aleksandar Ivanov
*/
public class Main extends JFrame{
	public static Main frame;
	public static Cell[][] field;
	private static JPanel panel;
	public Main(String title){
		this.setTitle(title);
		this.setBounds(0, 0, 586, 468);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String... args){
		initFrame();
		initGame();
		field[0][0].gameWin();
	}
	
	public static void initFrame(){
		frame = new Main("JMines");
		panel = new JPanel();
		panel.setLayout(null);
		panel.setVisible(true);
		panel.setBounds(0,0,580,460);
	}
	public static void initGame(){
		field = new Cell[29][22];

		for(int i =0;i<29;i++){
			for(int j =0;j<22;j++){
				field[i][j] = new Cell(i,j);
			}
		}
		for(int i =0;i<29;i++){
			for(int j =0;j<22;j++){
				field[i][j].calculateValues();
			}
		}
		
		for(int i =0;i<29;i++){
			for(int j =0;j<22;j++){
				panel.add(field[i][j]);
			}
		}
		frame.add(panel);
		frame.setVisible(true);
		panel.revalidate();
		panel.repaint();
	}
	public static void resetGame(){
		for(int i =0;i<29;i++){
			for(int j =0;j<22;j++){
				panel.remove(field[i][j]);
			}
		}
		panel.revalidate();
		panel.repaint();
		initGame();
	}
	public static void log(String arg){
		System.out.println(arg);
	}
	public static void log(int arg){
		System.out.println(arg);
	}
	
}
