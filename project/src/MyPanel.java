import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class MyPanel extends JPanel implements Runnable {

    static final int ABLAK_SZ = 1000;
    static final int ABLAK_M = 650;
    static final int UTO_M = 100;
    static final int UTO_SZ = 25;
    static final int LABDA_ATMERO = 20;

    static final Dimension ABLAK_MERET = new Dimension(ABLAK_SZ, ABLAK_M);

    private Uto baluto;
    private  Uto jobbuto;
    private Labda labda;
    private final Eredmeny eredmeny;


    private final Hangok pattog;
    private final Hangok utes;
    private final Hangok gol;


    public MyPanel() {
        ujUto();
        ujLabda();
        eredmeny = new Eredmeny(ABLAK_SZ, ABLAK_M);
        this.setFocusable(true);        //figyeljen a gomblenyomasokra
        this.addKeyListener(new ac_l());

        this.setPreferredSize(ABLAK_MERET);


        //hangok
        utes = new Hangok();
        utes.setFile(".//hangok//uto.wav");

        pattog = new Hangok();
        pattog.setFile(".//hangok//pattogas.wav");

        gol = new Hangok();
        gol.setFile(".//hangok//gol.wav");


        Thread jatekSzall = new Thread(this);
        jatekSzall.start();


    }

    public void ujUto() {
        baluto = new Uto(0, (ABLAK_M / 2) - (UTO_M / 2), UTO_SZ, UTO_M, 0);
        jobbuto = new Uto(ABLAK_SZ - UTO_SZ, (ABLAK_M / 2) - (UTO_M / 2), UTO_SZ, UTO_M, 1);

    }

    public void ujLabda() {
        labda = new Labda((ABLAK_SZ / 2) - (LABDA_ATMERO / 2), (ABLAK_M / 2) - (LABDA_ATMERO / 2), LABDA_ATMERO);

    }

    public void paint(Graphics g) {
        Image image = createImage(getWidth(), getHeight());
        Graphics graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        eredmeny.draw(g);
        baluto.draw(g);
        jobbuto.draw(g);
        labda.draw(g);


    }

    public void move() {
        baluto.move();          //gyorsabb lesz
        jobbuto.move();
        labda.move();

    }

    public void utkozesEllenorzes() {

        //uto mozgasi tartomanyat korlatozza
        if (baluto.y <= 0) baluto.y = 0;
        if (baluto.y >= (ABLAK_M - UTO_M)) baluto.y = ABLAK_M - UTO_M;

        if (jobbuto.y <= 0) jobbuto.y = 0;
        if (jobbuto.y >= (ABLAK_M - UTO_M)) jobbuto.y = ABLAK_M - UTO_M;


        //labda mozgasi tartomanyat korlatoza lent,fent


        if (labda.y <= 0) {
            labda.setyLepes(-labda.getyLepes());
            pattog.lejatsz();

        }
        if (labda.y >= ABLAK_M - LABDA_ATMERO) {
            labda.setyLepes(-labda.getyLepes());
            pattog.lejatsz();

        }

        //labda utovel talalkozik


        if (labda.intersects(baluto)) {       //rectangle
            utes.lejatsz();
            labda.setxLepes(-labda.getxLepes());
            labda.setxLepes(labda.getxLepes() + 1); //nehezites
            if (labda.getyLepes() > 0)
                labda.setyLepes(labda.getyLepes() + 1);       //nehezites

            else
                labda.setyLepes(labda.getyLepes() - 1);
            labda.setxLepes(labda.getxLepes());
            labda.setyLepes(labda.getyLepes());
        }

        if (labda.intersects(jobbuto)) {       //rectangle
            utes.lejatsz();
            labda.setxLepes(Math.abs(labda.getxLepes()));
            labda.setxLepes(labda.getxLepes() + 1); //nehezites
            if (labda.getyLepes() > 0)
                labda.setyLepes(labda.getyLepes() + 1);     //nehezites
            else
                labda.setyLepes(labda.getyLepes() - 1);
            labda.setxLepes(-labda.getxLepes());
            labda.setyLepes(labda.getyLepes());
        }

        //bal oldalt gol+ uj labda
        if (labda.x <= 0) {
            gol.lejatsz();
            eredmeny.setJatekos2(eredmeny.getJatekos2() + 1);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ujUto();
            ujLabda();
        }

        //jobb oldalt gol+ uj labda
        if (labda.x >= ABLAK_SZ - LABDA_ATMERO) {
            gol.lejatsz();
            eredmeny.setJatekos1(eredmeny.getJatekos1() + 1);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ujUto();
            ujLabda();
        }


    }


    public void run() {
        //loop
        long ido1 = System.nanoTime();
        double ns = 1000000000 / 60.0;              //60 fps -foto/sec
        double kulonbseg = 0;
        while (true) {
            long ido2 = System.nanoTime();
            kulonbseg += (ido2 - ido1) / ns;
            ido1 = ido2;
            if (kulonbseg >= 1) {
                move();
                utkozesEllenorzes();
                repaint();
                kulonbseg--;
            }
        }

    }

    public class ac_l extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            baluto.keyPressed(e);
            jobbuto.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            baluto.keyReleased(e);
            jobbuto.keyReleased(e);
        }
    }

    public static class Hangok {
        private Clip clip;

        public void setFile(String soundFileName) {
            try {
                File file = new File(soundFileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void lejatsz() {
            clip.setFramePosition(0);
            clip.start();
        }

    }

}
