package pt.technic.apps.minesfinder;

public class Main {
	 public static void main(String args[]) {
	        try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(MinesFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
	        
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new MinesFinder().setVisible(true);
	            }
	        });
	        Sound.backgroundSound("src/music/maple.wav");
	    }
}
