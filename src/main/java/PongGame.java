import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame extends JPanel implements Runnable, KeyListener {
    public static final int HEIGHT = 724;
    public static final int WIDTH = 1380;
  private boolean running;
    private Thread gameThread;
    private Paddle player1, player2;
    private Ball ball;

    public PongGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initGame();
    }

    private void initGame() {
        player1 = new Paddle(10, HEIGHT / 2 - 50);
        player2 = new Paddle(WIDTH - 30, HEIGHT / 2 - 50);
        ball = new Ball(WIDTH / 2 - 10, HEIGHT / 2 - 10);
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            updateGame();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateGame() {
        player1.update();
        player2.update();
        ball.update(player1, player2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player1.draw(g);
        player2.draw(g);
        ball.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            player1.setUp(true);
        } else if (key == KeyEvent.VK_S) {
            player1.setDown(true);
        } else if (key == KeyEvent.VK_UP) {
            player2.setUp(true);
        } else if (key == KeyEvent.VK_DOWN) {
            player2.setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            player1.setUp(false);
        } else if (key == KeyEvent.VK_S) {
            player1.setDown(false);
        } else if (key == KeyEvent.VK_UP) {
            player2.setUp(false);
        } else if (key == KeyEvent.VK_DOWN) {
            player2.setDown(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        PongGame pongGame = new PongGame();
        frame.add(pongGame);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
