import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;


public class TitleScreen extends JPanel{
boolean next ;
   



//Painting the Screen
    public void paint(Graphics g){

        g.setColor(Color.black);
        g.fillRect(0, 0, 600, 400);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(" PONG " , 200, 50);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Press Space to Start", 200, 200);

    }


   
    }

 


    




    

