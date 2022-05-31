import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel {
    private BufferedImage img;
    private static final int ABLAK_SZ = 1000;
    private static final int ABLAK_M = 650;
    private static final Dimension ABLAK_MERET = new Dimension(ABLAK_SZ, ABLAK_M);

    public StartPanel() {
        this.setPreferredSize(ABLAK_MERET);
        try {
            img = ImageIO.read(new File("hateer.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton gomb1 = new JButton(new ImageIcon("gomb.png"));
        gomb1.setBounds(805, 490, 170, 116);

        gomb1.addActionListener(e -> {
            setVisible(false);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            new MyFrame();
        });

        this.setLayout(null);
        this.add(gomb1);

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, 1000, 650, null);
    }
}
