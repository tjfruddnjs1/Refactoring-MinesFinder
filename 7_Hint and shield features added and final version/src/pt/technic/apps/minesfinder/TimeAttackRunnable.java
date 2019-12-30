package pt.technic.apps.minesfinder;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TimeAttackRunnable extends JFrame implements Runnable  {
	private JLabel TAlabel;
	private int TAleftTime; 
	private Container c;
	
	public void timeOver(){
    	 int timeover=JOptionPane.showConfirmDialog(null, "타임오버되었습니다.계속하시겠습니까?", "timeover", 
    			 JOptionPane.YES_NO_OPTION);
         
         if (timeover==JOptionPane.YES_OPTION) {
         	
         }else if(timeover==JOptionPane.CLOSED_OPTION) {
         	 
        	 c.setVisible(false);
         }else if(timeover==JOptionPane.NO_OPTION) {
         	 
        	 c.setVisible(false);
         }
    }
	
	public void getRootPane(Container c){
		this.c=c;
	}
	
	TimeAttackRunnable(JLabel label) {
		TAlabel = label;
		TAleftTime=Integer.parseInt(label.getText());
	}
	
	
	@Override
	public void run() {
		while (TAleftTime>=0) {
			TAlabel.setText(Integer.toString(TAleftTime));
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				return;
			}
			TAleftTime--;
		}
		if (TAleftTime<=0) {
			timeOver();
			}
		}
	}

