import gbs.GBSApp;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CookieClicker extends GBSApp {

    // Declare your private instance variables here...
    private JButton moreButton;

    private int howMany;  
    private Image cookie1;
    private Image cookie2;

    // Constructor method: initialize instance variables here...
    public CookieClicker(JFrame f) {
        super.setBackground(pink);
        super.setSize(300, 250);
        super.autoArrangeGUIForMe(true);

        moreButton = new JButton("More COOKIE!");
        super.add(moreButton);
        moreButton.addActionListener(this);

        howMany= 1;
        cookie1 = super.getImage("images/one cookie.png");
        cookie2 = super.getImage("images/many cookie.png");
    }

    // Perform any custom painting (if necessary) in this method
    // Do not modify instance variables here...
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);  // Provides a blank canvas to draw on

        g.setColor(BLACK);
        g.drawImage(cookie1, 120, 80, this);

        if (howMany >= 20) {
            g.drawString(howMany+" Cookie!",10,40);
            g.drawImage(cookie2, 25,40, this);
        }
        else {
            g.drawString(howMany+" Cookies!",10,40);
        }
    }

    // Process GUI input in this method
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        howMany += 1;
        super.repaint();  //keep this as the last line of this method
    }

}