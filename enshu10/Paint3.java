package enshu10;

import javax.swing.*;
import java.awt.*;

public class Paint3 extends JFrame {
    JButton clearBtn;
    JButton endBtn;
    ButtonGroup bg;
    JRadioButton r1, r2, r3, r4;

    public static void main(String[] args) {
        Paint3 p = new Paint3();
        PaintCanvas canvas;
        p.setTitle("Paint3");
        // canvas
        canvas = new PaintCanvas(p);
        canvas.setBackground(Color.WHITE);

        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        p.r1 = new JRadioButton("点", true);
        p.r2 = new JRadioButton("円");
        p.r3 = new JRadioButton("四角");
        p.r4 = new JRadioButton("線");
        p.bg = new ButtonGroup();
        p.bg.add(p.r1);
        p.bg.add(p.r2);
        p.bg.add(p.r3);
        p.bg.add(p.r4);

        p.clearBtn = new JButton("消去");
        p.endBtn = new JButton("終了");

        p.clearBtn.addActionListener(canvas);
        p.endBtn.addActionListener(canvas);

        toolBar.add(p.r1);
        toolBar.add(p.r2);
        toolBar.add(p.r3);
        toolBar.add(p.r4);
        toolBar.addSeparator(new Dimension(0, 200));
        toolBar.add(p.clearBtn);
        toolBar.add(p.endBtn);

        p.getContentPane().add(toolBar, BorderLayout.EAST);
        p.getContentPane().add(canvas, BorderLayout.CENTER);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setSize(900, 600);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }
}

