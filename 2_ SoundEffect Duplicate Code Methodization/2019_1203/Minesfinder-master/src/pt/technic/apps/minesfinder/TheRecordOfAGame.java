package pt.technic.apps.minesfinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.event.*;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.*;
import java.util.*;


public class TheRecordOfAGame extends javax.swing.JFrame {
    
	private Minefield minefield;
    private ButtonMinefield[][] buttons;
	
    public ArrayList<Integer> recClickX = new ArrayList<>();
    public ArrayList<Integer> recClickY = new ArrayList<>();
    public  static ArrayList<Integer> recMineX = new ArrayList<>();
	public  static ArrayList<Integer> recMineY = new ArrayList<>();

    
  
    public int recSizeX;
    public int recSizeY;
    public static int numOfMines=0;
    
    public int tmpCoordiX;
    public int tmpCoordiY;
    public int recNum=0;
    
    
 
    public void setSize(int x, int y) {
		recSizeX = x;
		recSizeY = y;
	}
    
    public void recClick(int x, int y) {
		tmpCoordiX = x;
		tmpCoordiY = y;
		recClickX.add(tmpCoordiX);
		recClickY.add(tmpCoordiY);
		recNum++;
	}
    
    public void reviewUpdateButtonsStates() {
        for (int x = 0; x < recSizeX; x++) {
            for (int y = 0; y < recSizeY; y++) {
            	int rvstate = minefield.getGridState(x, y);
                buttons[x][y].setEstado(rvstate);
            }
        }
    }
  
    public void RecGameselectionDialog(){
        int reviewOption=JOptionPane.showConfirmDialog(null, "복기하시겠습니까?", "review the Last Game", JOptionPane.YES_NO_OPTION);
        
        if (reviewOption==JOptionPane.YES_OPTION) {
        	showRec1();
        }else if(reviewOption==JOptionPane.CLOSED_OPTION) {
        	 setVisible(false);
        }else if(reviewOption==JOptionPane.NO_OPTION) {
        	 setVisible(false);
        }
    }
    
	public void showRec1() {
			Minefield.isShow=1;
			
			for (int i=0;i<recSizeX;i++) {
				for (int j=0;j<recSizeY;j++) {
  					minefield.setMineCovered(i, j);
				}
			}
			
			reviewUpdateButtonsStates();
			
            JOptionPane.showMessageDialog(null, "지금부터 복기를 시작합니다. 새 창이 뜨면 space바를 눌러 진행해 주세요",
                    "복기", JOptionPane.INFORMATION_MESSAGE);
			recNum=recClickX.size();

			ShowRec2(recSizeX,recSizeY,recNum,recClickX,recClickY);

		}
    
    
	   public void ShowRec2(int recSizeX,int recSizeY,int recNum, ArrayList<Integer> reclickX, ArrayList <Integer> reclickY ){
		   	
		   for (int i=0;i<recNum;i++) {
				
				int x=recClickX.get(i);
				int y=recClickY.get(i);
				
				minefield.revealGrid(x, y);
				

				reviewUpdateButtonsStates();
                
				JOptionPane.showMessageDialog(null,i+"번째\n \"새 창이 뜨면\" space바를 눌러 진행해주세요",
		                   "복기", JOptionPane.INFORMATION_MESSAGE);
			}
				JOptionPane.showMessageDialog(null,"복기가 완료되었습니다",
	                   "복기", JOptionPane.INFORMATION_MESSAGE);
				
				Minefield.isShow=0;
				
//				Minefield.isShow=0;
				
//				Container c = getContentPane().getParent();
//				
//				c.setVisible(false);
//				getContentPane().setVisible(false);
	    }
	    
	   public TheRecordOfAGame(Minefield minefield,ButtonMinefield[][] buttons) {
		   this.minefield = minefield;
		   this.buttons = buttons;
		   
	   }
}
