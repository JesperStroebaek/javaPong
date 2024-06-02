import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Pong extends Canvas implements KeyListener {

    private static final long serialVersionUID = 1L;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private int player1Y = 0;
    private int player2Y = 0;
    private int ballX = 0;
    private int ballY = 0;

    public Pong() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(this);

        // Start bolden i midten af skærmen
        ballX = WIDTH / 2;
        ballY = HEIGHT / 2;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Tegn spillerne
        g.setColor(Color.WHITE);
        g.fillRect(-300, player1Y, 20, 100);
        g.fillRect(300, player2Y, 20, 100);

        // Tegn bolden
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, 50, 50);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player1Y -= 20;
                break;
            case KeyEvent.VK_S:
                player1Y += 20;
                break;
            case KeyEvent.VK_UP:
                player2Y -= 20;
                break;
            case KeyEvent.VK_DOWN:
                player2Y += 20;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Pong());
        frame.pack();
        frame.setVisible(true);
    }

    private void update() {
        // Opdater boldens position
        ballX += 5;
        ballY += 5;

        // Tjek for kollisioner med skærmen
        if (ballX > WIDTH || ballX < 0) {
            ballX = WIDTH / 2;
            ballY = HEIGHT / 2;
        }

        // Tjek for kollisioner med spillerne
        if (ballY > 280 && ballX > -300 && ballX < 300) {
            ballY = 280;
        } else if (ballY < -280 && ballX > -300 && ballX < 300) {
            ballY = -280;
        }
    }

    public void run() {
        while (true) {
            update();
            repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

