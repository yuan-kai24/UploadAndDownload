package ry.yk.tool;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class FrameSet {
    private static int x = 0,
                        y = 0;

    @Deprecated
    //暂时没啥用
    public static void setBound_Move(final JFrame jf){
        jf.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                jf.setLocation(e.getX() + jf.getX() - x, e.getY() + jf.getY()
                        - y);
            }
        });
        jf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 27){
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
