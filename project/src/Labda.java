import java.awt.*;
import java.util.Random;

public class Labda extends Rectangle {

    private int xLepes;
    private int yLepes;

    public Labda(int x, int y, int a) {
        super(x, y, a, a);
        Random random;
        random = new Random();

        int randomXIrany = random.nextInt(2);
        if (randomXIrany == 0)
            randomXIrany--;
        int gyorsassag = 2;
        setxLepes(randomXIrany * gyorsassag);

        int randomYIrany = random.nextInt(2);
        if (randomYIrany == 0)
            randomYIrany--;
        setyLepes(randomYIrany * gyorsassag);
    }

    public void move() {
        x += xLepes;
        y += yLepes;
    }

    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, width, height);
    }

    public void setxLepes(int xLepes) {
        this.xLepes = xLepes;
    }

    public void setyLepes(int yLepes) {
        this.yLepes = yLepes;
    }

    public int getxLepes() {
        return xLepes;
    }

    public int getyLepes() {
        return yLepes;
    }
}
