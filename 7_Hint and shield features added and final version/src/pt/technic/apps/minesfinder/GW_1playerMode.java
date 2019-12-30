package pt.technic.apps.minesfinder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GW_1playerMode extends GameWindow{
	
	private JPanel toolbar;
    private JPanel panel;
    private JLabel timerLabel;
    private int numOfHint;
    private JButton shieldBtn;
    private JButton timerBtn;
    private JButton hintBtn;
    private JLabel shieldLabel;     
    private JPanel btnPanel;
    private JLabel hintLabel;
    
    public void useHint(int x,int y) {
    	if(numOfHint>0) {
    		if (minefield.hasMine(x, y)==true) {
    			minefield.setMineMarked(x, y);
    			updateButtonsStates();
    		}else {
    			minefield.revealGrid(x, y);
    			updateButtonsStates();
    		}
    		numOfHint--;
    		hintLabel.setText(Integer.toString(numOfHint));
    		
    	}else {
    		JOptionPane.showMessageDialog(null, "힌트를 모두 사용하셨습니다",
                                        "힌트를 사용할 수 없음", JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    
	public GW_1playerMode(Minefield minefield, RecordTable record) {
		
		minefield.numShield = 3;
   	 	
	       
    	initComponents();
                
        this.minefield = minefield;
        this.record = record;

        buttons = new ButtonMinefield[minefield.getWidth()][minefield.getHeight()];
        
        GridLayout GLayout = new GridLayout(minefield.getWidth(),minefield.getHeight());
        
        panel = new JPanel(GLayout);
        
        getContentPane().setLayout(new GridLayout(minefield.getWidth(),
                minefield.getHeight()));
        
        Review RecGame = new Review(minefield, buttons);
        RecGame.setSize(minefield.getWidth(),minefield.getHeight());
        
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonMinefield button = (ButtonMinefield) e.getSource();
                int x = button.getCol();
                int y = button.getLine();

                RecGame.reviewMapClicked(x, y);
                
                minefield.revealGrid(x, y);
                updateButtonsStates();
                if(minefield.hasMine(x,y)) {
                    if(minefield.numShield>0) {                      
                  	  minefield.numShield -=1;
                             JOptionPane.showMessageDialog(null, "Use Shield",
                                        "Lost!", JOptionPane.INFORMATION_MESSAGE);
                              shieldLabel.setText(Integer.toString(minefield.numShield));
                              RecGame.reviewMapdelete();
                       }
                 }
                if (minefield.isGameFinished()) {
                    if (minefield.isPlayerDefeated()) {
                        JOptionPane.showMessageDialog(null, "Oh, a mine broke",
                                "Lost!", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                    } else {
                    	minefield.numShield=0;// test
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
                int x = botao.getCol();//();
                int y = botao.getLine();//();
                
                try {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && y > 0) {//수정사항 1. 키보드를 이용할 수 있도록 수정
                    buttons[x][y - 1].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_UP && x > 0) {//
                    buttons[x - 1][y].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && y//
                        < minefield.getHeight() - 1) {
                    buttons[x][y + 1].requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && x//
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
                } else if (e.getKeyCode() == KeyEvent.VK_H) {
                	buttons[x][y].requestFocus();
                	useHint(x,y);
                }
                }catch(ArrayIndexOutOfBoundsException e1){
                    buttons[0][0].requestFocus();
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
        timerBtn = new JButton("TimeAttack");
        timerLabel = new JLabel("1000");
        timerBtn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { 
            	JOptionPane.showMessageDialog(null, "설정한 시간이 다 지나고 나면 사용자에게 계속할지 여부를 물어봅니다.",
                        "Imformation", JOptionPane.INFORMATION_MESSAGE
                );
            }  
         });
        toolbar.add(timerBtn);
        toolbar.add(timerLabel);
        shieldBtn = new JButton("Shield");
        shieldBtn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { 
            	JOptionPane.showMessageDialog(null, "지뢰 클릭시 Shield 카운트가 줄어들고 게임은 계속됩니다.",
                        "Imformation", JOptionPane.INFORMATION_MESSAGE
                );
            }  
         });
        shieldLabel = new JLabel("3");
        toolbar.add(shieldBtn);
        toolbar.add(shieldLabel);
        
        numOfHint=3;
        hintBtn = new JButton("Hint");
        hintBtn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { 
            	JOptionPane.showMessageDialog(null, "키보드로 알고자하는 버튼에 두고 H를 누르면 지뢰가 아닐시엔 밝혀주고 지뢰일시 지뢰표시가 됩니다.",
                        "Imformation", JOptionPane.INFORMATION_MESSAGE
                );
            }  
         });
        hintLabel = new JLabel(Integer.toString(numOfHint));
        toolbar.add(hintBtn);
        toolbar.add(hintLabel);
        
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
        timeattack.getRootPane(getContentPane());
        ta.start();
        
        
    }
}
