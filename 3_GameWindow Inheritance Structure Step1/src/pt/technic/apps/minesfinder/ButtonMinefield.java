package pt.technic.apps.minesfinder;

import java.awt.Color;
import javax.swing.JButton;

public class ButtonMinefield extends JButton {
	
    private int state, col, line;
    
    static boolean updateBusted=false;
    static boolean updateEmpty=false;
    
    GameWindow sound = new GameWindow();

    public ButtonMinefield(int col, int line) {
        this.col = col;
        this.line = line;
        state=Minefield.COVERED;
    }
    
    public void setEstado(int state) {
        this.state=state;
        switch (state) {
            case Minefield.EMPTY:
                setText("");
                setBackground(Color.gray);
                updateEmpty=true;
                break;
            case Minefield.COVERED:
                setText("");
                setBackground(null);
                break;
            case Minefield.QUESTION:
                setText("?");
                setBackground(Color.yellow);
                break;
            case Minefield.MARKED:
                setText("!");
                setBackground(Color.red);
                break;
            case Minefield.BUSTED:
                setText("*");
                setBackground(Color.orange);
                updateBusted=true;
                break;
            default:
                setText(String.valueOf(state));
                setBackground(Color.gray);
                break;
        }
    }

    public int getState() {
        return state;
    }

    public int getCol() {
        return col;
    }

    public int getLine() {
        return line;
    }
    
    
}
