package enshu11;

import java.awt.*;
import java.io.*;

// 図形の共通親クラス
public class Figure extends Coord implements Serializable {

    Color color = Color.BLACK;
    double w, h;
    float strokeWidth = 2.0f;
    boolean filled = false;

    Figure() {}

    public void setWH(double w, double h) {
        this.w = w;
        this.h = h;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void setStrokeWidth(float sw) {
        this.strokeWidth = sw;
    }

    public void setFilled(boolean f) {
        this.filled = f;
    }

    // サブクラスで必ず上書きされる
    public void paint(Graphics2D g) {}
}
