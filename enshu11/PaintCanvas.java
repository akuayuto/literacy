package enshu11;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

// 描画キャンバス
public class PaintCanvas extends JPanel
        implements MouseListener, MouseMotionListener, ActionListener {

    Figure obj = null;
    double x, y;
    Paint4 p4;

    ArrayList<Figure> objList;   // 現在の描画物
    ArrayList<Figure> undoList;  // Undoされた図形（Redo用）

    int mode = 0; // 1 = 点/丸, 2 = 通常図形, 3 = 折れ線などで拡張可能

    PaintCanvas(Paint4 p, String fname) {
        this.p4 = p;
        objList = new ArrayList<>();
        undoList = new ArrayList<>();

        addMouseListener(this);
        addMouseMotionListener(this);

        if (fname != null) load(fname);
    }

    // ★現在の色を Paint4 から取得
    private Color getCurrentColor() {
        if (p4.cRed.isSelected()) return Color.RED;
        if (p4.cBlue.isSelected()) return Color.BLUE;
        if (p4.cGreen.isSelected()) return Color.GREEN;
        return Color.BLACK;
    }

    // ★線の太さ
    private float getStrokeWidth() {
        if (p4.wBold.isSelected()) return 5.0f;
        if (p4.wMedium.isSelected()) return 3.0f;
        return 2.0f; // 細線
    }

    // ★塗りつぶしかどうか
    private boolean getFilled() {
        return p4.fillBox.isSelected();
    }

    // マウス押下
    @Override
    public void mousePressed(MouseEvent e) {

        Point2D p = e.getPoint();
        x = p.getX();
        y = p.getY();

        // 図形選択
        if (p4.r1.isSelected()) {
            mode = 1;
            obj = new Dot();
        } else if (p4.r2.isSelected()) {
            mode = 2;
            obj = new Circle();
        } else if (p4.r3.isSelected()) {
            mode = 2;
            obj = new Rect();
        } else if (p4.r4.isSelected()) {
            mode = 2;
            obj = new Line();
        }

        if (obj != null) {
            obj.moveto(x, y);
            obj.setColor(getCurrentColor());
            obj.setStrokeWidth(getStrokeWidth());
            obj.setFilled(getFilled());
            undoList.clear();  // 新しい描画を始めたらRedo履歴は消す
        }

        repaint();
    }

    // マウスドラッグ
    @Override
    public void mouseDragged(MouseEvent e) {

        Point2D p = e.getPoint();
        x = p.getX();
        y = p.getY();

        if (mode == 1) {
            obj.moveto(x, y);
        } else if (mode == 2) {
            obj.setWH(x - obj.x, y - obj.y);
        }

        repaint();
    }

    // マウス離し
    @Override
    public void mouseReleased(MouseEvent e) {

        Point2D p = e.getPoint();
        x = p.getX();
        y = p.getY();

        if (mode == 1) obj.moveto(x, y);
        else if (mode == 2) obj.setWH(x - obj.x, y - obj.y);

        if (mode >= 1) {
            objList.add(obj);
            obj = null;
        }

        mode = 0;
        repaint();
    }

    // 使わないイベント
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    // ★ボタン押下（Undo/Redo/クリア/保存/読み込み）
    @Override
    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();

        if (src == p4.endBtn) {
            save("paint.dat");
            System.exit(0);

        } else if (src == p4.clearBtn) {
            // 全消去 → Undoに全保存
            undoList.addAll(objList);
            objList.clear();
            repaint();

        } else if (src == p4.undoBtn) {
            if (!objList.isEmpty()) {
                Figure f = objList.remove(objList.size() - 1);
                undoList.add(f);
                repaint();
            }

        } else if (src == p4.redoBtn) {
            if (!undoList.isEmpty()) {
                Figure f = undoList.remove(undoList.size() - 1);
                objList.add(f);
                repaint();
            }

        } else if (src == p4.saveBtn) {
            save(p4.fileField.getText());

        } else if (src == p4.loadBtn) {
            load(p4.fileField.getText());
        }
    }

    // ★描画
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // 全図形描画
        for (Figure f : objList) {
            f.paint(g2);
        }

        // 描画途中の図形
        if (obj != null) obj.paint(g2);
    }

    // ★保存
    public void save(String fname) {
        try {
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objList);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("保存エラー");
        }
    }

    // ★読み込み
    @SuppressWarnings("unchecked")
    public void load(String fname) {
        try {
            FileInputStream fis = new FileInputStream(fname);
            ObjectInputStream ois = new ObjectInputStream(fis);
            objList = (ArrayList<Figure>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("読み込みエラー");
        }
        repaint();
    }
}
