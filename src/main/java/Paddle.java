import java.awt.*;

public class Paddle {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 100;
    private int x, y;
    private int yVelocity;
    private boolean up, down;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
        this.yVelocity = 0;
    }

    public void update() {
        if (up) {
            yVelocity = -5;
        } else if (down) {
            yVelocity = 5;
        } else {
            yVelocity = 0;
        }
        y += yVelocity;

        if (y < 0) {
            y = 0;
        } else if (y > PongGame.HEIGHT - HEIGHT) {
            y = PongGame.HEIGHT - HEIGHT;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
