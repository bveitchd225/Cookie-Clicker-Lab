import javax.swing.JFrame;

import gbs.GBSApp;

public class Main {
    
    // The main method creates a JFrame and adds your app as its content
    // The code below uses techniques that are not important in a first CS class
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        java.awt.EventQueue.invokeLater( 
            new Runnable() { 
                public void run() {
                    JFrame fr = new JFrame("Generic GUI App");
                    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    fr.setLocation(10, 10);
                    fr.setResizable(false);
                    fr.setIconImage(GBSApp.ICON);
                    fr.setContentPane(new CookieClicker(fr));
                    fr.pack();
                    fr.setVisible(true);
                }
            } 
        );
    }
}
