package pt.technic.apps.minesfinder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GW_1playerMode extends GameWindow{
		
	private JPanel toolbar;
    private JPanel panel;
    private JLabel timerLabel;
    
	public GW_1playerMode(Minefield minefield, RecordTable record) {
	       
    	initComponents();
                
        this.minefield = minefield;
        this.record = record;

        buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];
        
        GridLayout GLayout = new GridLayout(minefield.getWidth(),minefield.getHeight());
        
        panel = new JPanel(GLayout);

        
//        getContentPane().setLayout(new GridLayout(minefield.getWidth(),
//                minefield.getHeight()));
        
        Review RecGame = new Review(minefield, buttons);
        RecGame.setSize(minefield.getWidth(),minefield.getHeight());
    

        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonMinefield button = (ButtonMinefield) e.getSource();
                int x = button.getCol();
                int y = button.getLine();
                
                isCovered = true;
                if(minefield.getGridState(x, y)==9) {
                	RecGame.reviewMapClickedX.add(x);
                	RecGame.reviewMapClickedY.add(y);
                }
                minefield.revealGrid(x, y);
                updateButtonsStates();
                if (minefield.isGameFinished()) {
                    if (minefield.isPlayerDefeated()) {
                        JOptionPane.showMessageDialog(null, "Oh, a mine broke",
                                "Lost!", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Congratulations. You managed to discover all the mines in "
                                + (minefield.getGameDuration() / 1000) + " seconds",
                                "victory", JOptionPane.INFORMATION_MESSAGE
                        );

                        long a = minefield.getGameDuration();
                        long b = record.getScore();
                        boolean newRecord = minefield.getGameDuration() < record.getScore();

                        if (newRecord) {
                            String name = JOptionPane.showInputDialog("Enter your name");
                            if(name != "")
                                record.setRecord(name, minefield.getGameDuration());
                        }
                        RecGame.RecGameselectionDialog();
                        }
                    }
                }
            
        };

        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    ButtonMinefield botao = (ButtonMinefield) e.getSource();
                    int x = botao.getCol();
                    int y = botao.getLine();
                    if (minefield.getGridState(x, y) == minefield.COVERED) {
                        minefield.setMineMarked(x, y);
                    } else if (minefield.getGridState(x,
                            y) == minefield.MARKED) {
                        minefield.setMineQuestion(x, y);
                    } else if (minefield.getGridState(x,
                            y) == minefield.QUESTION) {
                        minefield.setMineCovered(x, y);
                    }
                    updateButtonsStates();
                }
            }

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        };

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                ButtonMinefield botao = (ButtonMinefield) e.getSource();
                int x = botao.getCol();
                int y = botao.getLine();
                
                
                if (e.getKeyCode() == KeyEvent.VK_LEFT && y > 0) {
                    buttons[x][y - 1].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_UP && x > 0) {
                    buttons[x - 1][y].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && y
                        < minefield.getHeight() - 1) {
                    buttons[x][y + 1].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && x
                        < minefield.getWidth() - 1) {
                    buttons[x + 1][y].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_M) {
                    if (minefield.getGridState(x, y) == minefield.COVERED) {
                        minefield.setMineMarked(x, y);
                    } else if (minefield.getGridState(x,
                            y) == minefield.MARKED) {
                        minefield.setMineQuestion(x, y);
                    } else if (minefield.getGridState(x,
                            y) == minefield.QUESTION) {
                        minefield.setMineCovered(x, y);
                    }
                    updateButtonsStates();
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
                buttons[x][y].addMouseListener(mouseListener);
                buttons[x][y].addKeyListener(keyListener);
                panel.add(buttons[x][y]);
            }
        }        
        getContentPane().add(panel,BorderLayout.CENTER);
        getContentPane().add(toolbar,BorderLayout.NORTH);
        
        getTime(timerLabel);
        
        timeattack = new TimeAttackRunnable(timerLabel);
        Thread ta = new Thread(timeattack);
//        timeattack.getClose(getDefaultCloseOperation());
        timeattack.getRootPane(getContentPane());
        ta.start();
        
    }
}
