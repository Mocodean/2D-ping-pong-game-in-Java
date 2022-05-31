import java.awt.*;

public class Eredmeny {
    private final int ABLAK_SZ;
    private final int ABLAK_H;
    private int jatekos1;
    private int jatekos2;

    public Eredmeny(int ABLAK_SZ, int ABLAK_H) {
        this.ABLAK_H = ABLAK_H;
        this.ABLAK_SZ = ABLAK_SZ;
        jatekos2 = 0;
        jatekos1 = 0;

    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));

        g.drawLine(ABLAK_SZ / 2, 0, ABLAK_SZ / 2, ABLAK_H);

        g.drawString(String.valueOf(jatekos1 / 10) + jatekos1 % 10, (ABLAK_SZ / 2) - 85, 50);
        g.drawString(String.valueOf(jatekos2 / 10) + jatekos2 % 10, (ABLAK_SZ / 2) + 20, 50);
    }

    public int getJatekos1() {
        return jatekos1;
    }

    public void setJatekos1(int jatekos1) {
        this.jatekos1 = jatekos1;
    }

    public int getJatekos2() {
        return jatekos2;
    }

    public void setJatekos2(int jatekos2) {
        this.jatekos2 = jatekos2;
    }
}
