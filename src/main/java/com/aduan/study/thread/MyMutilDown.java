package com.aduan.study.thread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyMutilDown {
    /**
     * 文件后缀名
     *
     * @param path
     * @return
     */
    public String getFileExtName(String path) {
        return path.substring(path.lastIndexOf("."));
    }

    /**
     * 多线程并行下载文件
     *
     * @param filePath
     *            文件保存路径
     * @param url
     *            url
     * @param tnum
     *            线程数量
     */
    public void mutiDown(String filePath, String url, int tnum) {
        try {
            // 要写入的文件
            final File file = new File(filePath + getFileExtName(url));
            RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");// 建立随机访问
            final URL ul = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) ul.openConnection();
            conn.setConnectTimeout(2000);// 请求超时时间
            conn.setRequestMethod("GET");
            int len = conn.getContentLength();// 文件长度
            accessFile.setLength(len);
            accessFile.close();
            final int block = (len + tnum - 1) / tnum;  // 每个线程下载的快大小

            for (int i = 0; i < tnum; i++) {
                final int a = i;
                new Thread(new Runnable() {
                    int start = block * a;// 开始位置
                    int end = block * (a + 1) - 1;// 结束位置

                    @Override
                    public void run() {
                        HttpURLConnection conn2 = null;
                        RandomAccessFile accessFile2 = null;
                        InputStream in = null;
                        try {
                            conn2 = (HttpURLConnection) ul.openConnection();
                            conn2.setConnectTimeout(2000);// 请求超时时间
                            conn2.setRequestMethod("GET");
                            // TODO Auto-generated method stub
                            conn2.setRequestProperty("Range", "bytes=" + start
                                    + "-" + end + "");// 设置一般请求属性 范围
                            in = conn2.getInputStream();
                            byte[] data = new byte[1024];
                            int len = 0;
                            accessFile2 = new RandomAccessFile(file, "rwd");
                            accessFile2.seek(start);

                            while ((len = in.read(data)) != -1) {
                                System.out.println(a + "/" + len);
                                accessFile2.write(data, 0, len);//并发写入
                            }
                            System.out.println("线程:" + a + "下载完成!");
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } finally {
                            try {
                                accessFile2.close();
                                in.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    }
                }).start();

            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        MyMutilDown mydown = new MyMutilDown();
        String path = "https://resource.testnet.pp.io/demo/release/macos/latest/ppio-demo.dmg";
        // 开启5个线程并行下载
        mydown.mutiDown("D://", path, 5);
    }
}
