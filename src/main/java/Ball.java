import java.awt.*;

public class Ball {
    private static final int SIZE = 20;
    private int x, y;
    private int xVelocity, yVelocity;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.xVelocity = -5;
        this.yVelocity = 5;
    }

    public void update(Paddle p1, Paddle p2) {
        x += xVelocity;
        y += yVelocity;

        if (y <= 0 || y >= PongGame.HEIGHT - SIZE) {
            yVelocity = -yVelocity;
        }

        if (getBounds().intersects(p1.getBounds()) || getBounds().intersects(p2.getBounds())) {
            xVelocity = -xVelocity;
        }

        if (x < 0 || x > PongGame.WIDTH - SIZE) {
            x = PongGame.WIDTH / 2 - SIZE / 2;
            y = PongGame.HEIGHT / 2 - SIZE / 2;
            xVelocity = -xVelocity;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, SIZE, SIZE);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
}
