import java.awt.*;

public class Score extends Rectangle{

    static int SCREEN_WIDTH;
    static int SCREEN_HEIGHT;
    int player1;
    int player2;

    Score(int SCREEN_WIDTH, int SCREEN_HEIGHT){
        this.SCREEN_WIDTH = SCREEN_WIDTH;
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
    }
    public void draw(Graphics graphics){
        graphics.setColor(Color.blue);
        graphics.setFont(new Font("Helvetica", Font.BOLD, 60));
        graphics.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (SCREEN_WIDTH/2)-110, 80);
        graphics.setColor(Color.red);
        graphics.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (SCREEN_WIDTH/2)+50, 80);

    }
}
