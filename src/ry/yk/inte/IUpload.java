package ry.yk.inte;

import javax.swing.*;
import java.io.File;

public interface IUpload {
    JButton setUploadButton(JTextArea jtf);//设置按钮
    void upload(String name);//上传
    void setWatermark(File fl);//图片添加水印
}
