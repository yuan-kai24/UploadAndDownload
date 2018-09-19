package ry.yk.entity;

import ry.yk.imp.Download;
import ry.yk.imp.Upload;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class GUI {
    private JFrame jf = new JFrame("凯凯制作");
    private Container con = jf.getContentPane();
    private Upload up = new Upload();
    private Download down = new Download();
    private JTabbedPane tabbedPane;
    JPanel panel1,panel2;


    public JFrame initGui() {
        jf.setBounds(100, 100, 525, 400);
        con.setBackground(Color.white);
        //FrameSet.setBound_Move(jf);

        jf.setResizable(false);
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);   //创建选项卡面板对象
        //创建面板
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel2 = new JPanel();
        panel2.setLayout(null);

        //将标签面板加入到选项卡面板对象上
        tabbedPane.addTab("上 传", panel1);
        tabbedPane.addTab("下 载", panel2);
        tabbedPane.setFont(new Font("微软雅黑",Font.PLAIN,20));

        con.add(tabbedPane);

        setUpload(panel1);
        setDown(panel2);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return jf;
    }

    private void setUpload(JPanel jp) {
        JButton jb = new JButton("选择上传文件");
        jb.setBounds(300, 30, 130, 150);
        jb.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        /* ---------------------------- */
        JTextArea jtf = new JTextArea();
        jtf.setBounds(15, 30, 260, 150);
        jtf.setLineWrap(true);
        jtf.setEnabled(false);
        /* ---------------------------- */

        jb.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up.setChoosepath(jtf);
            }
        });

        /* ============================================================================ */
        JButton upb = up.setUploadButton(jtf);
        upb.setBounds(15, 200, 415, 50);

        jp.add(upb);
        jp.add(jtf);
        jp.add(jb);
    }

    private void setDown(JPanel jp) {//下载选择

        JButton jb = new JButton("下载");
        jb.setBounds(300, 30, 130, 200);
        jb.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        JScrollPane jspw = new JScrollPane();
        //建立选项列表组件
        ArrayList<String> filename = new ArrayList<>();
        File fl = new File(down.getDownloadpath());
        File[] files = fl.listFiles();
        for (File f : files) {
            filename.add(f.getName());
        }
        JList jlif = new JList(filename.toArray());
        jlif.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jspw.setViewportView(jlif);
        jspw.setBounds(15, 30, 270, 200);

        jb.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String path = (String) jlif.getSelectedValue();
                if (path != null) {
                    JTextArea jf = new JTextArea();
                    jf.setText(path);
                    down.setChoosepath(jf);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "请选择需要下载的文件", "Error",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        jp.add(jspw);
        jp.add(jb);

    }

    public JFrame getJf() {
        return jf;
    }


}
