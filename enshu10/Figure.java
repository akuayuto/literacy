package enshu10;

import java.awt.*;

public class Figure extends Coord {
    Color color;
    double w, h;        // 追加
    Figure(){
        color = Color.BLACK;
    }
    public void paint(Graphics2D g){} // 描画処理用（ただしここでは描画しない）
    public void setWH(double w, double h){  // 追加
        this.w = w;
        this.h = h;
    }
}
