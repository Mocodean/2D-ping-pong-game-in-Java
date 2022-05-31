import java.awt.*;
import java.awt.event.KeyEvent;

public class Uto extends Rectangle {

    private final int id;   //mivel ket utonk van
    private final int szelessege;
    private final int magassaga;
    private int yMozgasa;

    public Uto(int x, int y, int szelessege, int magassaga, int id) {
        super(x, y, szelessege, magassaga);
        this.id = id;
        this.szelessege = szelessege;
        this.magassaga = magassaga;
    }

    public void move() {
        y += yMozgasa;
    }

    public void draw(Graphics g) {
        if (id == 0)
            g.setColor(Color.RED);
        else g.setColor(Color.GREEN);
        g.fillRect(x, y, szelessege, magassaga);
    }


    public void keyPressed(KeyEvent e) {
        int lepes = 10;
        if (id == 0) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                setYHelyzete(-lepes);
                move();
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                setYHelyzete(lepes);
                move();
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                setYHelyzete(-lepes);
                move();
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                setYHelyzete(lepes);
                move();
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        if (id == 0) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                setYHelyzete(0);
                move();
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                setYHelyzete(0);
                move();
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                setYHelyzete(0);
                move();
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                setYHelyzete(0);
                move();
            }
        }

    }

    public void setYHelyzete(int lepes) {
        yMozgasa = lepes;
    }

}
