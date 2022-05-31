import javax.swing.*;
import java.awt.*;

public class JatekVege extends JPanel {
    static final int ABLAK_SZ=1000;
    static final int ABLAK_M =650;
    static final Dimension ABLAK_MERET=new Dimension(ABLAK_SZ,ABLAK_M);

    public JatekVege(){
        this.setPreferredSize(ABLAK_MERET);
        this.setBackground(new Color(71, 200, 20));
    }
}
