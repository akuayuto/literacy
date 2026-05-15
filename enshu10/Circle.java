package enshu10;

import java.awt.*;
import java.awt.geom.*;

public class Circle extends Figure {
    Ellipse2D f;

    @Override
    public void paint(Graphics2D g) { // 描画のための処理
        double size = Math.sqrt((double)(w * w + h * h)); // 半径
        f = new Ellipse2D.Double(x - size, y - size, size * 2, size * 2);
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        g.setPaint(color);
        g.draw(f); // g.fill(f);
    }
}

