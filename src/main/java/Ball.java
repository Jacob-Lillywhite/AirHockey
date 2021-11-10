import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Ball extends Rectangle {
    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed =2;

    Ball(int x, int y, int width, int height){
        super(x, y, width, height);
        random = new Random();
        int randomXDir = random.nextInt(2);
        if(randomXDir == 0){
            randomXDir--;
        }
        setXDir(randomXDir*initialSpeed);
        int randomYDir = random.nextInt(2);
        if(randomYDir == 0){
            randomYDir--;
        }
        setYDir(randomYDir*initialSpeed);
    }
    public void keyPressed(KeyEvent e){

    }
    public void keyReleased(KeyEvent e){

    }

    public void setYDir(int yDir){
        yVelocity = yDir;
    }
    public void setXDir(int xDir){
        xVelocity = xDir;
    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics graphics){
        graphics.setColor(Color.red);
        graphics.fillOval(x, y, width, height);
    }
}
