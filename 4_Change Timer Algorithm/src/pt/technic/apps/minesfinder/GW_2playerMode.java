package pt.technic.apps.minesfinder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GW_2playerMode  extends GameWindow{
	
    private int player1Turn;
    private int player2Turn;
    Random randomTurn = new Random();
   	JTextField user1 = new JTextField(5);
   	JTextField user2 = new JTextField(5);
   	private int result_user ; 
   	private int winner_flag = 0;
	private JPanel toolbar;
    private JPanel panel;
    private JLabel timerLabel;
    private TimeAttackRunnable timeattack;
   	
public GW_2playerMode(Minefield minefield) {
	
	initComponents();
	
	this.minefield = minefield;
	
	JPanel match = new JPanel();
	match.add(new JLabel("user1"));
	match.add(user1);
	match.add(Box.createHorizontalStrut(20));
	match.add(new JLabel("user2"));
	match.add(user2);
	match.add(Box.createHorizontalStrut(20));
	
	result_user = JOptionPane.showConfirmDialog(null, match, 
			"Input user name [Within 5 letters]", JOptionPane.OK_CANCEL_OPTION);

	player1Turn=randomTurn.nextInt(10)%2;
	
	if (player1Turn==0) {
		player2Turn=1;
	}
	else if(player1Turn==1) {
		player2Turn=0;
	}
	if(player1Turn > player2Turn) {
    	JOptionPane.showMessageDialog(null, user1.getText() + "is first turn. [W:↑] [A:←] [S:↓] [D:→] [E:change button states] [Space:click button] ", "순서 정하기", JOptionPane.INFORMATION_MESSAGE);
    	
    }
    else 					 {
    	JOptionPane.showMessageDialog(null, user2.getText() + "is first turn. [방향키] [M:change button states] [Space:click button]", "순서 정하기", JOptionPane.INFORMATION_MESSAGE);
    }
	
	  buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];
	  
      GridLayout GLayout = new GridLayout(minefield.getWidth(),minefield.getHeight());
      
      panel = new JPanel(GLayout);

	
//      getContentPane().setLayout(new GridLayout(minefield.getWidth(), minefield.getHeight()));
      
      ActionListener action = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              ButtonMinefield button = (ButtonMinefield) e.getSource();
              int x = button.getCol();
              int y = button.getLine();
              
        		winner_flag++;
              
              minefield.revealGrid(x, y);
              updateButtonsStates();
              if (minefield.isGameFinished()) {
            	      
                  if (minefield.isPlayerDefeated()) {
                	  
                	  if(player1Turn > player2Turn && winner_flag %2 == 0)
                      JOptionPane.showMessageDialog(null, user1.getText() + "is Victory!", "Congratulantion", JOptionPane.INFORMATION_MESSAGE);
                	  else if(player1Turn > player2Turn && winner_flag %2 == 1)
                	  JOptionPane.showMessageDialog(null, user2.getText() + "is Victory!", "Congratulantion", JOptionPane.INFORMATION_MESSAGE);	  
                	  else if(player1Turn < player2Turn && winner_flag %2 ==0)
                	  JOptionPane.showMessageDialog(null, user2.getText() + "is Victory!", "Congratulantion", JOptionPane.INFORMATION_MESSAGE);  
                	  else if(player1Turn < player2Turn && winner_flag %2 ==1)
                      JOptionPane.showMessageDialog(null, user1.getText() + "is Victory!", "Congratulantion", JOptionPane.INFORMATION_MESSAGE);
                  } else {
                      JOptionPane.showMessageDialog(null, "Tied", "Tied", JOptionPane.INFORMATION_MESSAGE);
                  }
                  setVisible(false);
              }
          }
      };

      KeyListener keyListener = new KeyListener() {
          @Override
          public void keyPressed(KeyEvent e) {
        	  
              ButtonMinefield botao = (ButtonMinefield) e.getSource();
              int x = botao.getCol();
              int y = botao.getLine();
              if (e.getKeyCode() == KeyEvent.VK_W && x > 0) {
                  buttons[x - 1][y].requestFocus();
              } else if (e.getKeyCode() == KeyEvent.VK_A && y > 0) {
                  buttons[x ][y - 1].requestFocus();
              } else if (e.getKeyCode() == KeyEvent.VK_S && x < minefield.getWidth() - 1) {
            	  buttons[x + 1][y].requestFocus();
              } else if (e.getKeyCode() == KeyEvent.VK_D && y < minefield.getHeight() - 1 ) {
                  buttons[x][y + 1].requestFocus();
              }
                else if(e.getKeyCode() == KeyEvent.VK_E) {
                	 if (minefield.getGridState(x, y) == minefield.COVERED) {
                         minefield.setMineMarked(x, y);
                     } 
                     else if (minefield.getGridState(x, y) == minefield.MARKED) {
                         minefield.setMineQuestion(x, y);
                     } else if (minefield.getGridState(x, y) == minefield.QUESTION) {
                         minefield.setMineCovered(x, y);
                     }
                	 updateButtonsStates();
                }
              
                else if (e.getKeyCode() == KeyEvent.VK_UP && x > 0) {
                  buttons[x-1][y ].requestFocus();
              } else if (e.getKeyCode() == KeyEvent.VK_LEFT && y > 0) {
                  buttons[x ][y-1].requestFocus();
              } else if (e.getKeyCode() == KeyEvent.VK_DOWN && x
                      < minefield.getWidth() - 1) {
                  buttons[x+1][y ].requestFocus();
              } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && y
                      < minefield.getHeight() - 1) {
                  buttons[x ][y+1].requestFocus();
              }
              	
              	else if (e.getKeyCode() == KeyEvent.VK_M) {
                  if (minefield.getGridState(x, y) == minefield.COVERED) {
                      minefield.setMineMarked(x, y);
                  } 
                  else if (minefield.getGridState(x, y) == minefield.MARKED) {
                      minefield.setMineQuestion(x, y);
                  } else if (minefield.getGridState(x, y) == minefield.QUESTION) {
                      minefield.setMineCovered(x, y);
                  }
                  updateButtonsStates();
              }
              	else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
              	} 
          }

          @Override
          public void keyTyped(KeyEvent ke) {
          }

          @Override
          public void keyReleased(KeyEvent ke) {
          }

      };
    
      getContentPane().setLayout(new BorderLayout());
      toolbar = new JPanel();
      toolbar.setLayout(new FlowLayout());
      timerLabel = new JLabel("1000");
      toolbar.add(timerLabel);
      
      for (int x = 0; x < minefield.getWidth(); x++) {
          for (int y = 0; y < minefield.getHeight(); y++) {
              buttons[x][y] = new ButtonMinefield(x, y);
              buttons[x][y].addActionListener(action);
              buttons[x][y].addKeyListener(keyListener);
              panel.add(buttons[x][y]);
          }
      }
      getContentPane().add(panel,BorderLayout.CENTER);
      getContentPane().add(toolbar,BorderLayout.NORTH);
      
      getTime(timerLabel);
      
      timeattack = new TimeAttackRunnable(timerLabel);
      Thread ta = new Thread(timeattack);
    		  
      ta.start();
      
  }
    
}