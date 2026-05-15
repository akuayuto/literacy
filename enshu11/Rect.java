package enshu11;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class Rect extends Figure implements Serializable {

    Rectangle2D f;

    @Override
    public void paint(Graphics2D g) {

        // ドラッグ方向に依存せず描けるように補正
        double left   = Math.min(x, x + w);
        double top    = Math.min(y, y + h);
        double width  = Math.abs(w);
        double height = Math.abs(h);

        f = new Rectangle2D.Double(left, top, width, height);

        // 線の太さを反映
        g.setStroke(new BasicStroke(strokeWidth,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // 色
        g.setPaint(color);

        // 塗りつぶしか通常描画か
        if (filled) g.fill(f);
        else        g.draw(f);
    }
}
