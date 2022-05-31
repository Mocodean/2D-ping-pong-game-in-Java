
import javax.swing.*;


public class StartFrame extends JFrame {
    public StartFrame() {
        StartPanel s = new StartPanel();
        this.add(s);
        this.setTitle("Jatek");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();     //nem kell bealitan a frame meretet beallitani, a panelhez ileszkedik
        this.setVisible(true);
        this.setLocationRelativeTo(null);       //az kepernyo kozepen jelenjen meg az ablak
    }
}



