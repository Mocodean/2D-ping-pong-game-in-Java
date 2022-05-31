import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        MyPanel panel = new MyPanel();
        this.add(panel);
        this.setTitle("Jatek");
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();     //nem kell bealitan a frame meretet beallitani, a panelhez ileszkedik
        this.setVisible(true);
        this.setLocationRelativeTo(null);       //az kepernyo kozepen jelenjen meg az ablak


    }
}
