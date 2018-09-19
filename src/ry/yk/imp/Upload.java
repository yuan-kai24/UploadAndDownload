package ry.yk.imp;

import ry.yk.inte.IUpload;
import ry.yk.inte.UpAndDown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class Upload extends UpAndDown implements IUpload {
    private String suffix = "";
    @Override
    public JButton setUploadButton(JTextArea jtf) {

        JButton upb = new JButton("上    传");
        upb.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        upb.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(jtf.getText()))
                {
                    upload(jtf.getText());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "请选择上传的文件", "Error",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        return upb;

    }

    @Override
    public void upload(String name) {
        File sou = new File(choosepath);
        File up = new File(downloadpath + "\\" + getUUID() + suffix);

        try {
            FileInputStream fin = new FileInputStream(sou);
            FileOutputStream fout = new FileOutputStream(up);

            byte [] by = new byte[8*1024];
            int len = fin.read(by);
            while(len != -1){
                fout.write(by,0,len);
                len = fin.read(by);
            }

            fin.close();
            fout.close();
            JOptionPane.showConfirmDialog(null, "上传成功", "提示",JOptionPane.YES_NO_OPTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setChoosepath(JTextArea jtf) {
        FileDialog fd = new FileDialog(new JFrame(), "选择你要上传的文件", FileDialog.LOAD);
        fd.setVisible(true);
        if(fd.getDirectory() != null){
            choosepath = fd.getDirectory() + fd.getFile();
            for(int i = fd.getFile().length()-1;i > 0 ;i--)
            {
                if(fd.getFile().charAt(i) == '.')
                {
                    suffix = fd.getFile().substring(i,fd.getFile().length());
                    break;
                }
            }
            jtf.setText(choosepath);
        }
    }

    @Override
    public String getPreview() {
        return null;
    }
}
