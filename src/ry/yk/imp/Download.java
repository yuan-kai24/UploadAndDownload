package ry.yk.imp;

import ry.yk.inte.IDownload;
import ry.yk.inte.UpAndDown;

import javax.swing.*;
import java.io.*;
import java.math.BigInteger;

public class Download extends UpAndDown implements IDownload {
    @Override
    public void setChoosepath(JTextArea jtf) {
        JFileChooser jfc = new JFileChooser();//文件选择器
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//选择文件
        int i = jfc.showOpenDialog(null);//打开选择窗口
        if(i == 0)
        {
            File fl = jfc.getSelectedFile();//获取文件路径
            if (fl != null)
            {
                choosepath = jfc.getSelectedFile().getAbsolutePath();//路径存储
            }
            download(jtf.getText());
        }

    }

    @Override
    public String getPreview() {
        return null;
    }

    @Override
    public void storFile() {

    }

    @Override
    public void cretaTem() {

    }

    @Override
    public String getTemPath() {
        return null;
    }

    @Override
    public BigInteger getOffset() {
        return null;
    }

    @Override
    public void download(String name) {//下载

        //资源路径，下载路径
        File sou = new File(downloadpath+"\\" + name);
        File down = new File(choosepath+"\\" + name);

        try {
            byte[] by = new byte[8*1024];
            FileInputStream fin = new FileInputStream(sou);
            FileOutputStream fout = new FileOutputStream(down);
            int len = fin.read(by);
            while(len != -1){
                fout.write(by);
                len = fin.read(by,0,len);
            }
            JOptionPane.showConfirmDialog(null, "成功下载了"+name, "成功", 0);
            fout.close();
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExitis() {
        return false;
    }
}
