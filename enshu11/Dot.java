package enshu11;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Dot extends Figure implements Serializable {
    private static final long serialVersionUID = 1L;

    double size = 10.0;
    Ellipse2D f;

    @Override
    public void paint(Graphics2D g) { // 描画のための処理

        // 中心 (x, y) の周りに size の円
        f = new Ellipse2D.Double(x - size / 2, y - size / 2, size, size);

        // 線の太さ反映（★ここが重要）
        g.setStroke(new BasicStroke(strokeWidth,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g.setPaint(color);

        // 塗りつぶし対応
        if (filled) {
            g.fill(f);
        } else {
            g.draw(f);
        }
    }
}
