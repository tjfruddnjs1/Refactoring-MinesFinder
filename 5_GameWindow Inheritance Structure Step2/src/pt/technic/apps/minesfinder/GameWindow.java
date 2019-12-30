package pt.technic.apps.minesfinder;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameWindow extends javax.swing.JFrame {

	protected ButtonMinefield[][] buttons;
	protected Minefield minefield;
	protected RecordTable record;
    protected boolean isCovered= false; 
    protected String Stime;
    protected int Itime;
    protected JLabel Timerlabel;
   protected TimeAttackRunnable timeattack;
    
    public void getTime(JLabel label){
    	Timerlabel = label;

    	Stime = JOptionPane.showInputDialog("시간을 입력하세요");
    	
    	try {
    		Itime=Integer.parseInt(Stime); 
    	}catch(Exception e) {
    		getTime(Timerlabel);
    	}
    	label.setText((Stime));
  	}
    
//    public void timeOver(){
//   	 int timeover=JOptionPane.showConfirmDialog(null, "타임오버되었습니다.계속하시겠습니까?", "timeover", JOptionPane.YES_NO_OPTION);
//        
//        if (timeover==JOptionPane.YES_OPTION) {
//        	timeattack.interrupt();
//        }else if(timeover==JOptionPane.CLOSED_OPTION) {
//        	 timeattack.interrupt();
//        	 setVisible(false);
//        }else if(timeover==JOptionPane.NO_OPTION) {
//        	 timeattack.interrupt();
//       	 setVisible(false);
//        }
//   }
    

    protected void updateButtonsStates() {
        for (int x = 0; x < minefield.getWidth(); x++) {
            for (int y = 0; y < minefield.getHeight(); y++) {
                buttons[x][y].setEstado(minefield.getGridState(x, y));
            }
        }
        if(isCovered==true) {
        	    Sound.pressButtonSound("src/music/Beat.wav");
        }
 
         if(Minefield.isShowAllMines==true) {
        	 Sound.pressButtonSound("src/music/DeadMarine.wav");
        }
     	isCovered=false;
    }
    
    @SuppressWarnings("unchecked")
    protected void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Game");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1094, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
        );

        pack();
    }
}
