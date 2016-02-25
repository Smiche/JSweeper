import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Alexander
 *
 */
public class Cell extends JButton {
	boolean revealed = false;
	//29x22 = 638 cells
	static int openedValue = 0;
	static int minesValue = 0;
	int posX, posY;
	int value;
	int valX, valY;
	static final Random random = new Random(System.currentTimeMillis());
	
	public Cell(int valX, int valY) {
		this.setFont(new Font("Arial", Font.BOLD, 12));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				set();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		this.setBorderPainted(true);
		this.setMargin(new Insets(0, 0, 0, 0));
		this.posX = valX * 20;
		this.posY = valY * 20;
		this.valX = valX;
		this.valY = valY;
		this.setSize(20, 20);
		this.setLocation(posX, posY);
		minesValue++;
		if (random.nextInt(10) % 5 == 0) {
			value = -1; // The bomb has been planted.
		} else {
			value = 0;
		}
	}

	void calculateValues() {
		Main.log("X:" + valX + " Y:" + valY);
		if (value != -1) {
			if (valX < 28 && Main.field[valX + 1][valY].value == -1) {
				value++;
			}
			if (valX < 28 && valY < 21
					&& Main.field[valX + 1][valY + 1].value == -1)
				value++;
			if (valX > 0 && Main.field[valX - 1][valY].value == -1)
				value++;
			if (valY < 21 && Main.field[valX][valY + 1].value == -1)
				value++;
			if (valX > 0 && valY > 0
					&& Main.field[valX - 1][valY - 1].value == -1)
				value++;
			if (valY > 0 && Main.field[valX][valY - 1].value == -1)
				value++;
			if (valX < 28 && valY > 0
					&& Main.field[valX + 1][valY - 1].value == -1)
				value++;
			if (valX > 0 && valY < 21
					&& Main.field[valX - 1][valY + 1].value == -1)
				value++;
		}
		Main.log(value);

	}

	void set() {
		if (value == -1) {
			this.setText("*");
			gameLost();
		} else if (value > 0) {
			this.setText("" + value);
			openedValue++;
			if(openedValue+minesValue == 638)gameWin();
		} else {
			this.setText("0");
			openedValue++;
			if(openedValue+minesValue == 638)gameWin();
			//this.setPressed();
			reveal(this.valX, this.valY);
		}
	}

	void setCell(int x, int y) {
		Main.field[x][y].set();
	}

	int getVal(int x, int y) {
		return Main.field[x][y].value;
	}

	// if x<27 & y<20
	void reveal(int x, int y) {
		if (!Main.field[x][y].revealed) {
			this.revealed = true;
			System.out.println("X = " + x + " _Y = " + y);
			setCell(x, y);
			if (x < 27)
				setCell(x + 1, y);
			if (x > 0)
				setCell(x - 1, y);
			if (y < 20)
				setCell(x, y + 1);
			if (y > 0)
				setCell(x, y - 1);
			if (x < 27 && y < 20)
				setCell(x + 1, y + 1);
			if (x < 27 && y > 0)
				setCell(x + 1, y - 1);
			if (x > 0 && y < 20)
				setCell(x - 1, y + 1);
			if (x > 0 && y > 0)
				setCell(x - 1, y - 1);

			if (getVal(x + 1, y) == 0 && x < 27 && y < 20 && y> 0 &&x > 0) {
				reveal(x + 1, y);
			}
			if (getVal(x - 1, y) == 0 && x < 27 && y < 20 && y> 0 &&x > 0) {
				reveal(x - 1, y);
			}
			if (getVal(x, y + 1) == 0 && x < 27 && y < 20 && y> 0 &&x > 0) {
				reveal(x, y + 1);
			}
			if (getVal(x, y - 1) == 0 && x < 27 && y < 20 && y> 0 &&x > 0) {
				reveal(x, y - 1);
			}
			if (getVal(x + 1, y + 1) == 0 && x < 27 && y < 20 && y> 0 &&x > 0) {
				reveal(x + 1, y + 1);
			}
			if (getVal(x + 1, y - 1) == 0 && x < 27 && y < 20 && y> 0 &&x > 0) {
				reveal(x + 1, y - 1);
			}
			if (getVal(x - 1, y + 1) == 0 && x < 27 && y < 20 && y> 0 &&x > 0) {
				reveal(x - 1, y + 1);
			}
			if (getVal(x - 1, y - 1) == 0 && x < 27 && y < 20 && y> 0 &&x > 0) {
				reveal(x - 1, y - 1);
			}

		}
	}
	
	public static void gameWin(){
		
		JFrame endFrame = new JFrame("You have won the game!");
		endFrame.setBounds(Main.frame.getX()+200,Main.frame.getY()+200,400,200);
		JPanel container = new JPanel();
		
		JLabel victory = new JLabel("Congratulations!");
		victory.setSize(victory.getPreferredSize());
		//victory.setEnabled(true);
		
		boolean nextCol = false;
		
		JButton restart = new JButton("Restart");
		JButton quit = new JButton("Quit");
		
		restart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.resetGame();
				
			}
			
		});
		
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(-1);
				
			}
			
		});
		container.add(restart);
		container.add(quit);
		
		endFrame.add(container);
		endFrame.setVisible(true);
		
		while(true){
	        if (nextCol) {
	           victory.setForeground(Color.red);
	           nextCol=!nextCol;
	        } else {
	            victory.setForeground(Color.BLUE);
	            nextCol=!nextCol;
	        }
	        try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	public static void gameLost(){
		JFrame endFrame = new JFrame("You have lost the game!");
		endFrame.setBounds(Main.frame.getX()+200,Main.frame.getY()+200,400,200);
		JPanel container = new JPanel();
		
		JButton restart = new JButton("Restart");
		JButton quit = new JButton("Quit");
		
		restart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.resetGame();
				
			}
			
		});
		
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(-1);
				
			}
			
		});
		container.add(restart);
		container.add(quit);
		
		endFrame.add(container);
		endFrame.setVisible(true);
		
	}
}
