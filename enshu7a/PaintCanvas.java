package enshu7a;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

// 描画キャンバス
public class PaintCanvas extends JPanel implements MouseListener, MouseMotionListener {

    Figure1 obj = null;
    double x, y;
    ArrayList<Figure1> objList;

    public PaintCanvas() {
        objList = new ArrayList<Figure1>();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point2D p = e.getPoint();
        x = p.getX();
        y = p.getY();

        // === 右クリック（BUTTON3）なら四角、左クリック（BUTTON1）なら円 ===
        if (e.getButton() == MouseEvent.BUTTON3) {
            obj = new Shikaku();  // 右クリック → 四角
        } else {
            obj = new Circle1();   // 左クリック → 円
        }

        obj.moveto(x, y);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point2D p = e.getPoint();
        x = p.getX();
        y = p.getY();

        if (obj != null) {
            obj.moveto(x, y);
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point2D p = e.getPoint();
        x = p.getX();
        y = p.getY();

        if (obj != null) {
            obj.moveto(x, y);
            objList.add(obj);

            // === 最大30個まで保持（古いものを削除） ===
            if (objList.size() > 30) {
                objList.remove(0);
            }

            // === 円・四角の総数をコンソール表示 ===
            System.out.println("描いた図形の数: " + objList.size());

            obj = null;
            repaint();
        }
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // === これまでの全図形を描画 ===
        for (Figure1 f : objList) {
            f.paint(g2);
        }

        // === ドラッグ中の図形も描画 ===
        if (obj != null) {
            obj.paint(g2);
        }
    }
}
