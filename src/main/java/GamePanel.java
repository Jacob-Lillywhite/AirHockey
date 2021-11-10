import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 600;
    static final int BALL_DIAMETER = 50;
    static final int PADDLE_HEIGHT = 100;
    static final int PADDLE_WIDTH = 15;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle playerPaddle;
    Paddle comPaddle;
    Ball ball;
    Score score;


    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        newPaddles();
        newBall();
        score = new Score(SCREEN_WIDTH, SCREEN_HEIGHT);
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void newPaddles() {
        playerPaddle = new Paddle(SCREEN_WIDTH - PADDLE_WIDTH, SCREEN_HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        comPaddle = new Paddle(0, SCREEN_HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    private void newBall() {
        random = new Random();
        ball = new Ball(SCREEN_WIDTH / 2 - BALL_DIAMETER / 2, SCREEN_HEIGHT / 2 - BALL_DIAMETER / 2, BALL_DIAMETER, BALL_DIAMETER);
    }

    public void paintComponent(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics graphics) {
        //region draw stage
        graphics.setColor(Color.red);
        Graphics2D g2d = (Graphics2D) graphics;
        float[] dash1 = {10f, 0f, 10f};
        BasicStroke bs1 = new BasicStroke(10,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND,
                10f,
                dash1,
                10f);
        g2d.setStroke(bs1);
        g2d.drawLine(SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT);
        BasicStroke bs2 = new BasicStroke(10);
        g2d.setStroke(bs2);
        g2d.drawLine(0, 5, SCREEN_WIDTH, 5);
        g2d.drawLine(0, SCREEN_HEIGHT - 5, SCREEN_WIDTH, SCREEN_HEIGHT - 5);
        g2d.drawLine(SCREEN_WIDTH / 4 + SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 4 + SCREEN_WIDTH / 2, SCREEN_HEIGHT);
        g2d.drawLine(SCREEN_WIDTH / 4, 0, SCREEN_WIDTH / 4, SCREEN_HEIGHT);
        g2d.fillOval(SCREEN_WIDTH / 2 - BALL_DIAMETER / 2, SCREEN_HEIGHT / 2 - BALL_DIAMETER / 2, BALL_DIAMETER, BALL_DIAMETER);
        g2d.drawRect(0, SCREEN_HEIGHT / 2 - 300 / 2, 100, 300);
        g2d.drawRect(SCREEN_WIDTH - 100, SCREEN_HEIGHT / 2 - 300 / 2, 100, 300);
        graphics.setColor(Color.blue);
        g2d.drawOval(SCREEN_WIDTH / 2 - 200 / 2, SCREEN_HEIGHT / 2 - 200 / 2, 200, 200);

        //endregion
        playerPaddle.draw(graphics);
        comPaddle.draw(graphics);
        ball.draw(graphics);
        score.draw(graphics);

    }

    public void checkCollisions() {
        //region Paddle Border Collisions
        if (playerPaddle.y <= 0) {
            playerPaddle.y = 0;
        }
        if (playerPaddle.y >= (SCREEN_HEIGHT - PADDLE_HEIGHT)) {
            playerPaddle.y = SCREEN_HEIGHT - PADDLE_HEIGHT;
        }
        if (comPaddle.y <= 0) {
            comPaddle.y = 0;
        }
        if (comPaddle.y >= (SCREEN_HEIGHT - PADDLE_HEIGHT)) {
            comPaddle.y = SCREEN_HEIGHT - PADDLE_HEIGHT;
        }
        //endregion
        //region Ball Border Collisions
        if (ball.y <= 0) {
            ball.setYDir(-ball.yVelocity);
        }
        if (ball.y >= SCREEN_HEIGHT - BALL_DIAMETER) {
            ball.setYDir(-ball.yVelocity);
        }

        //endregion
        //region Paddle-Ball Collisions
        if (ball.intersects(playerPaddle)) {
            ball.xVelocity *= -1;
            if (ball.xVelocity < 2) {
                ball.xVelocity *= 2;
            }
            if (ball.yVelocity > 0) {
                ball.yVelocity++;
            } else {
                ball.yVelocity--;
            }

            ball.setXDir(ball.xVelocity);
            ball.setYDir(ball.yVelocity);
        }
        if (ball.intersects(comPaddle)) {
            ball.xVelocity *= -1;
            if (ball.xVelocity < 2) {
                ball.xVelocity *= 2;
            }
            if (ball.yVelocity > 0) {
                ball.yVelocity++;
            } else {
                ball.yVelocity--;
            }

            ball.setXDir(ball.xVelocity);
            ball.setYDir(ball.yVelocity);
        }
        //endregion
        //region Goal Collisions
        if ((ball.x < 0 && ball.y <= SCREEN_HEIGHT / 4) || (ball.x < 0 && ball.y >= SCREEN_HEIGHT / 2 + SCREEN_HEIGHT / 4)) {
            ball.setXDir(-ball.xVelocity);
        }
        if (ball.x < -BALL_DIAMETER / 2 && ball.y >= SCREEN_HEIGHT / 4 && ball.y <= (SCREEN_HEIGHT / 2 + SCREEN_HEIGHT / 4)) {
            score.player1++;
            newPaddles();
            newBall();
        }
        if ((ball.x >= SCREEN_WIDTH - BALL_DIAMETER && ball.y <= SCREEN_HEIGHT / 4) || (ball.x >= SCREEN_WIDTH - BALL_DIAMETER && ball.y >= SCREEN_HEIGHT / 2 + SCREEN_HEIGHT / 4)) {
            ball.setXDir(-ball.xVelocity);
        }
        if (ball.x >= SCREEN_WIDTH && ball.y >= SCREEN_HEIGHT / 4 && ball.y <= SCREEN_HEIGHT / 2 + SCREEN_HEIGHT / 4) {
            score.player2++;
            newPaddles();
            newBall();
        }
        //endregion

    }

    public void move() {
        playerPaddle.move();
        comPaddle.move();
        ball.move();
    }

    public class ActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            playerPaddle.keyPressed(e);
            comPaddle.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            playerPaddle.keyReleased(e);
            comPaddle.keyReleased(e);
        }
    }

    @Override
    public void run() {
        // Game Loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double nanoSeconds = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSeconds;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollisions();
                repaint();
                delta--;
            }
        }

    }
}
