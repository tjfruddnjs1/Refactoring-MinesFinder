package pt.technic.apps.minesfinder;

public class GameWindow extends javax.swing.JFrame {

	protected ButtonMinefield[][] buttons;
	protected Minefield minefield;
	protected RecordTable record; 

    protected void updateButtonsStates() {
        for (int x = 0; x < minefield.getWidth(); x++) {
            for (int y = 0; y < minefield.getHeight(); y++) {
                buttons[x][y].setEstado(minefield.getGridState(x, y));
            }
        }
        if(ButtonMinefield.isEmpty==true) {
        	    Sound.pressButtonSound("src/music/Beat.wav");
        }
 
         if(ButtonMinefield.isBusted==true) {
        	 Sound.pressButtonSound("src/music/DeadMarine.wav");
        }
     	ButtonMinefield.isBusted=false;
     	ButtonMinefield.isEmpty=false;
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
