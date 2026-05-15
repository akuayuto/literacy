package enshu11;

import java.io.*;

public class Coord implements Serializable {
    double x, y;

    Coord() {
        x = y = 0;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void moveto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // public void paint(Graphics2D g){} // 描画処理用（ただしここでは描画しない）
}
