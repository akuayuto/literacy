package enshu10;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

// 描画キャンバス
public class PaintCanvas extends JPanel implements MouseListener,
        MouseMotionListener, ActionListener {

    Figure obj = null;
    double x, y;
    Paint3 p3;
    ArrayList<Figure> objList; // 描画する全オブジェクトを管理する
    int mode = 0;

    PaintCanvas(Paint3 p) {
        this.p3 = p;
        objList = new ArrayList<Figure>();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point2D p = e.getPoint();

        x = p.getX();
        y = p.getY();

        if (p3.r1.isSelected()) { // 丸
            mode = 1;
            obj = new Dot();
        } else if (p3.r2.isSelected()) { // 円
            mode = 2;
            obj = new Circle();
        } else if (p3.r3.isSelected()) { // 四角
            mode = 2;
            obj = new Rect();
        } else if (p3.r4.isSelected()) { // 線
            mode = 2;
            obj = new Line();
        }

        if (obj != null) {
            obj.moveto(x, y);
        }

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point2D p = e.getPoint();

        x = p.getX();
        y = p.getY();

        if (mode == 1) {
            obj.moveto(x, y);
        } else if (mode == 2) {
            obj.setWH(x - obj.x, y - obj.y); // 幅と高さの設定
        }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point2D p = e.getPoint();

        x = p.getX();
        y = p.getY();

        if (mode == 1) {
            obj.moveto(x, y);
        } else if (mode == 2) {
            obj.setWH(x - obj.x, y - obj.y);
        }

        if (mode >= 1) {
            objList.add(obj);
            obj = null;
        }

        mode = 0;
        repaint();
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) { // ボタン
        if (e.getSource() == p3.endBtn) { // 終了ボタン
            System.exit(0);
        } else if (e.getSource() == p3.clearBtn) { // クリアボタン
            objList.clear();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Figure f;
        for (int i = 0; i < objList.size(); i++) { // objList のすべての要素について
            f = objList.get(i); // i 番目の要素取得
            f.paint(g2); // その要素の paint メソッド呼出し
        }

        if (obj != null && mode > 1) {
            obj.paint(g2); // 現在作成中の要素も描画
        }
    }
}
