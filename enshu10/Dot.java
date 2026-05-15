package enshu10;

import java.awt.*;
import java.awt.geom.*;

public class Dot extends Figure {
    double size = 10.0;
    Ellipse2D f;

    @Override
    public void paint(Graphics2D g) { // 描画のための処理
        f = new Ellipse2D.Double(x - size / 2, y - size / 2, size, size);
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        g.setPaint(color);
        g.draw(f); // g.fill(f);
    }
}
