package enshu10;

import java.awt.*;
import java.awt.geom.*;

public class Rect extends Figure {
    Rectangle2D f;

    @Override
    public void paint(Graphics2D g) { // 描画のための処理
        f = new Rectangle2D.Double(x - w, y - h, w * 2, h * 2);
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        g.setPaint(color);
        g.draw(f); // g.fill(f);
    }
}

