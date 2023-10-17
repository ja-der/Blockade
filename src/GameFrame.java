import javax.swing.JFrame;

// intialize and setup the game frame 
public class GameFrame extends JFrame {

    GameFrame(){
        this.add(new GamePanel());
        this.setTitle("Blockade");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack(); // will pack down added elements into the jframe
        this.setVisible(true);
    }
}