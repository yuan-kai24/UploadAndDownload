package ry.yk.imp;

import ry.yk.inte.IUpload;
import ry.yk.inte.UpAndDown;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class Upload extends UpAndDown implements IUpload {
    private String suffix = "";
    private String watermark_path = "resource\\watermark\\kksy.png";
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
        if(!(suffix.equals(".jpg") || suffix.equals(".png") || suffix.equals(".jpeg")))
        {
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

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            setWatermark(sou);
        }
        JOptionPane.showConfirmDialog(null, "上传成功", "提示",JOptionPane.YES_NO_OPTION);

    }

    @Override
    public void setWatermark(File source) {
        try {
            File file = source;//图片源
        File syfile = new File(watermark_path);//水印路径
        Image sourceimage = ImageIO.read(file);//源图片
        Image wetermark = ImageIO.read(syfile);//水印

            //创建图像缓冲区
        BufferedImage image = new BufferedImage(sourceimage.getWidth(null),sourceimage.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics =  image.createGraphics();
        graphics.drawImage(sourceimage, 0, 0, sourceimage.getWidth(null), sourceimage.getHeight(null), null);
        graphics.drawImage(wetermark, 0, 0, sourceimage.getWidth(null), sourceimage.getHeight(null), null);
        graphics.dispose();
        FileOutputStream out = new FileOutputStream(downloadpath+ "\\" + getUUID() + suffix);
        ImageIO.write(image, "JPG", out);//写
        out.close();

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