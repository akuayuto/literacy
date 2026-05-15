package enshu7a;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class Circle1 extends Figure1 {
    double size;
    Ellipse2D f;
    Random rand = new Random();

    Circle1() {
        // ④ 色つけ：ランダムカラー
        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        // 初期サイズ（10〜50の範囲でランダム）
        size = 10 + rand.nextInt(41);
    }

    @Override
    public void paint(Graphics2D g) {
        f = new Ellipse2D.Double(x - size / 2, y - size / 2, size, size);
        g.setPaint(color);

        // ⑤ 塗りつぶし描画に変更
        g.fill(f);
    }
}
