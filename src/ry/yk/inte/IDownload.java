package ry.yk.inte;

import java.math.BigInteger;

public interface IDownload {
    public void storFile();
    public abstract void cretaTem();//创建偏移量临时文件
    public abstract String getTemPath();//扫描偏移量临时文件
    public abstract BigInteger getOffset();//获取偏移量
    public abstract void download(String name);//下载
    public abstract boolean isExitis();//判断是否下载

}
