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
    public int[] getScores(){
        int[] scores = {player1, player2};
        return scores;
    }

    public boolean checkWinner(Graphics graphics){
        if(player1 >= 10){
            drawWinner(graphics, 1);
            return false;
        }
        if(player2 >= 10){
            drawWinner(graphics, 2);
            return false;
        }
        return true;
    }

    public void drawWinner(Graphics graphics, int playerNumber){
        String winner;
        if(playerNumber == 1){
            winner = "Blue";
        }else{
            winner = "Red";
        }
        graphics.setColor(new Color(0, 0, 0, 200));
        graphics.fillRect(0, SCREEN_HEIGHT/4, 1000, SCREEN_HEIGHT/2);
        graphics.setColor(Color.GREEN);
        graphics.setFont(new Font("Monospaced", Font.BOLD, 75));
        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
        graphics.drawString( winner + " Wins!", (SCREEN_WIDTH - metrics.stringWidth( winner + " Wins!"))/2, SCREEN_HEIGHT/3 + graphics.getFont().getSize() + 25);
        graphics.setFont(new Font("Monospaced", Font.BOLD, 40));
        FontMetrics metrics2 = graphics.getFontMetrics(graphics.getFont());
        graphics.drawString("Press Spacebar to reset", (SCREEN_WIDTH - metrics2.stringWidth("Press Spacebar to reset"))/2, SCREEN_HEIGHT/2 + graphics.getFont().getSize() + 25);

    }

}
