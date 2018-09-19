package ry.yk.inte;

import javax.swing.*;
import java.util.UUID;

public abstract class UpAndDown {
    protected static final String downloadpath = "F:\\IDEA_Java\\download_upload\\src\\ry\\yk\\resource";//上传下载路径
    protected String choosepath = null;//选择路径
    protected int offset;//偏移量
    public abstract void setChoosepath(JTextArea jtf);//设置路径
    public abstract String getPreview();//预览

    public String getUUID(){//生成随机文件名
        return UUID.randomUUID().toString().replace("-", "");
    }
    public String getDownloadpath(){return downloadpath;}
}
