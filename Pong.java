import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pong extends JPanel implements KeyListener, ActionListener {


    //Setting up every Variable needed. Names Explain their Function
    private Timer timer;
    private int delay = 8; //in ms
    private int player1Y = 200;
    private int player2Y = 200;
    private int ballX = 300;
    private int ballY = 200;
    private int ballXdir = -3; 
    private int ballYdir = -3;
    private int player1Score = 0;
    private int player2Score = 0;
    private static TitleScreen title;
    private JFrame frame;
    private JButton startButton;
  

    public Pong() {
        
        //Make the Game responsive to input
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        //Used to call the Actionperformed method to repaint everything reguraly
        timer = new Timer(delay, this);
       
       //Define the TitleScreen and the StartButton
        title = new TitleScreen();
        startButton =new JButton();
        startButton.addActionListener(this);
        
        //Adding the TitleScreen and the Button to the Frame
        frame = new JFrame("Pong");
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(title);
        frame.add(startButton,BorderLayout.SOUTH);
    
        //Make the Button invisable
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
      


     
    }


    //Painting the Start of the Game
    public void paint(Graphics g) {
        
        // draw the background
        g.setColor(Color.black);
        g.fillRect(0, 0, 600, 400);

        // draw the paddles
        g.setColor(Color.white);
        g.fillRect(50, player1Y, 10, 50);
        g.fillRect(540, player2Y, 10, 50);

        // draw the ball
        g.setColor(Color.yellow);
        g.fillOval(ballX, ballY, 20, 20);

        // draw the scores
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Player 1" , 110,25);
        g.setColor(Color.blue);
        g.drawString("Player 2",  410, 25);
        g.setColor(Color.white);
        g.drawString("" + player1Score, 150, 60);
        g.drawString("" + player2Score, 450, 60);
    }

    //Is getting Called every time the set delay time is up. // Changes the Position of the Balls and the Paddels and Checks for Bounces and Scores
    public void actionPerformed(ActionEvent e) {
       
    
       //move the Ball
        ballX += ballXdir;
        ballY += ballYdir;


        //Call the StartGame Method when the SpaceBar is pressed
        if(e.getSource()==startButton){
          
           startGame();
            
        }

        // detect collisions with walls
        if (ballY < 0 || ballY > 380) {
            ballYdir = -ballYdir;
        }

        // detect collisions with paddles
        if (ballX <= 60) {
            if (ballY >= player1Y && ballY <= player1Y + 50) {
                ballXdir = -ballXdir;
            } else {
                player2Score++;
                ballX = 300;
                ballY = 200;
            }
        }

        if (ballX >= 520) {
            if (ballY >= player2Y && ballY <= player2Y + 50) {
                ballXdir = -ballXdir;
            } else {
                player1Score++;
                ballX = 300;
                ballY = 200;
            }
        }

        repaint(); //Is making the Changes Visable by Deleting Everything and Redraw the the Status
    }


    //Switch from TitleScreen to the Game
    public void startGame(){
        //Rearange the Size of the Window
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(600, 400 ));
        
        //delete the TitleScreen
        frame.getContentPane().removeAll();

        //Add the Game , make it listen and start the Timer
        frame.getContentPane().add(this);
        frame.pack();
        this.requestFocus();
        frame.repaint();
        timer.start();
    }
       
    

    //Read KeyInput
    public void keyPressed(KeyEvent e) {

       

        //Player2 Movement Up 
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (player2Y > 0) {
                player2Y -= 20;
            }
        }

        //Player2 Movement Down
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (player2Y < 350) {
                player2Y += 20;
            }
        }
         //Player1 Movement Up
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (player1Y > 0) {
                player1Y -= 20;
            }
        }
         //Player1 Movement Down
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (player1Y < 350) {
                player1Y += 20;
            }
        }
    }


    //Needs to be implemented because of the Interface but not Used so they can stay empty
    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        Pong game = new Pong(); //Initaliing the Game
  
      
    }
}
