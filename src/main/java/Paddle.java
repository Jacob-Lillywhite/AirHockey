import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    private final int id;
    private static final int speed = 10;
    private int yVelocity;
    private int centerY;

    Paddle(int x, int y, int width, int height, int id){
    super(x,y,width,height);
    this.id = id;
    }

    public void setYDir(int yDir){
        yVelocity = yDir;
    }

    public void move(){
            y += yVelocity;
            centerY = y+(height/2);
    }

    public void move(Ball ball){
        // While the paddle is not at the right height
        // We'll move it to match the ball height
        centerY = y+(height/2);
        int ballDist = ball.centerY - centerY;
        if(ball.centerY != centerY){
            int MAX_COM_PADDLE_SPEED = 8;
            if(ball.centerY > centerY){
              for(int i = MAX_COM_PADDLE_SPEED; i > 0; i--){
                  if(ballDist % i == 0){
                      y += i;
                  }
              }
           } else{
                for(int i = MAX_COM_PADDLE_SPEED; i > 0; i--){
                    if(ballDist % i == 0){
                        y -= i;
                    }
                }
           }
        }
    }
    public void draw(Graphics graphics){
        if(id==1){
           graphics.setColor(Color.blue);
           graphics.fillRect(x, y, width, height);
        }else{
            graphics.setColor(Color.red);
            graphics.fillRect(x, y, width, height);
        }
    }
    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode() ==  KeyEvent.VK_UP) {
                    setYDir(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    setYDir(speed);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode() ==  KeyEvent.VK_W) {
                    setYDir(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    setYDir(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode() ==  KeyEvent.VK_UP) {
                    setYDir(0);
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    setYDir(0);
                }
                break;
            case 2:
                if(e.getKeyCode() ==  KeyEvent.VK_W) {
                    setYDir(0);
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    setYDir(0);
                }
                break;
        }
    }
}
