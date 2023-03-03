import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pong extends JPanel implements KeyListener, ActionListener {

    private Timer timer;
    private int delay = 8;
    private int player1Y = 200;
    private int player2Y = 200;
    private int ballX = 300;
    private int ballY = 200;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private int player1Score = 0;
    private int player2Score = 0;

    public Pong() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // draw the background
        g.setColor(Color.black);
        g.fillRect(0, 0, 600, 400);

        // draw the paddles
        g.setColor(Color.white);
        g.fillRect(50, player1Y, 10, 50);
        g.fillRect(540, player2Y, 10, 50);

        // draw the ball
        g.setColor(Color.white);
        g.fillOval(ballX, ballY, 20, 20);

        // draw the scores
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("" + player1Score, 150, 50);
        g.drawString("" + player2Score, 450, 50);
    }

    public void actionPerformed(ActionEvent e) {
        timer.start();
        ballX += ballXdir;
        ballY += ballYdir;

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

        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (player2Y > 0) {
                player2Y -= 20;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (player2Y < 350) {
                player2Y += 20;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (player1Y > 0) {
                player1Y -= 20;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (player1Y < 350) {
                player1Y += 20;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        Pong game = new Pong();
        frame.add(game);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
