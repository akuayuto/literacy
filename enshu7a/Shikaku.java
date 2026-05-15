package enshu7a;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class Shikaku extends Figure1 {
    double width, height;
    Rectangle2D r;
    Random rand = new Random();

    Shikaku() {
        // ランダムサイズ（10〜50）
        width = 10 + rand.nextInt(41);
        height = 10 + rand.nextInt(41);

        // ランダム色
        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    @Override
    public void paint(Graphics2D g) {
        r = new Rectangle2D.Double(x - width / 2, y - height / 2, width, height);
        g.setPaint(color);
        g.fill(r);
    }
}
