package enshu11;

import javax.swing.*;
import java.awt.*;

public class Paint4 extends JFrame {

    // 既存
    JButton clearBtn, endBtn;
    ButtonGroup bg;
    JRadioButton r1, r2, r3, r4;

    // ★追加：色選択
    ButtonGroup colorBg;
    JRadioButton cBlack, cRed, cBlue, cGreen;

    // ★追加：線の太さ
    ButtonGroup widthBg;
    JRadioButton wThin, wMedium, wBold;

    // ★追加：塗りつぶし
    JCheckBox fillBox;

    // ★追加：Undo / Redo
    JButton undoBtn, redoBtn;

    // ★追加：保存・読み込み
    JButton saveBtn, loadBtn;
    JTextField fileField;

    public static void main(String[] args) {

        Paint4 p = new Paint4();
        PaintCanvas canvas;
        String fname = null;

        if (args.length == 1) fname = args[0];

        p.setTitle("Paint4 改造版");

        // canvas
        canvas = new PaintCanvas(p, fname);
        canvas.setBackground(Color.WHITE);

        // ★ツールバー
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        // -----------------------
        // ★図形選択
        // -----------------------
        p.r1 = new JRadioButton("丸", true);
        p.r2 = new JRadioButton("円");
        p.r3 = new JRadioButton("四角");
        p.r4 = new JRadioButton("線");
        p.bg = new ButtonGroup();
        p.bg.add(p.r1);
        p.bg.add(p.r2);
        p.bg.add(p.r3);
        p.bg.add(p.r4);

        toolBar.add(new JLabel("図形"));
        toolBar.add(p.r1);
        toolBar.add(p.r2);
        toolBar.add(p.r3);
        toolBar.add(p.r4);
        toolBar.addSeparator();

        // -----------------------
        // ★色選択
        // -----------------------
        p.cBlack = new JRadioButton("黒", true);
        p.cRed   = new JRadioButton("赤");
        p.cBlue  = new JRadioButton("青");
        p.cGreen = new JRadioButton("緑");

        p.colorBg = new ButtonGroup();
        p.colorBg.add(p.cBlack);
        p.colorBg.add(p.cRed);
        p.colorBg.add(p.cBlue);
        p.colorBg.add(p.cGreen);

        toolBar.add(new JLabel("色"));
        toolBar.add(p.cBlack);
        toolBar.add(p.cRed);
        toolBar.add(p.cBlue);
        toolBar.add(p.cGreen);
        toolBar.addSeparator();

        // -----------------------
        // ★線の太さ
        // -----------------------
        p.wThin = new JRadioButton("細", true);
        p.wMedium = new JRadioButton("中");
        p.wBold = new JRadioButton("太");

        p.widthBg = new ButtonGroup();
        p.widthBg.add(p.wThin);
        p.widthBg.add(p.wMedium);
        p.widthBg.add(p.wBold);

        toolBar.add(new JLabel("線の太さ"));
        toolBar.add(p.wThin);
        toolBar.add(p.wMedium);
        toolBar.add(p.wBold);
        toolBar.addSeparator();

        // -----------------------
        // ★塗りつぶし
        // -----------------------
        p.fillBox = new JCheckBox("塗りつぶし");

        toolBar.add(p.fillBox);
        toolBar.addSeparator();

        // -----------------------
        // ★Undo / Redo
        // -----------------------
        p.undoBtn = new JButton("Undo");
        p.redoBtn = new JButton("Redo");

        p.undoBtn.addActionListener(canvas);
        p.redoBtn.addActionListener(canvas);

        toolBar.add(p.undoBtn);
        toolBar.add(p.redoBtn);
        toolBar.addSeparator();

        // -----------------------
        // ★保存 / 読み込み
        // -----------------------
        p.fileField = new JTextField("paint.dat", 10);
        p.saveBtn = new JButton("保存");
        p.loadBtn = new JButton("読み込み");

        p.saveBtn.addActionListener(canvas);
        p.loadBtn.addActionListener(canvas);

        toolBar.add(new JLabel("ファイル名"));
        toolBar.add(p.fileField);
        toolBar.add(p.saveBtn);
        toolBar.add(p.loadBtn);
        toolBar.addSeparator();

        // -----------------------
        // ★消去 / 終了
        // -----------------------
        p.clearBtn = new JButton("消去");
        p.endBtn   = new JButton("終了");

        p.clearBtn.addActionListener(canvas);
        p.endBtn.addActionListener(canvas);

        toolBar.add(p.clearBtn);
        toolBar.add(p.endBtn);

        // -----------------------
        p.getContentPane().add(toolBar, BorderLayout.EAST);
        p.getContentPane().add(canvas, BorderLayout.CENTER);

        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setSize(1000, 700);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }
}
