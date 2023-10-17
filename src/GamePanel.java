import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 900, SCREEN_HEIGHT = 900, UNIT_SIZE = 15, DELAY = 75, GRID_BLOCKS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    int growDelay; 
    boolean running = false;
    String win;
    Timer timer;
    Random random;
    Snake snake1, snake2;

    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        random = new Random();
        snake1 = new Snake(GRID_BLOCKS, UNIT_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT);
        snake2 = new Snake(GRID_BLOCKS, UNIT_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT);
        snake1.x[0] = SCREEN_WIDTH / 2 + UNIT_SIZE;
        snake1.y[0] = SCREEN_WIDTH / 2 ;

        snake2.x[0] = SCREEN_WIDTH / 2 - UNIT_SIZE;
        snake2.y[0] = SCREEN_WIDTH / 2 ;
        snake2.direction = 'L';

        startGame();
    }

    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g) {
        if (running){
            for (int i = 0; i < snake1.bodyParts; i ++){
                g.setColor(Color.green);
                g.fillRect(snake1.x[i], snake1.y[i], UNIT_SIZE, UNIT_SIZE);
            }

            for (int j = 0; j < snake2.bodyParts; j ++){
                g.setColor(Color.CYAN);
                g.fillRect(snake2.x[j], snake2.y[j], UNIT_SIZE, UNIT_SIZE);
            }

        } else{
            gameOver(g);
        }
    }

    public void checkCollisions() {
        for (int i = snake2.bodyParts; i > 0; i --){
            if ((snake1.x[0] == snake2.x[i]) && (snake1.y[0] == snake2.y[i])){
                win = "BLUE";
                running = false;
            }
        }

        for (int i = snake1.bodyParts; i > 0; i --){
            if ((snake2.x[0] == snake1.x[i]) && (snake2.y[0] == snake1.y[i])){
                win = "GREEN";
                running = false;
            }
        }
        
    }

    public void gameOver(Graphics g) {
        
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);

        g.setFont(new Font("Ink Free", Font.BOLD, 45));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString(win + " has won!", (SCREEN_WIDTH - metrics2.stringWidth(win + " has won!"))/2, (SCREEN_HEIGHT / 2) - (metrics2.getFont().getSize() * 2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            snake1.move();
            snake2.move();
            snake1.grow();
            snake2.grow();
            repaint();

            if (!snake1.checkCollisions()) {
                win = "BLUE";
                running = false;
            }

            if (!snake2.checkCollisions()) {
                win = "GREEN";
                running = false;
            }

            checkCollisions();
        }


        if (!running) {
            timer.stop();
        }

    }

    public class myKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){

            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (snake1.direction != 'R'){
                        snake1.direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake1.direction != 'L'){
                        snake1.direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake1.direction != 'D'){
                        snake1.direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake1.direction != 'U'){
                        snake1.direction = 'D';
                    }
                    break;
            }

            switch(e.getKeyCode()){
                case KeyEvent.VK_A:
                    if (snake2.direction != 'R'){
                        snake2.direction = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if (snake2.direction != 'L'){
                        snake2.direction = 'R';
                    }
                    break;
                case KeyEvent.VK_W:
                    if (snake2.direction != 'D'){
                        snake2.direction = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                    if (snake2.direction != 'U'){
                        snake2.direction = 'D';
                    }
                    break;
            }
        }
    }
    
}
