package enshu11;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Circle extends Figure implements Serializable {
    private static final long serialVersionUID = 1L;

    Ellipse2D f;

    @Override
    public void paint(Graphics2D g) { // 描画のための処理

        // 半径として使うため、距離を計算
        double size = Math.sqrt((double)(w * w + h * h));

        // 左上座標を計算
        double left = x - size;
        double top  = y - size;

        // 円の形状
        f = new Ellipse2D.Double(left, top, size * 2, size * 2);

        // 線の太さ
        g.setStroke(new BasicStroke(strokeWidth,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // 色
        g.setPaint(color);

        // 塗りつぶし or 枠線
        if (filled) {
            g.fill(f);
        } else {
            g.draw(f);
        }
    }
}

